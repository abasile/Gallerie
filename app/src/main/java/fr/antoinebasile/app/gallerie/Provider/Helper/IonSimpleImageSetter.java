package fr.antoinebasile.app.gallerie.Provider.Helper;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.koushikdutta.ion.Ion;

import fr.antoinebasile.app.gallerie.Provider.ImageSetter;
import fr.antoinebasile.app.gallerie.R;

/**
 * Created by a on 09/09/13.
 */
public class IonSimpleImageSetter implements ImageSetter {

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

    public IonSimpleImageSetter(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void setImage(LinearLayout imageHolder) {
        imageView = (ImageView) imageHolder.findViewById(R.id.image_placeholder);
        Ion.with(imageView).placeholder(R.drawable.twitter).resizeWidth(imageHolder.getWidth()).load(imageUrl);
    }
}
