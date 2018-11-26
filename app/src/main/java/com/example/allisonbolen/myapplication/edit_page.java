package com.example.allisonbolen.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import org.joda.time.DateTime;

public class edit_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateTime now = new DateTime();
                EditText cmpyName = findViewById(R.id.cmpyName);
                EditText cmpyDesc = findViewById(R.id.cmpyDesc);
                EditText jbTitle = findViewById(R.id.jbTitle);
                EditText jbDesc = findViewById(R.id.jbDesc);
                EditText contactInfo = findViewById(R.id.contactInfo);
                finish();
            }
        });
    }

}
