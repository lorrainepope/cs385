package com.wherecycle.smartrecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class DBLayoutActivity extends AppCompatActivity {
    private DatabaseReference myDB;
    private ArrayList<> myDBArray = new ArrayList<>();
    private ListView myDBList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dblayout);
    }
}
