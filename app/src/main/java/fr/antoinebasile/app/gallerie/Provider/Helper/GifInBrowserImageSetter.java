package fr.antoinebasile.app.gallerie.Provider.Helper;

import android.content.Context;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.koushikdutta.ion.Ion;

import fr.antoinebasile.app.gallerie.Provider.ImageSetter;

/**
 * Created by a on 09/09/13.
 */
public class GifInBrowserImageSetter implements ImageSetter {

    private String imageUrl;
    private ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public GifInBrowserImageSetter(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void setImage(LinearLayout imageHolder) {
        //TODO move it eslewhere
        float scale = imageHolder.getContext().getResources().getDisplayMetrics().density;

        imageHolder.removeAllViews();
        Context context = imageHolder.getContext().getApplicationContext();

        Ion.with(context);

        WebView gifviewer = new WebView(context);
        gifviewer.loadUrl(imageUrl);
        int pixels = (int) (55 * scale + 0.5f);
        imageHolder.addView(gifviewer,imageHolder.getWidth(),pixels);

    }
}
