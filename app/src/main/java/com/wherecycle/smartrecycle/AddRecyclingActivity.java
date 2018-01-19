package com.wherecycle.smartrecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wherecycle.smartrecycle.model.Locations;

public class AddRecyclingActivity extends ChildActivity {
    private DatabaseReference myDB;                             //declaring the various variables needed
    private EditText eName, eEmail, ePhone, eLat, eLong;
    private CheckBox eAlu, eBatt, eCard, eElec, eFurn, eGlass, eMetal, ePlast, eText;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Add Recycling Facility");
        setContentView(R.layout.activity_add_recycling);

        myDB = FirebaseDatabase.getInstance().getReference();

        eName = (EditText)findViewById(R.id.enterRName);        //linking the variables to the xml widgets
        eEmail = (EditText)findViewById(R.id.enterREmail);
        ePhone = (EditText)findViewById(R.id.enterRPhone);
        eLat = (EditText)findViewById(R.id.enterRLat);
        eLong = (EditText)findViewById(R.id.enterRLong);
        eAlu = (CheckBox) findViewById(R.id.checkAlu);
        eBatt = (CheckBox) findViewById(R.id.checkBatt);
        eCard = (CheckBox) findViewById(R.id.checkCard);
        eElec = (CheckBox) findViewById(R.id.checkElec);
        eFurn = (CheckBox) findViewById(R.id.checkFurn);
        eGlass = (CheckBox) findViewById(R.id.checkGlass);
        eMetal = (CheckBox) findViewById(R.id.checkMetal);
        ePlast = (CheckBox) findViewById(R.id.checkPlas);
        eText = (CheckBox) findViewById(R.id.checkTexti);

        submit = (Button)findViewById(R.id.submitRecycling);        //on click we String all the text entries
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String DBName = eName.getText().toString();
                String DBEmail = eEmail.getText().toString();
                String DBPhone = ePhone.getText().toString();
                String strLat = eLat.getText().toString();
                double DBLat = Double.parseDouble(strLat);          //and parse the latitude and longitude into doubles
                String strLong = eLong.getText().toString();
                double DBLong = Double.parseDouble(strLong);
                boolean DBAlu = eAlu.isChecked();                   //the checkboxes become booleans.
                boolean DBBatt = eBatt.isChecked();
                boolean DBCard = eCard.isChecked();
                boolean DBElec = eElec.isChecked();
                boolean DBFurn = eFurn.isChecked();
                boolean DBGlass = eGlass.isChecked();
                boolean DBMetal = eMetal.isChecked();
                boolean DBPlast = ePlast.isChecked();
                boolean DBText = eText.isChecked();                 //these variables are psuhed into the constructor below

                Locations newRecycler = new Locations(DBName, DBLat, DBLong, DBPhone, DBEmail, DBAlu, DBBatt, DBCard, DBElec, DBFurn, DBGlass, DBMetal, DBPlast, DBText);
                myDB.child("Recycling Centres").push().setValue(newRecycler);   //and here the new object is pushed to the database

                Toast.makeText(AddRecyclingActivity.this, "Database has been updated.", Toast.LENGTH_SHORT).show();
                eName.setText("");                                      //the following lines empty the widgets so that you may type anew
                eEmail.setText("");
                ePhone.setText("");
                eLat.setText("");
                eLong.setText("");
                if(eAlu.isChecked()){eAlu.setChecked(false);}
                if(eBatt.isChecked()){eBatt.setChecked(false);}
                if(eCard.isChecked()){eCard.setChecked(false);}
                if(eElec.isChecked()){eElec.setChecked(false);}
                if(eFurn.isChecked()){eFurn.setChecked(false);}
                if(eGlass.isChecked()){eGlass.setChecked(false);}
                if(eMetal.isChecked()){eMetal.setChecked(false);}
                if(ePlast.isChecked()){ePlast.setChecked(false);}
                if(eText.isChecked()){eText.setChecked(false);}
            }
        });
    }
}
