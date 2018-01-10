package com.wherecycle.smartrecycle;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
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

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

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
    }

    private void init(){

    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available== ConnectionResult.SUCCESS){
            //EVERYTHING IS GOOD AND USER CAN MAKE MAP REQUEST.
            Log.d(TAG, "isServicesOk: Google Play Service is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //AN ERROR OCCURED BUT RESOLVABLE.
            Log.d(TAG, "isServicesOk: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.bottle_banks) {
            Intent dbintent = new Intent(MainActivity.this, DBLayoutActivity.class);
            startActivity(dbintent);

        } else if (id == R.id.bin_comapanies) {
            Intent binIntent = new Intent(MainActivity.this, binCompanies.class);
            startActivity(binIntent);

        } else if (id == R.id.useful_links) {
            Intent usefulIntent = new Intent(MainActivity.this, usefulLinksActivity.class);
            startActivity(usefulIntent);

        } else if (id == R.id.benefits) {
            Intent benefitsIntent = new Intent(MainActivity.this, BenefitsIfRecycling.class);
            startActivity(benefitsIntent);

        } else if (id == R.id.not_recycle) {
            Intent notRecycleIntent = new Intent(MainActivity.this, NotToRecycle.class);
            startActivity(notRecycleIntent);

        } else if (id == R.id.symbols) {
            Intent symbolIntent = new Intent(MainActivity.this, recycling_symbols.class);
            startActivity(symbolIntent);

        } else if (id == R.id.addRecycler) {
            Intent addIntent = new Intent(MainActivity.this, AddRecyclingActivity.class);
            startActivity(addIntent);

        } else if (id == R.id.settings) {

        }else if (id == R.id.about) {

        }
        else if (id == R.id.showall){

            if(isServicesOK()) {
                Intent mapIntent = new Intent(MainActivity.this, MapActivityShowAll.class);
                startActivity(mapIntent);
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
