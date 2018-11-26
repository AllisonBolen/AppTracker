package com.example.allisonbolen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.allisonbolen.myapplication.dummy.DummyContent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements ApplicationFragment.OnListFragmentInteractionListener {
    private DatabaseReference database;

    @Override
    public void onResume(){
        super.onResume();
        super.onResume();
        allApps.clear();
        database= FirebaseDatabase.getInstance().getReference("Cards");
        database.addChildEventListener (chEvListener);

    }

    public static List<DummyContent.Application_Information_Object> allApps;
    public static final int NewItem = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        allApps = new ArrayList<DummyContent.Application_Information_Object>();


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
            database.push().setValue(temp);
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
            Intent intent = new Intent(HomeActivity.this, Settings_Activity.class);
            startActivity(intent);
            return true;
        }
        return false;
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
