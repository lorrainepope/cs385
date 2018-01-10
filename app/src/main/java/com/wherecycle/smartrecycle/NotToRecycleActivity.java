package com.wherecycle.smartrecycle;
/*
* created by Lorraine
* This activity simply displays information on what is not accepted in a household recycling bin*/
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//extends child activity as we want it to have a back button on the action bar
public class NotToRecycleActivity extends ChildActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_to_recycle);
        this.setTitle("What Not To Recycle");
    }
}
