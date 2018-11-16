package com.example.allisonbolen.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.allisonbolen.myapplication.dummy.DummyContent.*;
import com.example.allisonbolen.myapplication.dummy.DummyContent;

import org.joda.time.DateTime;

public class new_application_object extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_application_object);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            DateTime now = new DateTime();
            EditText cmpyName = findViewById(R.id.cmpyName);
            EditText cmpyDesc = findViewById(R.id.cmpyDesc);
            EditText jbTitle = findViewById(R.id.jbTitle);
            EditText jbDesc = findViewById(R.id.jbDesc);
            EditText contactInfo = findViewById(R.id.contactInfo);

            Application_Information_Object tempItem = new Application_Information_Object(
                    cmpyName.getText().toString(), cmpyDesc.getText().toString(),
                    jbTitle.getText().toString(), now, jbDesc.getText().toString(),
                    now, contactInfo.getText().toString(), 1);
            DummyContent.addItem(tempItem);
            finish();

        });
    }

}
