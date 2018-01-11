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
    //private final ArrayList<Locations> myDBArray = new ArrayList<>();
    private int nEle = 0;
    private ListView myDBList;
    private ArrayList<String> myKeys = new ArrayList<>();
    private LocationsAdapter arrayAdapter;
    SharedPreferences sp;
    private final String fileName = "myFile";
    private int recyc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dblayout);

        sp = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        recyc = sp.getInt("nameKey", -1);

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
                    if (recyc == "A") {
                        if (locations.isAlumin()) {myDBArray.add(locations);}
                    }
                    else if (recyc == "B") {
                        if (locations.isBatteries()) {myDBArray.add(locations);}
                    }
                    else if (recyc == "C") {
                        if (locations.isCardboard()) {myDBArray.add(locations);}
                    }
                    else if (recyc == "E") {
                        if (locations.isElectronics()) {myDBArray.add(locations);}
                    }
                    else if (recyc == "F") {
                        if (locations.isFurniture()) {myDBArray.add(locations);}
                    }
                    else if (recyc == "G") {
                        if (locations.isGlass()) {myDBArray.add(locations);}
                    }
                    else if (recyc == "M") {
                        if (locations.isMetal()) {myDBArray.add(locations);}
                    }
                    else if (recyc == "P") {
                        if (locations.isPlastics()) {myDBArray.add(locations);}
                    }
                    else if (recyc == "T") {
                        if (locations.isTextiles()) {myDBArray.add(locations);}
                    }

                    else{myDBArray.add(locations);}
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

