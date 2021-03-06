package com.tracker.allisonbolen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.tracker.allisonbolen.myapplication.dummy.DummyContent.*;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class new_application_object extends AppCompatActivity {
    private final int NewItem = 1;
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
                    jbTitle.getText().toString(), now.toString(DateTimeFormat.shortDate()), jbDesc.getText().toString(),
                    now.toString(DateTimeFormat.shortDate()), contactInfo.getText().toString(), 1);

            Intent intent = new Intent(new_application_object.this, MainActivity.class);
            intent.putExtra("App", tempItem );
            setResult(NewItem, intent);
            finish();
            // DummyContent.addItem(tempItem);
            // I feel like this is wrong and it should use an intent to pass data

            // finish();

        });
    }

}
