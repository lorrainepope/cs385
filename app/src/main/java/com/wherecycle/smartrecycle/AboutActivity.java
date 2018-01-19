package com.wherecycle.smartrecycle;

import android.os.Bundle;

public class AboutActivity extends ChildActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        this.setTitle("About");

    }
}
