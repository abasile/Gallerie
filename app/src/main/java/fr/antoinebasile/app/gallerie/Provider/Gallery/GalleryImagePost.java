package fr.antoinebasile.app.gallerie.Provider.Gallery;

import fr.antoinebasile.app.gallerie.Provider.Helper.BaseImagePost;
import fr.antoinebasile.app.gallerie.Provider.ImagePost;
import fr.antoinebasile.app.gallerie.Provider.ImageSetter;

/**
 * Created by a on 07/09/13.
 */
public class GalleryImagePost extends BaseImagePost implements ImagePost {


    public GalleryImagePost(String title, String info, ImageSetter imageSetter) {
        super(title, info, imageSetter);
    }

}