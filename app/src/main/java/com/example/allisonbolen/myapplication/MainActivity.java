package com.example.allisonbolen.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.allisonbolen.myapplication.dummy.DummyContent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth authUser;
    public static DatabaseReference database;
    public static List<DummyContent.Application_Information_Object> allApps;

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = authUser.getCurrentUser();
//        if(currentUser != null ) {
//            Intent home = new Intent(this, HomeActivity.class);
//            startActivity(home);
//        }
//        // updateUI(currentUser);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button signUp = findViewById(R.id.register_button);
        Button login = findViewById(R.id.login_button);
        EditText username = findViewById(R.id.username_editText);
        EditText password = findViewById(R.id.password_editText);
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        allApps = new ArrayList<DummyContent.Application_Information_Object>();

        // ...
        // Initialize Firebase Auth
        authUser = FirebaseAuth.getInstance();

        // mode method
        signUp.setOnClickListener(v -> {
            Intent intentToSignUp = new Intent(this, Signup_activity.class);
            startActivity(intentToSignUp);
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

    }

    @Override
    public void onResume(){
        super.onResume();
        allApps.clear();
        database= FirebaseDatabase.getInstance().getReference("Cards");
        database.addChildEventListener (chEvListener);

    }

    private ChildEventListener chEvListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            DummyContent.Application_Information_Object entry =
                    (DummyContent.Application_Information_Object) dataSnapshot.getValue(DummyContent.Application_Information_Object.class);
            entry._key = dataSnapshot.getKey();
            allApps.add(entry);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            DummyContent.Application_Information_Object entry =
                    (DummyContent.Application_Information_Object) dataSnapshot.getValue(DummyContent.Application_Information_Object.class);
            entry._key = dataSnapshot.getKey();
            List<DummyContent.Application_Information_Object> newApp = new ArrayList<DummyContent.Application_Information_Object>();
            for (DummyContent.Application_Information_Object t : allApps) {
                if (!t._key.equals(dataSnapshot.getKey())) {
                    newApp.add(t);
                }
            }
            allApps = newApp;
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
