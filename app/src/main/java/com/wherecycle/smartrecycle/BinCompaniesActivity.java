package com.wherecycle.smartrecycle;
/*
* created by Lorraine
* This activity will show the user the details of bin companies that service their area
* They will be able to click the link to the website or click the call button to call the company
* */
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BinCompaniesActivity extends ChildActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin_companies);
        this.setTitle("Bin Companies");
        //code below sets up the links to the different websites
        TextView thorntons = (TextView) findViewById(R.id.thorntons);
        thorntons.setMovementMethod(LinkMovementMethod.getInstance());
        TextView panda = (TextView) findViewById(R.id.panda);
        panda.setMovementMethod(LinkMovementMethod.getInstance());
        TextView citybin = (TextView) findViewById(R.id.citybin);
        citybin.setMovementMethod(LinkMovementMethod.getInstance());
        TextView greyhound = (TextView) findViewById(R.id.greyhound);
        greyhound.setMovementMethod(LinkMovementMethod.getInstance());
        //code below sets up the call buttons
        Button callThorntons = (Button) findViewById(R.id.callThorntons);
        callThorntons.setOnClickListener(this);
        Button callPanda = (Button) findViewById(R.id.callPanda);
        callPanda.setOnClickListener(this);
        Button callCitybin = (Button) findViewById(R.id.callCitybin);
        callCitybin.setOnClickListener(this);
        Button callGreyhound = (Button) findViewById(R.id.callGreyhound);
        callGreyhound.setOnClickListener(this);
    }

    @Override
    /*using a switch statement to determine which number to call depending on the choice. Each intent is set to
    *move to the dialer when the call button is clicked. I chose to go to dialer rather than call directly as
    *the user may prefer to save the number in phonebook rather than call straight away*/
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.callThorntons):
                Intent thorntonsIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + "01 623 5133"));
                startActivity(thorntonsIntent);
                break;
            case (R.id.callPanda):
                Intent pandaIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + "1890 626 262"));
                startActivity(pandaIntent);
                break;
            case (R.id.callCitybin):
                Intent cityIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + "091 787 800"));
                startActivity(cityIntent);
                break;
            case (R.id.callGreyhound):
                Intent greyhoundIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + "1890 989 998"));
                startActivity(greyhoundIntent);
            }

        }


    }

