package com.pigeonstudios.scavenj.controller.fragmentcontroller;

import android.support.v4.app.Fragment;

import com.pigeonstudios.scavenj.view.fragments.assignments.PasswordFragment;
import com.pigeonstudios.scavenj.view.fragments.assignments.QAFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 6/26/2017.
 */

public class FragmentChangeController {

    private List<Fragment> fragments;
    private int amountOfFragments;

    private int currentFragment;
    private int previousFragment;

    public FragmentChangeController(int id){
        fragments = new ArrayList<>();
        this.currentFragment = 0;
        this.amountOfFragments = 0;
        this.previousFragment = 0;

        initialiseTheListOfAssignments(id);
    }


    public int initialiseTheListOfAssignments(long idOfAssignmentList){
        for(int i =0; i<idOfAssignmentList; i++){
            fragments.add(new QAFragment());
            fragments.add(new PasswordFragment());
        }

        amountOfFragments = fragments.size();
        return amountOfFragments;
    }

    public boolean switchToNextFragment(){
        if(currentFragment < amountOfFragments-1){
            previousFragment = currentFragment;
            currentFragment++;
            return true;
        }

        return false;
    }

    public boolean switchToPrevFragment(){
        if(currentFragment == amountOfFragments){
            currentFragment--;
        }

        if(currentFragment > 0) {
            previousFragment = currentFragment;
            currentFragment--;
            return true;
        }

        return false;
    }

    public Fragment getCurrentFragment(){
        return fragments.get(currentFragment);
    }

    public Fragment getSwitchFragment(){
        return  fragments.get(previousFragment);
    }

    public int getAssignmentAmount(){
        return  this.amountOfFragments;
    }

    public int getCurrentFragmentNumber(){
        return currentFragment;
    }
}
