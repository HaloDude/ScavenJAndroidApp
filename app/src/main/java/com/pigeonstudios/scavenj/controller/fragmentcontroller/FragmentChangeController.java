package com.pigeonstudios.scavenj.controller.fragmentcontroller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pigeonstudios.scavenj.view.fragments.assignments.PasswordFragment;
import com.pigeonstudios.scavenj.view.fragments.assignments.QAFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a fragment change controller that extends FragmentPagerAdapter.
 * This class is designed to:
 * <ul><li>
 * 1. Reconstruct an appropriate fragment from the database
 *
 * </li><li>
 * 2. Inform if the switch to the next fragment is possible
 *
 * </li><li>
 * 3. Return a reconstructed fragment for the pager in the above activity which uses this adapter
 * Created by Dennis on 6/26/2017.
 * </li></ul>
 */
public class FragmentChangeController extends FragmentPagerAdapter{

    private List<Integer> assignments;

    private List<Fragment> fragments;
    private int amountOfFragments;

    private int currentFragment;
    private int previousFragment;

    public FragmentChangeController(FragmentManager fragmentManager, long id){
        super(fragmentManager);
        assignments = new ArrayList<>();

        fragments = new ArrayList<>();
        this.currentFragment = 0;
        this.amountOfFragments = 0;
        this.previousFragment = 0;

        initialiseTheListOfAssignments(id);
    }

    /**
     * This method will get the list of assignments from the database of a scavenger hunt with a specific id
     * @param idOfAssignmentList - id of the scavenger hunt assignment list that needs to be loaded
     */
    private void initialiseTheListOfAssignments(long idOfAssignmentList){

        //get the id of the assignment list that was passed.
        if(idOfAssignmentList == 0){
            assignments.add(2);
            assignments.add(1);
            assignments.add(2);
            assignments.add(1);
            assignments.add(2);
            assignments.add(1);
            assignments.add(2);
            assignments.add(1);
        } else {
            assignments.add(2);
            assignments.add(1);
            assignments.add(2);
            assignments.add(1);
        }
        amountOfFragments = assignments.size();
    }

    /**
     * This method check if a switch to the next fragment is possible. I.E. it checks if there are
     * more assignments available in the list by checking if our current position in the list is
     * within the bounds of the assignment list. If possible then switches the position of the
     * current pointer one position forward
     * @return - true if switch is possible, false if not possible
     */
    public boolean switchToNextFragment(){
        if(currentFragment < amountOfFragments-1){
            currentFragment++;
            return true;
        }

        return false;
    }

    /**
     * Checks if switch to previous fragment is possible by checking if the current position in the
     * list is within bounds of the assignment list. If possible then switches the position of the
     * current pointer one position back
     * @return - true if switch back is possible, false if not
     */
    public boolean switchToPrevFragment(){
        if(currentFragment == amountOfFragments){
            currentFragment--;
        }

        if(currentFragment > 0) {
            currentFragment--;
            return true;
        }

        return false;
    }

    /**
     * Returns the current fragment position in the list.
     * @return - current fragment position
     */
    public int getCurrentFragmentNumber(){
        return currentFragment;
    }

    /**
     * This method looks through the list of assignments that was received from the database.
     * Depending on the id of the assignment that was requested from the specific position in the list,
     * the method will generate and return an appropriate fragment
     * @param position - position in the list of assignments
     * @return - a fragment or null
     */
    @Override
    public Fragment getItem(int position) {
        switch(assignments.get(position)){
            case 1:
                return new QAFragment();
            case 2:
                return new PasswordFragment();
        }
        return null;
    }

    /**
     * Returns the amount of elements in the assignment list.
     * @return - size of list
     */
    @Override
    public int getCount() {
        return assignments.size();
    }
}
