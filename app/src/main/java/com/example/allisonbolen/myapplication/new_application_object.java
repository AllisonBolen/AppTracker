package com.example.allisonbolen.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
            Application_Information_Object tempItem = new Application_Information_Object(findViewById(R.id.cmpyName).toString(), findViewById(R.id.cmpyDesc).toString(),
                    findViewById(R.id.jbTitle).toString(), now, findViewById(R.id.jbDesc).toString(),
                    now, findViewById(R.id.contactInfo).toString(), 1);
            DummyContent.addItem(tempItem);
            finish();

        });
    }

}
