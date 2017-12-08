package com.wherecycle.smartrecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.wherecycle.smartrecycle.adapters.MyRecyclerAdapter;
import com.wherecycle.smartrecycle.model.RecycleableItem;
import com.wherecycle.smartrecycle.model.RecycleableType;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MyRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* *//*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }*//*);*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
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
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false
        ));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.bottle_banks) {
            Intent intent = new Intent(MainActivity.this, DBLayoutActivity.class);
            startActivity(intent);

        } else if (id == R.id.bin_comapanies) {

        } else if (id == R.id.useful_links) {

        } else if (id == R.id.benefits) {

        } else if (id == R.id.not_recycle) {

        } else if (id == R.id.symbols) {

        } else if (id == R.id.settings) {

        }else if (id == R.id.about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
