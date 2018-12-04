package com.example.allisonbolen.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allisonbolen.myapplication.dummy.DummyContent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;

public class EditProfileActivity extends AppCompatActivity {

    private FirebaseAuth authUser;
    private int profileChange = 0;

    private EditText email;
    private EditText password;
    private EditText conf_password;
    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        authUser = FirebaseAuth.getInstance();
        FirebaseUser currentUser = authUser.getCurrentUser();

        email = findViewById(R.id.email_editText);
        password = findViewById(R.id.pass_editText);
        conf_password = findViewById(R.id.conf_pass_editText);
        username = findViewById(R.id.usr_editText);

        email.setText(currentUser.getEmail());
        username.setText(currentUser.getDisplayName());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(EditProfileActivity.this, Profile_Activity.class);
            @Override
            public void onClick(View view) {
                final AtomicBoolean email_done = new AtomicBoolean(false);
                final AtomicBoolean user_done = new AtomicBoolean(false);
                if (!email.getText().toString().equals("")) {
                    currentUser.updateEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            email_done.set(true);
                        }
                    });
                    intent.putExtra("email", email.getText().toString());
                }
                if (!password.getText().toString().equals("")) {
                    if(password.getText().toString().equals(conf_password.getText().toString())) {
                        currentUser.updatePassword(password.getText().toString());
                    }
                }
                if (!username.getText().toString().equals("")) {
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(username.getText().toString()).build();
                    currentUser.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("Username Updated.", "User profile updated.");
                                user_done.set(true);
                            }
                        }
                    });
                    while(!email_done.get() && !user_done.get());
                    intent.putExtra("username", username.getText().toString());
                }
                Log.d("profileChange", "" + profileChange);
                setResult(profileChange, intent);
                finish();

            }
        });


        ConstraintLayout layout = findViewById(R.id.edit_profile_layout);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });



        Button linkedIn_button = findViewById(R.id.connect_button);
        Button temp = findViewById(R.id.tempButton);

        temp.setOnClickListener( v-> {
            generateHashkey();
        });

        linkedIn_button.setOnClickListener( v-> {

            LISessionManager.getInstance(getApplicationContext()).init(this, buildScope(), new AuthListener() {
                @Override
                public void onAuthSuccess() {
                    // Authentication was successful.  You can now do
                    // other calls with the SDK.
                    Toast.makeText(EditProfileActivity.this, "It worked",
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthError(LIAuthError error) {
                    // Handle authentication errors
                    Toast.makeText(EditProfileActivity.this, "Did not work",
                            Toast.LENGTH_SHORT).show();
                    Log.e("Tag", error.toString());
                }
            }, true);
        });
    }

    // Build the list of member permissions our LinkedIn session requires
    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.R_EMAILADDRESS);
    }

    public void generateHashkey(){
        TextView tempPack = findViewById(R.id.tempPack);
        TextView tempHash = findViewById(R.id.tempHash);
        try {
                PackageInfo info = getPackageManager().getPackageInfo(
                "com.example.allisonbolen.myapplication",
                PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    tempPack.setText(info.packageName);
                        tempHash.setText(Base64.encodeToString(md.digest(),
                                Base64.NO_WRAP));
            }
        } catch (PackageManager.NameNotFoundException e) {
                Log.d("NameNotFound", e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
                Log.d("Algorithm", e.getMessage(), e);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Add this line to your existing onActivityResult() method
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);
    }

}
