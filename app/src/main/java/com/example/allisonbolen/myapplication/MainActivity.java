package com.example.allisonbolen.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.view.MotionEvent;



public class MainActivity extends AppCompatActivity {


    private FirebaseAuth authUser;
    public static String CHANNEL_ID = "TEST";
    public static NotificationManager notificationManager;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = authUser.getCurrentUser();
        if(currentUser != null ) {
            Intent home = new Intent(this, HomeActivity.class);
            startActivity(home);
        }
        // updateUI(currentUser);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "POPS"; //TODO IMPLEMENT AS STRING
            String description = "TESTING"; //TODO getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button signUp = findViewById(R.id.register_button);
        Button login = findViewById(R.id.login_button);
        EditText username = findViewById(R.id.username_editText);
        EditText password = findViewById(R.id.password_editText);
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        // ...
        // Initialize Firebase Auth
        authUser = FirebaseAuth.getInstance();

        // set up push notifications
        createNotificationChannel();
        // mode method
        signUp.setOnClickListener(v -> {
            Intent intentToSignUp = new Intent(this, Signup_activity.class);
            startActivity(intentToSignUp);
        });

        ConstraintLayout layout = findViewById(R.id.main_activity_layout);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });

        login.setOnClickListener(v-> {
            mgr.hideSoftInputFromWindow(username.getWindowToken(), 0);
            mgr.hideSoftInputFromWindow(password.getWindowToken(), 0);
            authUser.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Successful Login", "signInWithEmail:success");
                                FirebaseUser user = authUser.getCurrentUser();
                                Intent intentToLogin = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intentToLogin);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Failed to Auth", "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed On Login.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            // ...
                        }
                    });

        });

        // Set up notifications?

    }


}
