package com.tracker.allisonbolen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile_Activity extends AppCompatActivity {
    private final int PROFILECHANGE = 0;
    private FirebaseAuth authUser;
    private TextView email;
    private TextView password;
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        authUser = FirebaseAuth.getInstance();
        FirebaseUser currentUser = authUser.getCurrentUser();
        
        Button edit_profile = findViewById(R.id.edit_pro_button);
        edit_profile.setOnClickListener(v->{
            Intent editProfile = new Intent(this, EditProfileActivity.class);
            startActivityForResult(editProfile, PROFILECHANGE);
        });

        email = findViewById(R.id.email_lab);
        password = findViewById(R.id.pass_lab);
        username = findViewById(R.id.usr_lab);
        email.setText(currentUser.getEmail());
        password.setText("******");
        username.setText(currentUser.getDisplayName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.d("profileChange", "Before if");
        if(resultCode == PROFILECHANGE && data !=null){
            Log.d("profileChange", "After if");
            authUser = FirebaseAuth.getInstance();
            FirebaseUser currentUser = authUser.getCurrentUser();
            this.email.setText(data.getStringExtra("email"));
            this.password.setText("******");
            this.username.setText(data.getStringExtra("username"));
            Log.d("profileChange", data.getStringExtra("email"));
            Log.d("profileChange", data.getStringExtra("username"));
        }
    }

}
