package fr.antoinebasile.app.gallerie;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import fr.antoinebasile.app.gallerie.Provider.ImagePost;
import fr.antoinebasile.app.gallerie.Provider.ImagesProvider;
import fr.antoinebasile.app.gallerie.Provider.ProviderService;


public class GalleryFragment extends Fragment {
    final static public String PROVIDER_KEY = "provider";

    ImagesProvider provider;
    ArrayAdapter<ImagePost> postAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.article, container, false);

        // create a tweet adapter for our list view
        postAdapter = new ArrayAdapter<ImagePost>(this.getActivity(), 0) {


            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null)
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.post, null);

                // we're near the end of the list adapter, so load more items
                if (position >= getCount() - 3)
                    load();

                // grab the tweet (or retweet)
                ImagePost post = getItem(position);


                LinearLayout imageHolder = (LinearLayout)convertView.findViewById(R.id.image_holder);
                imageHolder.setMinimumWidth(parent.getWidth());
                post.getImageSetter().setImage(imageHolder);

                // and finally, set the name and text
                TextView title = (TextView)convertView.findViewById(R.id.title);
                title.setText(post.getTitle());

                TextView body = (TextView)convertView.findViewById(R.id.body);
                body.setText(post.getInfo());
                return convertView;
            }
        };

        // Inflate the layout for this fragment
        ListView listView = (ListView)view.findViewById(R.id.postListView);
        listView.setAdapter(postAdapter);

        // authenticate and do the first load
        load();


        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProviderService providerService = new ProviderService(getActivity().getApplicationContext());
        Bundle bundle = this.getArguments();
        provider = (ImagesProvider) providerService.getProviders().get(bundle.getString(PROVIDER_KEY,null));
    }

    private void load() {

        provider.setClient(postAdapter);
        provider.getList();
    }

    
}
