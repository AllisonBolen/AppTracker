package com.example.allisonbolen.myapplication;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.example.allisonbolen.myapplication.dummy.DummyContent;

import static com.example.allisonbolen.myapplication.MainActivity.CHANNEL_ID;
import static com.example.allisonbolen.myapplication.MainActivity.notificationManager;


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
        Not();

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

    public void Not(){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.google_logo)
                .setContentTitle("HELLO WORLD")
                .setContentText("WERE MOVING TO MARS")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        // the notification will do somehting with the next code
        Intent resultIntent = new Intent(this, Profile_Activity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Profile_Activity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);


        // Create an explicit intent for an Activity in your app
        notificationManager.notify(1, mBuilder.build());
    }


}
