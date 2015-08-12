package fr.antoinebasile.app.gallerie.domain;

import java.net.URL;

/**
 * Created by abasile on 12/08/2015.
 */
public class Image {
    URL url;

    public Image(URL url) {
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
