package com.pigeonstudios.scavenj.view.activities;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.pigeonstudios.scavenj.R;
import com.pigeonstudios.scavenj.view.fragments.assignments.QAFragment;

public class ScavenJAssignmentHolder extends AppCompatActivity implements QAFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scavenj_assignment_holder);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //get next button to switch to next fragment
        Button next = (Button)findViewById(R.id.next_assignment);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                QAFragment f = new QAFragment();
                ft.replace(R.id.assignment_fragment_placeholder, f);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
