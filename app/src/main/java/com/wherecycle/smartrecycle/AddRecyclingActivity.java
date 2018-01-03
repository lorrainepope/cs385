package com.wherecycle.smartrecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wherecycle.smartrecycle.model.Locations;

import javax.microedition.khronos.egl.EGLDisplay;

public class AddRecyclingActivity extends ChildActivity {
    private DatabaseReference myDB;
    private EditText eName;
    private EditText eContact;
    private EditText eLat;
    private EditText eLong;
    private Button submit;
    private RadioButton eAlu;
    private RadioButton eBatt;
    private RadioButton eCard;
    private RadioButton eElec;
    private RadioButton eFurn;
    private RadioButton eGlass;
    private RadioButton eMetal;
    private RadioButton ePlast;
    private RadioButton eText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recycling);

        myDB = FirebaseDatabase.getInstance().getReference();


        eName = (EditText)findViewById(R.id.enterRName);
        eContact = (EditText)findViewById(R.id.enterRContact);
        eLat = (EditText)findViewById(R.id.enterRLat);
        eLong = (EditText)findViewById(R.id.enterRLong);
        eAlu = (RadioButton)findViewById(R.id.radioAlum);
        eBatt = (RadioButton)findViewById(R.id.radioBatt);
        eCard = (RadioButton)findViewById(R.id.radioCard);
        eElec = (RadioButton)findViewById(R.id.radioElect);
        eFurn = (RadioButton)findViewById(R.id.radioFurn);
        eGlass = (RadioButton)findViewById(R.id.radioGlass);
        eMetal = (RadioButton)findViewById(R.id.radioMetal);
        ePlast = (RadioButton)findViewById(R.id.radioPlastics);
        eText = (RadioButton)findViewById(R.id.radioText);

        submit = (Button)findViewById(R.id.submitRecycling);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String DBName = eName.getText().toString();
                String DBContact = eContact.getText().toString();
                String strLat = eLat.getText().toString();
                double DBLat = Double.parseDouble(strLat);
                String strLong = eLong.getText().toString();
                double DBLong = Double.parseDouble(strLong);
                boolean DBAlu = eAlu.isChecked();
                boolean DBBatt = eBatt.isChecked();
                boolean DBCard = eCard.isChecked();
                boolean DBElec = eElec.isChecked();
                boolean DBFurn = eFurn.isChecked();
                boolean DBGlass = eGlass.isChecked();
                boolean DBMetal = eMetal.isChecked();
                boolean DBPlast = ePlast.isChecked();
                boolean DBText = eText.isChecked();

                Locations newRecycler = new Locations(DBName, DBLat, DBLong, DBContact, DBAlu, DBBatt, DBCard, DBElec, DBFurn, DBGlass, DBMetal, DBPlast, DBText);
                myDB.child("Recycling Centres").push().setValue(newRecycler);

                Intent intent = new Intent(AddRecyclingActivity.this, DBLayoutActivity.class);
                startActivity(intent);
            }
        });
    }
}
