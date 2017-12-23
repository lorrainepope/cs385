package com.wherecycle.smartrecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wherecycle.smartrecycle.model.Locations;

import java.util.ArrayList;

public class DBLayoutActivity extends AppCompatActivity {
    private DatabaseReference myDB;
    private final ArrayList<Locations> myDBArray = new ArrayList<>();
    private ListView myDBList;
    private ArrayList<String> myKeys = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dblayout);

        myDB = FirebaseDatabase.getInstance().getReference(); //.child("Recycling Centres");
        myDBList = (ListView)findViewById(R.id.DBList);

        final ArrayAdapter<Locations> arrayAdapter = new ArrayAdapter<Locations>(this, android.R.layout.simple_list_item_1, myDBArray);
        myDBList.setAdapter(arrayAdapter);
        myDB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                Locations value = dataSnapshot.getValue(Locations.class);
                String key = dataSnapshot.getKey();
                myDBArray.add(value);
                myKeys.add(key);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Locations value = dataSnapshot.getValue(Locations.class);
                String key = dataSnapshot.getKey();
                int index = myKeys.indexOf(key);
                myDBArray.set(index, value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       /* myDB.child("Recycling Centres").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    Locations locations = child.getValue(Locations.class);
                    myDBArray.add(locations);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });/*


    }
}
