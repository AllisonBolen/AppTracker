package com.example.allisonbolen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.example.allisonbolen.myapplication.dummy.DummyContent;


public class HomeActivity extends AppCompatActivity
        implements ApplicationFragment.OnListFragmentInteractionListener {


    public static final int NewItem = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab);
       fab.setOnClickListener(v->{
           Intent newAppObject = new Intent(this, new_application_object.class);
           startActivityForResult(newAppObject, NewItem);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == NewItem){
            DummyContent.Application_Information_Object temp = (DummyContent.Application_Information_Object) data.getSerializableExtra("App");
            DummyContent.addItem(temp);
            MainActivity.database.push().setValue(temp);
        }
    }


    @Override
    public void onListFragmentInteraction(DummyContent.Application_Information_Object item) {
        System.out.println("Interact!");
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;// "true" means the menu should be visible
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_settings) {
            Intent intent = new Intent(this, Settings_Activity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }



}
