package fr.antoinebasile.app.gallerie.Provider.Helper;

import fr.antoinebasile.app.gallerie.Provider.ImagePost;
import fr.antoinebasile.app.gallerie.Provider.ImageSetter;

/**
 * Created by a on 07/09/13.
 */
public class BaseImagePost implements ImagePost {
    protected String title;
    protected String info;
    protected ImageSetter imageSetter;

    @Override
    public String getTitle() {
        return title;
    }

    public BaseImagePost(String title, String info, ImageSetter imageSetter) {
        this.imageSetter = imageSetter;
        this.title = title;
        this.info = info;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public ImageSetter getImageSetter() {
        return imageSetter;
    }
}
