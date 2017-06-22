package com.pigeonstudios.scavenj.view.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.pigeonstudios.scavenj.R;
import com.pigeonstudios.scavenj.controller.recyclerviewcontroller.AvailableScavenJAdapter;
import com.pigeonstudios.scavenj.controller.recyclerviewcontroller.OnLoadMoreListener;
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
        recyclerView = (RecyclerView)findViewById(R.id.scavenJRecyclerView);
        //because recycler view will have fixed size, do this to increase performance
        recyclerView.setHasFixedSize(true);

        //Create a layout manager for the cards
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //set manager to recycler view
        recyclerView.setLayoutManager(linearLayoutManager);

        //temp list of cards
        final List<ScavenJCard> cards = new ArrayList<>();
        cards.add(new ScavenJCard("Name", "Bla bla bla", R.drawable.ic_menu_gallery));
        cards.add(new ScavenJCard("Name", "Bla bla bla", R.drawable.ic_menu_gallery));
        cards.add(new ScavenJCard("Name", "Bla bla bla", R.drawable.ic_menu_gallery));
        cards.add(new ScavenJCard("Name", "Bla bla bla", R.drawable.ic_menu_gallery));


        final AvailableScavenJAdapter adapter = new AvailableScavenJAdapter(cards, recyclerView, this);
        recyclerView.setAdapter(adapter);

        //set load more listener
        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if(cards.size() <= 20){
                    cards.add(null);
                    adapter.notifyItemInserted(cards.size() - 1); //insert the loading bar
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            //remove loading bar
                            cards.remove(cards.size() - 1);
                            adapter.notifyItemRemoved(cards.size());

                            //generate more data here
                            int index = cards.size();
                            int end = index + 5;
                            for (int i = index; i < end; i++) {
                                cards.add(new ScavenJCard("Name" + String.valueOf(i), "Desccccc", R.drawable.ic_menu_gallery));
                            }

                            adapter.notifyDataSetChanged();
                            adapter.setLoaded();
                        }
                    }, 3000);
                } else {
                    Toast.makeText(MainActivity.this, "No more data to load", Toast.LENGTH_SHORT).show();
                }
            }
        });





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
