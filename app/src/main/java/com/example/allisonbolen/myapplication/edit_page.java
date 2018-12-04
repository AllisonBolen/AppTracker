package com.example.allisonbolen.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.allisonbolen.myapplication.dummy.DummyContent;

import org.joda.time.DateTime;

import static com.example.allisonbolen.myapplication.HomeActivity.database;

public class edit_page extends AppCompatActivity {

    private int changedItem = 0;
    private int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent data = getIntent();
        DummyContent.Application_Information_Object temp = (DummyContent.Application_Information_Object) data.getSerializableExtra("App");

        EditText cmpyName = findViewById(R.id.cmpyName);
        EditText cmpyDesc = findViewById(R.id.cmpyDesc);
        EditText jbTitle = findViewById(R.id.jbTitle);
        EditText jbDesc = findViewById(R.id.jbDesc);
        EditText contactInfo = findViewById(R.id.contactInfo);
        Button delete = findViewById(R.id.delete_button);

        cmpyName.setText(temp.getCompanyName());
        cmpyDesc.setText(temp.getCompanyDesc());
        jbTitle.setText(temp.getJobTitle());
        jbDesc.setText(temp.getJobDesc());
        contactInfo.setText(temp.getContactInfo());
        pos = data.getIntExtra("Position", 0);

        delete.setOnClickListener(v -> {
            String key = HomeActivity.allApps.get(pos)._key;
            database.child(key).setValue(null);

            Intent home = new Intent(this, HomeActivity.class);
            startActivity(home);
        });
        ConstraintLayout layout = findViewById(R.id.edit_page_layout);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });

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

                setResult(changedItem, changed);
               // get object in list at position

                finish();
            }
        });
    }

}
