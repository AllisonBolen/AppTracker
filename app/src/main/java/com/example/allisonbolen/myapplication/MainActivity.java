package com.example.allisonbolen.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button signUp = findViewById(R.id.register_button);



        // mode method
        signUp.setOnClickListener(v -> {
            Intent intentToSignUp = new Intent(this, Signup_activity.class);
            startActivity(intentToSignUp);
        });





    }


}
