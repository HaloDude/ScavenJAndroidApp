package com.pigeonstudios.scavenj.view.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.pigeonstudios.scavenj.R;
import com.pigeonstudios.scavenj.controller.fragmentcontroller.FragmentChangeController;

public class ScavenJAssignmentHolder extends AppCompatActivity{

    private FragmentChangeController changeController;
    private ViewPager pager;

    public ScavenJAssignmentHolder(){

    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scavenj_assignment_holder);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //get the intent that brought us here and get the id value of a scavange that was passed to us
        Intent intent = getIntent();
        long id = intent.getLongExtra("ID", 0);
        this.changeController = new FragmentChangeController(getSupportFragmentManager(), id);

        this.pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(changeController);

        final SeekBar progressBar = (SeekBar)findViewById(R.id.assignmentProgressBar);
        progressBar.setOnTouchListener(new View.OnTouchListener() { //disable touch recognition
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        progressBar.setMax(changeController.getCount() - 1);

        //get next button to switch to next fragment
        ImageButton next = (ImageButton)findViewById(R.id.next_assignment);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                if(changeController.switchToNextFragment()) {
                    pager.setCurrentItem(changeController.getCurrentFragmentNumber());
                    progressBar.setProgress(changeController.getCurrentFragmentNumber());
                }
            }
        });

        ImageButton prev = (ImageButton)findViewById(R.id.previous_assignment);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                if (changeController.switchToPrevFragment()) {
                    pager.setCurrentItem(changeController.getCurrentFragmentNumber());
                    progressBar.setProgress(changeController.getCurrentFragmentNumber());
                }
            }
        });

    }

    /**
     * This is a method that gives the activity an ability to have the back button pressed that is
     * on the top right corner in the app bar
     * @param item - the arrow thing on top right
     * @return - I got no clue what it returns. Look in super class. It a bool so you got a 50/50 idk
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            //save stuff from this activity somehow
            //TODO save the current assignment stuff
            finish(); // back out from here
            overridePendingTransition(R.anim.hold, R.anim.slide_out);
        }

        return super.onOptionsItemSelected(item);
    }


}
