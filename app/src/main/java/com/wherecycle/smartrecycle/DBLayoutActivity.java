package com.wherecycle.smartrecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DBLayoutActivity extends AppCompatActivity {
    private DatabaseReference myDB;
    private ArrayList<Locations> myDBArray = new ArrayList<>();
    private ListView myDBList;
    private ArrayList<Locations> myKeys = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dblayout);

        myDB = FirebaseDatabase.getInstance().getReference();
        myDBList = (ListView)findViewById(R.id.DBList);

        final ArrayAdapter<Locations> arrayAdapter = new ArrayAdapter<Locations>(this, android.R.layout.simple_list_item_1, myDBArray);
        myDBArray.setAdapter(arrayAdapter);
        myDB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Locations value = dataSnapshot.getValue(String.class);
                String key = dataSnapshot.getKey();
                myDBArray.add(value);
                myKeys.add(key);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Locations value = dataSnapshot.getValue(String.class);
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
        })
    }
}
