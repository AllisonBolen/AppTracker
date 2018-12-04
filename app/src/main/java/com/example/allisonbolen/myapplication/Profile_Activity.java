package com.example.allisonbolen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button edit_profile = findViewById(R.id.edit_pro_button);
        edit_profile.setOnClickListener(v->{
            Intent editProfile = new Intent(this, EditProfileActivity.class);
            startActivity(editProfile);
        });

        TextView email = findViewById(R.id.email_lab);
        TextView password = findViewById(R.id.pass_lab);
        TextView username = findViewById(R.id.usr_lab);
        email.setText(currentUser.getEmail());
        password.setText("******");
        username.setText(currentUser.getDisplayName());
    }

}
