package fr.antoinebasile.app.gallerie.Provider.Gallery;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.koushikdutta.ion.Ion;

import fr.antoinebasile.app.gallerie.Provider.ImagePost;
import fr.antoinebasile.app.gallerie.Provider.ImagesProvider;

/**
 * Created by a on 05/09/13.
 */
public class GalleryImagesProvider implements ImagesProvider {
    private Context context;

    private ArrayAdapter<ImagePost> client;

    public GalleryImagesProvider(Context context) {
        init(context);
    }

    public GalleryImagesProvider(Context context, ArrayAdapter<ImagePost> client) {
        init(context);
        this.client = client;
    }

    public void init(Context context) {
        // Enable global Ion logging
        this.context = context;
        Ion.getDefault(context).configure().setLogging("ion-sample", Log.DEBUG);
    }

    @Override
    public void getList() {
        load();
    }



    private void load() {

        String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, MediaStore.Images.Media._ID);

        if(cursor != null)
        {
            int count = 0;
            if (cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Images.Thumbnails._ID));
                    String path = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    GalleryImagePost post = new GalleryImagePost(path,id,new GalleryImageSetter(path));
                    client.add(post);
                    count++;
                } while (cursor.moveToNext()&&count<10);
            }
            cursor.close();
        }
    }


    public ArrayAdapter<ImagePost> getClient() {
        return client;
    }

    public void setClient(ArrayAdapter<ImagePost> client) {
        this.client = client;
    }
}
