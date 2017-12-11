package com.wherecycle.smartrecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

import javax.microedition.khronos.egl.EGLDisplay;

public class AddRecyclingActivity extends AppCompatActivity {
    private DatabaseReference myDB;
    private EditText eName;
    private EditText eContact;
    private EditText eLat;
    private EditText eLong;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recycling);


    }
}
