package com.tracker.allisonbolen.myapplication;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.tracker.allisonbolen.myapplication.dummy.DummyContent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

import static com.tracker.allisonbolen.myapplication.MainActivity.CHANNEL_ID;
import static com.tracker.allisonbolen.myapplication.MainActivity.notificationManager;


public class HomeActivity extends AppCompatActivity
        implements ApplicationFragment.OnListFragmentInteractionListener {
    private FirebaseAuth authUser;
    private final int changedItem = 0;
    private final int NewItem = 1;
    public static List<DummyContent.Application_Information_Object> allApps;
    public static DatabaseReference database;
    ApplicationFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        allApps = new ArrayList<DummyContent.Application_Information_Object>();
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        authUser = FirebaseAuth.getInstance();
        frag = (ApplicationFragment) getFragmentManager().findFragmentById(R.id.fragment2);



        FloatingActionButton fab =  findViewById(R.id.fab);
       fab.setOnClickListener(v->{
           Intent newAppObject = new Intent(this, new_application_object.class);
           startActivityForResult(newAppObject, NewItem);
        });



    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void checkDates() {
        // check item for an update notification
        allApps.size();
        boolean notify = false;
        for(int i = 0; i < allApps.size(); i++){
            DummyContent.Application_Information_Object application = allApps.get(i);
            DateTime now = DateTime.now();
            String thing = now.toString(DateTimeFormat.shortDate());
            String timeOfApp = application.getAppDate();

            int nowMonth = Integer.parseInt(thing.substring(0, thing.indexOf("/")));
            int nowDay = Integer.parseInt(thing.substring(thing.indexOf("/")+1,thing.indexOf("/",thing.indexOf("/")+1)));
            int nowYear = Integer.parseInt(thing.substring(thing.indexOf("/",thing.indexOf("/")+1)+1));

            int month = Integer.parseInt(timeOfApp.substring(0, timeOfApp.indexOf("/")));
            int day = Integer.parseInt(timeOfApp.substring(timeOfApp.indexOf("/")+1,timeOfApp.indexOf("/",timeOfApp.indexOf("/")+1)));
            int year = Integer.parseInt(timeOfApp.substring(timeOfApp.indexOf("/",timeOfApp.indexOf("/")+1)+1));

            if(nowDay > day || nowMonth > month || nowYear > year ){
               notify = true;
            }
        }
        if(notify){
            Not();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        checkDates();
        allApps.clear();
        FirebaseUser currentUser = authUser.getCurrentUser();
        String uid = currentUser.getUid();
        database = FirebaseDatabase.getInstance().getReference("Users/"+uid+"/Cards");
        database.addChildEventListener (chEvListener);
        database.addValueEventListener(valListener);



    }

    public void onPause() {
        super.onPause();
        database.removeEventListener(chEvListener);
        database.removeEventListener(valListener);
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
            Intent intent = new Intent(this, Settings_Activity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    public void Not(){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_collections_bookmark_black_24dp)
                .setContentTitle("Reminder")
                .setContentText("Have you updated your applications lately?")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        // the notification will do something with the next code
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);


        // Create an explicit intent for an Activity in your app
        notificationManager.notify(1, mBuilder.build());
    }

    private ValueEventListener valListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            frag.adapter.notifyDataSetChanged();

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

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
