package fr.antoinebasile.app.gallerie.Provider.Tumblr;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.antoinebasile.app.gallerie.Provider.ImagePost;
import fr.antoinebasile.app.gallerie.Provider.ImagesProvider;

/**
 * Created by a on 05/09/13.
 */
public class TumblrImagesProvider implements ImagesProvider {
    private Context context;
    private String tumblrName;
    private List<JsonObject> jsonObjects;

    private ArrayAdapter<ImagePost> client;

    public TumblrImagesProvider(Context context, String tumblrName, ArrayAdapter<ImagePost> client) {
        init(context,tumblrName);
        this.client = client;
    }

    public TumblrImagesProvider(Context context, String tumblrName) {
        init(context,tumblrName);
    }

    private void init(Context context, String tumblrName){
        // Enable global Ion logging
        this.context = context;

        this.tumblrName = tumblrName;
        jsonObjects = new ArrayList<JsonObject>();
        Ion.getDefault(context).configure().setLogging("ion-sample", Log.DEBUG);
    }

    @Override
    public void getList() {
        load();
    }

    public void update(){
        for(JsonObject  jsonObject : jsonObjects){
               // "id date title body";
            String title = jsonObject.get("title").getAsString();
            String body = jsonObject.get("body").getAsString();
            String date = jsonObject.get("date").getAsString();
            String id = jsonObject.get("id").getAsString();

            //extract image from body
            Pattern p = Pattern.compile("src=\"(http://(.*)\\.gif)\"");
            Matcher m = p.matcher(body);
            m.matches();
            m.find();

            try {
                String gif = m.group(1);
                TumblrImagePost post = new TumblrImagePost(title,date,new TumblrImageSetter(gif));
                client.add(post);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    Future<JsonObject> loading;

    private void load() {
        // don't attempt to load more if a load is already in progress
        if (loading != null && !loading.isDone() && !loading.isCancelled())
            return;

        // load the tweets
        String url = "http://api.tumblr.com/v2/blog/"+tumblrName+".tumblr.com/posts/text?limit=30&api_key=fuiKNFp9vQFvjLNvx4sUwti4Yb5yGutBN4Xh10LXZhhRKjWlV4";


        // Request tweets from Twitter using Ion.
        // This is done using Ion's Fluent/Builder API.
        // This API lets you chain calls together to build
        // complex requests.

        // This request loads a URL as JsonArray and invokes
        // a callback on completion.
        loading = Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // this is called back onto the ui thread, no Activity.runOnUiThread or Handler.post necessary.
                        if (e != null) {
                            Toast.makeText(context, "Error loading tumblr", Toast.LENGTH_LONG).show();
                            return;
                        }
                        // add the tweets
                        for (int i = 0; i < result.getAsJsonObject("response").getAsJsonArray("posts").size(); i++) {
                            jsonObjects.add(result.getAsJsonObject("response").getAsJsonArray("posts").get(i).getAsJsonObject());
                        }
                        update();
                    }

                });

    }

    public ArrayAdapter<ImagePost> getClient() {
        return client;
    }

    public void setClient(ArrayAdapter<ImagePost> client) {
        this.client = client;
    }
}
