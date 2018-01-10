package com.wherecycle.smartrecycle;
/*
* created by Lorraine
* This activity is a horizontal scroll view. It will present the user with symbols
* related to recycling. When the user touches the image it will vibrate and bring up
* a short toast to explain what the symbol means*/
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class RecyclingSymbolsActivity extends ChildActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling_symbols);
        this.setTitle("Recycling Symbols");
        //code below sets click listerner on each image
        ImageView wood = (ImageView) findViewById(R.id.wood);
        wood.setOnClickListener(this);
        ImageView tidyman = (ImageView) findViewById(R.id.tidyman);
        tidyman.setOnClickListener(this);
        ImageView steel = (ImageView) findViewById(R.id.steel);
        steel.setOnClickListener(this);
        ImageView paper = (ImageView) findViewById(R.id.paper);
        paper.setOnClickListener(this);
        ImageView alusymbol = (ImageView) findViewById(R.id.alusymbol);
        alusymbol.setOnClickListener(this);
        ImageView greendot = (ImageView) findViewById(R.id.greendot);
        greendot.setOnClickListener(this);
        ImageView mob = (ImageView) findViewById(R.id.mobius);
        mob.setOnClickListener(this);
        ImageView glass = (ImageView) findViewById(R.id.glasssymbol);
        glass.setOnClickListener(this);
        ImageView nonhousehold = (ImageView) findViewById(R.id.nonhousehold);
        nonhousehold.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        final Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(100);//sets up the new vibrator to vibrate on each symbol click
        //using a simple switch statement to determine what is displayed on screen
        switch (view.getId()){
            case (R.id.alusymbol):
                Toast.makeText(this, "Indicates that aluminium packaging can be recycled", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.glasssymbol) :
                Toast.makeText(this, "Indicates that a glass product can be recycled if washed out and placed into a bottle bank", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.greendot) :
                Toast.makeText(this, "The producer of the packaging has contributed to the end recovery of packaging. It does not mean that the packaging is recyclable", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.mobius) :
                Toast.makeText(this, "Indicates that the product's packaging can be recycled", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.nonhousehold) :
                Toast.makeText(this, "Item should be disposed of separately from household waste", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.paper) :
                Toast.makeText(this, "Paper packaging can be recycled", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.steel) :
                Toast.makeText(this, "Steel can be recycled", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.tidyman) :
                Toast.makeText(this, "Do not litter", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.wood) :
                Toast.makeText(this, "Wood packaging has been sustainably sourced and may have come from recycled wood", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
