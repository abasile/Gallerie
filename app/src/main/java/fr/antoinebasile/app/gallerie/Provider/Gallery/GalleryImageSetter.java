package fr.antoinebasile.app.gallerie.Provider.Gallery;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.koushikdutta.ion.Ion;

import java.io.File;
import java.util.logging.Logger;

import fr.antoinebasile.app.gallerie.Provider.ImageSetter;
import fr.antoinebasile.app.gallerie.R;

/**
 * Created by a on 09/09/13.
 */
public class GalleryImageSetter implements ImageSetter{
    private String imagePath;
    private ImageView imageView;

    public GalleryImageSetter(String imagePath) {
       this.imagePath= imagePath;
    }


    @Override
    public void setImage(LinearLayout imageHolder) {
        imageView = (ImageView) imageHolder.findViewById(R.id.image_placeholder);
        int width =  imageHolder.getWidth();
        File imgFile = new  File(imagePath);
        if(imgFile.exists()){
            Ion.with(imageView).placeholder(R.drawable.twitter)
            //        .resizeWidth(width)
                    .load(imgFile.getAbsolutePath());
        }
    }
}
