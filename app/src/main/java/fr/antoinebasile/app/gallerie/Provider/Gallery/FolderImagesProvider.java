package fr.antoinebasile.app.gallerie.Provider.Gallery;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.koushikdutta.ion.Ion;

import java.io.File;

import fr.antoinebasile.app.gallerie.Provider.ImagePost;
import fr.antoinebasile.app.gallerie.Provider.ImagesProvider;

/**
 * Created by a on 05/09/13.
 */
public class FolderImagesProvider implements ImagesProvider {
    private Context context;
    private int local_count = 0;

    private ArrayAdapter<ImagePost> client;

    public FolderImagesProvider(Context context) {
        init(context);
    }

    public FolderImagesProvider(Context context, ArrayAdapter<ImagePost> client) {
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

        String root_sd = Environment.getExternalStorageDirectory().toString();
        File file = new File( root_sd + "/Fakedata/files" ) ;
        File list[] = file.listFiles();



        int count = 0;
        if (local_count<list.length) {
            do {
                File f = list[local_count];
                GalleryImagePost post = new GalleryImagePost(f.getName(),f.toString(),new GalleryImageSetter(f.getAbsolutePath()));
                client.add(post);
                count++;
                local_count++;
            } while (local_count<list.length&&count<10);
        }

    }


    public ArrayAdapter<ImagePost> getClient() {
        return client;
    }

    public void setClient(ArrayAdapter<ImagePost> client) {
        this.client = client;
    }
}
