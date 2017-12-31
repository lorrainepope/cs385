package com.wherecycle.smartrecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NotToRecycle extends ChildActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_to_recycle);
        this.setTitle("What Not To Recycle");
    }
}
