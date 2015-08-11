package fr.antoinebasile.app.gallerie.Provider;

import android.widget.ArrayAdapter;

/**
 * Created by a on 05/09/13.
 */
public interface ImagesProvider {
    public void getList();
    public void setClient(ArrayAdapter<ImagePost> client);
}
