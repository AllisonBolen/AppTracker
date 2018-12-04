package com.example.allisonbolen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup_activity extends AppCompatActivity {
    private FirebaseAuth authUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditText username = findViewById(R.id.email_editText);
        EditText password = findViewById(R.id.password_editText);
        authUser = FirebaseAuth.getInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// firebase auth

                authUser.createUserWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(Signup_activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("GOOD", "createUserWithEmail:success");
                                    FirebaseUser user = authUser.getCurrentUser();
                                    //updateUI(user);
                                    Intent intentToLogin = new Intent(view.getContext(), HomeActivity.class);
                                    startActivity(intentToLogin);
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Log.w("Failure", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Signup_activity.this, "Authentication failed On Sign Up.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });
                ///

            }
        });


    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = authUser.getCurrentUser();
       // updateUI(currentUser);
    }

}
