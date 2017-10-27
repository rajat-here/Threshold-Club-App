package com.miapp.club.threshold;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private ImageView image1;
    private int endIndex;
    private int currentIndex;
    private int[] imageArray;
    private static final int TYPE_WIFI = 1;
    private static final int TYPE_MOBILE = 2;
    private static final int TYPE_NOT_CONNECTED = 0;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast toast = new Toast(getApplicationContext());
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinator_layout);
        setupToolbar();

        setupViewPager();

        setupCollapsingToolbar();

        setupDrawer();

    }

    private void setupDrawer() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_dashboard:
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_club:
                        Intent intent = new Intent(getApplicationContext(), Club_Feeds.class);
                        startActivity(intent);
                        drawer.closeDrawers();
                        break;
                   /*case R.id.nav_staff:
                        Toast.makeText(getApplicationContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                        break;*/
                    case R.id.nav_celesta:
                        Toast.makeText(getApplicationContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_anwesha:
                        Toast.makeText(getApplicationContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_about:
                        Intent intent1 = new Intent(getApplicationContext(), About.class);
                        startActivity(intent1);
                        drawer.closeDrawers();
                        break;
                    /*case R.id.nav_developers:
                        Intent intent2 = new Intent(getApplicationContext(), Developers.class);
                        startActivity(intent2);
                        drawer.closeDrawers();
                        break;*/
                }
                return false;
            }
        });



    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageArray = new int[4];
        imageArray[0]=R.drawable.cover_main;
        imageArray[1]=R.drawable.cover1;
        imageArray[2]=R.drawable.club_feeds;
        imageArray[3]=R.drawable.events_cover;
        image1=(ImageView)findViewById(R.id.header);
        int startIndex = 0;
        endIndex = 3;
        nextImage();
    }
    private void nextImage(){
        image1.setImageResource(imageArray[currentIndex]);
        currentIndex++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(currentIndex>endIndex){
                    currentIndex=0;
                }
                nextImage();

            }
        },4000);

    }

    private void setupCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.collapse_toolbar);
        collapsingToolbar.setTitleEnabled(false);
    }

    private void setupViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TabFragment1(), "Events");
        adapter.addFrag(new Tribune(), "Tribune");

        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerInternetCheckReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    private void registerInternetCheckReceiver() {
        IntentFilter internetFilter = new IntentFilter();
        internetFilter.addAction("android.net.wifi.STATE_CHANGE");
        internetFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcastReceiver, internetFilter);
    }


    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int status = getConnectivityStatus(context);
            setSnackbarMessage(status);
        }
    };

    private static int getConnectivityStatus(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }


    private void setSnackbarMessage(int status) {

        if(status==TYPE_NOT_CONNECTED) {

            Snackbar snackbar = Snackbar.make(coordinatorLayout, "No Connection", Snackbar.LENGTH_LONG);
            snackbar.show();

        }


    }
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.team:
                Intent intent = new Intent(this, Coordinator.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }



    static class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
