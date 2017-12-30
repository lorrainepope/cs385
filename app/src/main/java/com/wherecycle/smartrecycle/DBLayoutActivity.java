package com.wherecycle.smartrecycle;

import android.content.Intent;
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

public class DBLayoutActivity extends AppCompatActivity {
    private DatabaseReference myDB;
    //private final ArrayList<Locations> myDBArray = new ArrayList<>();
    private int nEle = 0;
    private ListView myDBList;
    private ArrayList<String> myKeys = new ArrayList<>();
    private LocationsAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dblayout);

        myDB = FirebaseDatabase.getInstance().getReference(); //.child("Recycling Centres");
        myDBList = (ListView)findViewById(R.id.DBList);
        final ArrayList<Locations> myDBArray = new ArrayList<>();


        myDB.child("Recycling Centres").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    Locations locations = child.getValue(Locations.class);
                    nEle++;
                    myDBArray.add(locations);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DBLayoutActivity.this, "Database Read Failed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DBLayoutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        arrayAdapter = new LocationsAdapter(this, R.layout.list_locations, myDBArray);
        myDBList.setAdapter(arrayAdapter);
    }
}

