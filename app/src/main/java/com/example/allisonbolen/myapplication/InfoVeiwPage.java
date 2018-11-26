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

public class InfoVeiwPage extends AppCompatActivity {

    private final int changedItem = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_veiw_page);
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
                Intent editPage = new Intent(view.getContext(), edit_page.class);
                editPage.putExtra("App",temp);
                editPage.putExtra("Position", pos);
                startActivityForResult(editPage, changedItem);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == changedItem){
            String cpname = data.getStringExtra("name");
            String cpDesc = data.getStringExtra("CPDesc");
            String title = data.getStringExtra("Title");
            String JbDesc = data.getStringExtra("JbDesc");
            String ci = data.getStringExtra("ci");

//            database.push().setValue(temp);

        }
    }

}
