package com.wherecycle.smartrecycle;
/*
* created by Lorraine
* This activity allows the user to see some useful websites and
* they can click on the link to go to the website
* */
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

public class usefulLinksActivity extends ChildActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful_links);
        this.setTitle("Useful Links");
        TextView repak = (TextView) findViewById(R.id.re);
        repak.setMovementMethod(LinkMovementMethod.getInstance());//when the textbox is clicked it takes us to browser
        TextView epa = (TextView) findViewById(R.id.ePa);
        epa.setMovementMethod(LinkMovementMethod.getInstance());
        TextView ci = (TextView) findViewById(R.id.citizensText);
        ci.setMovementMethod(LinkMovementMethod.getInstance());
        TextView We = (TextView) findViewById(R.id.weeeText);
        We.setMovementMethod(LinkMovementMethod.getInstance());
    }


}

