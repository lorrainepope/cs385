package com.wherecycle.smartrecycle;
/*
* created by Lorraine
* This activity will display a scrollable list detailing some of the benefits of recycling*/
import android.os.Bundle;
//extends childActivity as we want a back button
public class BenefitsOfRecyclingActivity extends ChildActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benefits_of_recycling);
        this.setTitle("Benefits of Recycling");
    }
}
