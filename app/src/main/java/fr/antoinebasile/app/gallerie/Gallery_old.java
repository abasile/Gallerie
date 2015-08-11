package fr.antoinebasile.app.gallerie;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.antoinebasile.app.gallerie.Provider.ProviderService;


public class Gallery_old extends Activity
            implements NavigationDrawerFragment.NavigationDrawerCallbacks {

        ListView mdrawerList;
        ActionBarDrawerToggle mdrawerToggle;
        List<String> mProvidersNames;
        DrawerLayout mdrawerLayout;
        ProviderService mProviderService;
        /**
         * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
         */
        private NavigationDrawerFragment mNavigationDrawerFragment;
        /**
         * Used to store the last screen title. For use in {@link #restoreActionBar()}.
         */
        private CharSequence mTitle;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_gallery);



            mNavigationDrawerFragment = (NavigationDrawerFragment)
                    getFragmentManager().findFragmentById(R.id.navigation_drawer);
            mTitle = getTitle();

            // Set up the drawer.
            mNavigationDrawerFragment.setUp(
                    R.id.navigation_drawer,
                    (DrawerLayout) findViewById(R.id.drawer_layout));
        }

        @Override
        public void onNavigationDrawerItemSelected(int position) {
            // Create a new fragment and specify the planet to show based on position
            Fragment fragment = new GalleryFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            if(mProvidersNames==null){getProvidersNames();}
            args.putString(GalleryFragment.PROVIDER_KEY, mProvidersNames.get(position));
            fragment.setArguments(args);
            mTitle = mProvidersNames.get(position);
            replaceFragment(fragment);
        }

        public void getProvidersNames(){
            mProviderService = new ProviderService(getApplicationContext());
            mProvidersNames =  new ArrayList<String>(mProviderService.getProviders().keySet());
        }



        public void restoreActionBar() {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(mTitle);
        }

        private void replaceFragment(Fragment fragment){
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            if (!mNavigationDrawerFragment.isDrawerOpen()) {
                // Only show items in the action bar relevant to this screen
                // if the drawer is not showing. Otherwise, let the drawer
                // decide what to show in the action bar.
                getMenuInflater().inflate(R.menu.gallery, menu);
                restoreActionBar();
                return true;
            }
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

    }
