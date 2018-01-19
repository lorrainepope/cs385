package com.wherecycle.smartrecycle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wherecycle.smartrecycle.model.Locations;

import java.util.ArrayList;

public class DBLayoutActivity extends ChildActivity {
    private DatabaseReference myDB;
    private ListView myDBList;
    private LocationsAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dblayout);


        myDB = FirebaseDatabase.getInstance().getReference();                                       //instaniate the database at ROOT
        myDBList = (ListView) findViewById(R.id.DBList);                                             //instantiate the listview
        final ArrayList<Locations> myDBArray = new ArrayList<>();                                   //instantiate the object array


        myDB.child("Recycling Centres").addValueEventListener(new ValueEventListener() {            //this method allow us to read data from the database, ROOT->Recycling Centres
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();                       //iterable object gives us a snapshot of the children of the database, ROOT->Recycling Centres->LocationObject0
                for (DataSnapshot child : children) {                                                                                                                                           //->LocationObject1
                    Locations locations = child.getValue(Locations.class);                          //here we assign the current Location to a variable                                         //->LocationObject2
                    myDBArray.add(locations);                                                       //so we can add it to the object array                                                      //->etc, etc, ...
                }
                arrayAdapter = new LocationsAdapter(DBLayoutActivity.this, R.layout.list_locations, myDBArray); //call the adapter
                myDBList.setAdapter(arrayAdapter);                                                  //set the adapter to the listview
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DBLayoutActivity.this, "Database Read Failed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DBLayoutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

