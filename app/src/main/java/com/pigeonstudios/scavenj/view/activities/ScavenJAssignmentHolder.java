package com.pigeonstudios.scavenj.view.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.pigeonstudios.scavenj.R;
import com.pigeonstudios.scavenj.controller.fragmentcontroller.FragmentChangeController;

public class ScavenJAssignmentHolder extends AppCompatActivity{

    private FragmentChangeController changeController;

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

        Intent intent = getIntent();
        int id = intent.getIntExtra("ID", 0);
        this.changeController = new FragmentChangeController(id);

        final SeekBar progressBar = (SeekBar)findViewById(R.id.assignmentProgressBar);
        progressBar.setClickable(false);
        progressBar.setMax(changeController.getAssignmentAmount() - 1);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment f = changeController.getCurrentFragment();
        ft.add(R.id.assignment_fragment_placeholder, f, null);
        ft.commit();

        //get next button to switch to next fragment
        ImageButton next = (ImageButton)findViewById(R.id.next_assignment);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                if(changeController.switchToNextFragment()) {
                    Fragment f = changeController.getCurrentFragment();
                    if (f != null) {
                        if (f.isAdded()) {
                            ft.hide(changeController.getSwitchFragment());
                            ft.show(f);
                        } else {
                            ft.hide(changeController.getSwitchFragment());
                            ft.add(R.id.assignment_fragment_placeholder, f, null);
                        }
                        progressBar.setProgress(changeController.getCurrentFragmentNumber());
                        ft.commit();
                    }
                }
            }
        });

        ImageButton prev = (ImageButton)findViewById(R.id.previous_assignment);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                if(changeController.switchToPrevFragment()) {
                    Fragment f = changeController.getCurrentFragment();
                    if (f != null) {
                        if (f.isAdded()) {
                            ft.hide(changeController.getSwitchFragment());
                            ft.show(f);
                        } else {
                            ft.hide(changeController.getSwitchFragment());
                            ft.add(R.id.assignment_fragment_placeholder, f, null);
                        }
                        progressBar.setProgress(changeController.getCurrentFragmentNumber());
                        ft.commit();
                    }
                }
            }
        });
    }

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
