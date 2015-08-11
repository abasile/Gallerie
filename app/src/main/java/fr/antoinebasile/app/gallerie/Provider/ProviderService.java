package fr.antoinebasile.app.gallerie.Provider;

import android.content.Context;

import fr.antoinebasile.app.gallerie.Provider.Gallery.FolderImagesProvider;
import fr.antoinebasile.app.gallerie.Provider.Gallery.GalleryImagesProvider;
import fr.antoinebasile.app.gallerie.Provider.Tumblr.TumblrImagesProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by a on 08/02/14.
 */
public class ProviderService {
    private Map<String, ImagesProvider> providers;
    private Context context;

    public ProviderService(Context context) {
        this.context= context;
        providers = new TreeMap<String,ImagesProvider>();
        providers.put("Gallery", new GalleryImagesProvider(context));
        providers.put("Tumblr", new TumblrImagesProvider(context, "lesjoiesducode"));
        providers.put("Folder", new FolderImagesProvider(context));
    }


    public Map<String, ImagesProvider> getProviders() {
        return providers;
    }

    public void setProviders(Map<String, ImagesProvider> providers) {
        this.providers = providers;
    }
}
