package com.example.allisonbolen.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import webService.QuoteService;

import static webService.QuoteService.BROADCAST_WEATHER;

public class QuotePage extends AppCompatActivity {

    TextView quoteBox, authorBox;


    @Override
    public void onResume(){
        super.onResume();

        //some details removed …

        IntentFilter quoteFilter = new IntentFilter(BROADCAST_WEATHER);
        LocalBroadcastManager.getInstance(this).registerReceiver(quoteReceiver, quoteFilter);
    }

    @Override
    public void onPause(){
        super.onPause();

        //some details removed …
        LocalBroadcastManager.getInstance(this).unregisterReceiver(quoteReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        quoteBox = (TextView) findViewById(R.id.quoteBox);
        authorBox = (TextView) findViewById(R.id.author);
        QuoteService.startGetQuote(this, "p1");
        Intent intentToLogin = new Intent(this, HomeActivity.class);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(intentToLogin);            }
        }, 5000);   //5 seconds



    }

    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(this, MainActivity.class);
        startActivity(setIntent);
        return;
    }

    private BroadcastReceiver quoteReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String summary = bundle.getString("SUMMARY");
            String author = bundle.getString("AUTHOR");
            String key = bundle.getString("KEY");
            //setWeatherViews(View.VISIBLE);
            if (key.equals("p1"))  {
                quoteBox.setText(summary);
                authorBox.setText(author);
            }

        }
    };

}
