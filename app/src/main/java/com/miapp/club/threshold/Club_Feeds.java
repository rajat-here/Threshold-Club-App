package com.miapp.club.threshold;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import java.util.ArrayList;
import java.util.List;

public class Club_Feeds extends AppCompatActivity {


    private static final int TYPE_WIFI = 1;
    private static final int TYPE_MOBILE = 2;
    private static final int TYPE_NOT_CONNECTED = 0;
    private LinearLayout linearLayout;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_feeds);
        Context context = getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Club Feeds");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.feeds);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        List<Messages> messages = new ArrayList<>();

        mProgressBar.setVisibility(View.VISIBLE);
        int secondsDelayed = 3;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mProgressBar.setVisibility(View.GONE);

            }
        }, secondsDelayed * 1000);

        Feed_Adapter madapter = new Feed_Adapter(messages, context);
        recyclerView.setAdapter(madapter);
        madapter.notifyDataSetChanged();
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

            Snackbar snackbar = Snackbar.make(linearLayout, "No Connection", Snackbar.LENGTH_LONG);
            snackbar.show();
            mProgressBar.setVisibility(View.GONE);
        }


    }
}

