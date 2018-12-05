package com.example.allisonbolen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

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


        Button logout_button = findViewById(R.id.log_out_button);
        logout_button.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent loggedout = new Intent(this, MainActivity.class);
            startActivity(loggedout);
        });

    }

}
