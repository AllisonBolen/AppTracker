package com.example.allisonbolen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Settings_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button profile_button = findViewById(R.id.pro_info);
        profile_button.setOnClickListener(v -> {
            Intent profile_activity = new Intent(this, Profile_Activity.class);
            startActivity(profile_activity);
        });

        Button notification_button = findViewById(R.id.notif_button);
        notification_button.setOnClickListener(v -> {
            Intent notifications_activity = new Intent(this, NotificationsActivity.class);
            startActivity(notifications_activity);
        });

    }

}
