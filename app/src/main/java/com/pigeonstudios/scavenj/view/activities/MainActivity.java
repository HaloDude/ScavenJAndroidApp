package com.pigeonstudios.scavenj.view.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.pigeonstudios.scavenj.R;
import com.pigeonstudios.scavenj.controller.AvailableScavenJAdapter;
import com.pigeonstudios.scavenj.model.ScavenJCard;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //================================Recycler View=========================================//
        //get recycler view that will have cards for the scavenger hunts that are available
        recyclerView = (RecyclerView)findViewById(R.id.availableScavenJ);
        //because recycler view will have fixed size, do this to increase performance
        recyclerView.setHasFixedSize(true);

        //Create a layout manager for the cards
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //set manager to recycler view
        recyclerView.setLayoutManager(linearLayoutManager);

        //temp list of cards
        List<ScavenJCard> cards = new ArrayList<>();
        cards.add(new ScavenJCard("Name", "Bla bla bla", R.drawable.ic_menu_gallery));
        cards.add(new ScavenJCard("Name", "Bla bla bla", R.drawable.ic_menu_gallery));
        cards.add(new ScavenJCard("Name", "Bla bla bla", R.drawable.ic_menu_gallery));
        cards.add(new ScavenJCard("Name", "Bla bla bla", R.drawable.ic_menu_gallery));


        AvailableScavenJAdapter adapter = new AvailableScavenJAdapter(cards);
        recyclerView.setAdapter(adapter);





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
        getMenuInflater().inflate(R.menu.navigation, menu);
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

        if (id == R.id.profile) {
            // Handle the camera action
        } else if (id == R.id.search_scavenj) {

        } else if (id == R.id.create_scavenj) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
