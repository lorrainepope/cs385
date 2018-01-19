package com.wherecycle.smartrecycle;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.wherecycle.smartrecycle.adapters.MyRecyclerAdapter;
import com.wherecycle.smartrecycle.model.MapActivityShowAll;
import com.wherecycle.smartrecycle.model.RecycleableItem;
import com.wherecycle.smartrecycle.model.RecycleableType;

import java.util.ArrayList;
/*
* This still extends appCompat instead of child as we do not need a back button here and we want to have the
* drawer accessible from this screen*/
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }


    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    SharedPreferences sp;
    private final String fileName = "myFile";
    MyRecyclerAdapter adapter;//creates the new adapter that we need

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //set up the recyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mainRecycler);
        RecycleableItem item = new RecycleableItem(RecycleableType.Aluminium);
        RecycleableItem item1 = new RecycleableItem(RecycleableType.Textiles);
        RecycleableItem item2 = new RecycleableItem(RecycleableType.ScrapMetal);
        RecycleableItem item3 = new RecycleableItem(RecycleableType.Plastics);
        RecycleableItem item4 = new RecycleableItem(RecycleableType.Furniture);
        RecycleableItem item5 = new RecycleableItem(RecycleableType.Electronics);
        RecycleableItem item6 = new RecycleableItem(RecycleableType.Cardboard);
        RecycleableItem item7 = new RecycleableItem(RecycleableType.Batteries);
        RecycleableItem item8 = new RecycleableItem(RecycleableType.Glass);
        //create a new arrayList to hold each recyclableItem we have just created
        ArrayList<RecycleableItem> arrayList = new ArrayList<>(8);
        arrayList.add(item);
        arrayList.add(item1);
        arrayList.add(item2);
        arrayList.add(item3);
        arrayList.add(item4);
        arrayList.add(item5);
        arrayList.add(item6);
        arrayList.add(item7);
        arrayList.add(item8);
        recyclerView.setAdapter(new MyRecyclerAdapter(this, arrayList));
        //set the grid layout so that the items will be displayed properly on screen
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false
        ));
        //code below sets up the click listener for the items in recyclerview
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, final int position) {

                sp = getSharedPreferences(fileName, Context.MODE_PRIVATE);  //instantiate SharedPreferences
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("nameKey", position);                       //set value of nameKey equal to the position of the clicked recycler item
                editor.apply();
                if (isServicesOK()) { //only starts map activity if google service okay.
                    Intent intent = new Intent(MainActivity.this, MapActivityShowAll.class);    //jump to map activity
                    startActivity(intent);
                }
            }
            @Override
            public void onLongClick(View view, int position) {

            }

        }));
    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            //EVERYTHING IS GOOD AND USER CAN MAKE MAP REQUEST.
            Log.d(TAG, "isServicesOk: Google Play Service is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //AN ERROR OCCURED BUT RESOLVABLE.
            Log.d(TAG, "isServicesOk: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            //THE USER CANNOT MAKE MAP REQUESTS
            Toast.makeText(this, "You cant make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.bottle_banks) {
            Intent dbintent = new Intent(MainActivity.this, DBLayoutActivity.class);
            startActivity(dbintent);

        } else if (id == R.id.bin_comapanies) {
            Intent binIntent = new Intent(MainActivity.this, BinCompaniesActivity.class);
            startActivity(binIntent);

        } else if (id == R.id.useful_links) {
            Intent usefulIntent = new Intent(MainActivity.this, usefulLinksActivity.class);
            startActivity(usefulIntent);

        } else if (id == R.id.benefits) {
            Intent benefitsIntent = new Intent(MainActivity.this, BenefitsOfRecyclingActivity.class);
            startActivity(benefitsIntent);

        } else if (id == R.id.not_recycle) {
            Intent notRecycleIntent = new Intent(MainActivity.this, NotToRecycleActivity.class);
            startActivity(notRecycleIntent);

        } else if (id == R.id.symbols) {
            Intent symbolIntent = new Intent(MainActivity.this, RecyclingSymbolsActivity.class);
            startActivity(symbolIntent);

        } else if (id == R.id.addRecycler) {
            Intent addIntent = new Intent(MainActivity.this, AddRecyclingActivity.class);
            startActivity(addIntent);

        } else if (id == R.id.selectBinDay) {
            Intent addIntent = new Intent(MainActivity.this, SelectBinDayActivity.class);
            startActivity(addIntent);

        }else if (id == R.id.about) {
            Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(aboutIntent);


        } else if (id == R.id.showall) {

            if (isServicesOK()) {
                Intent mapIntent = new Intent(MainActivity.this, MapActivityShowAll.class);
                startActivity(mapIntent);
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;


        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {


        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}

