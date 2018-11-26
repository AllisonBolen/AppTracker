package com.example.allisonbolen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.allisonbolen.myapplication.dummy.DummyContent;

import org.joda.time.DateTime;

public class edit_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent data = getIntent();
        DummyContent.Application_Information_Object temp = (DummyContent.Application_Information_Object) data.getSerializableExtra("App");
        int pos = data.getIntExtra("Position", 0);

        EditText cmpyName = findViewById(R.id.cmpyName);
        EditText cmpyDesc = findViewById(R.id.cmpyDesc);
        EditText jbTitle = findViewById(R.id.jbTitle);
        EditText jbDesc = findViewById(R.id.jbDesc);
        EditText contactInfo = findViewById(R.id.contactInfo);

        cmpyName.setText(temp.getCompanyName());
        cmpyDesc.setText(temp.getCompanyDesc());
        jbTitle.setText(temp.getJobTitle());
        jbDesc.setText(temp.getJobDesc());
        contactInfo.setText(temp.getContactInfo());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Make changes to object

               String cpName = cmpyName.getText().toString();
               String cpDesc = cmpyDesc.getText().toString();
               String jbT = jbTitle.getText().toString();
               String jbDes = jbDesc.getText().toString();
               String ci = contactInfo.getText().toString();

                Intent changed = new Intent(edit_page.this, InfoVeiwPage.class);
                changed.putExtra("Name", cpName );
                changed.putExtra("CPDesc", cpDesc );
                changed.putExtra("Title", jbT );
                changed.putExtra("JbDesc", jbDes );
                changed.putExtra("ci", ci );

                setResult(HomeActivity.NewItem, changed);
               // get object in list at position

                finish();
            }
        });
    }

}
