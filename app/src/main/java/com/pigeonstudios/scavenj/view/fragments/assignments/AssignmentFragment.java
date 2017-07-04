package com.pigeonstudios.scavenj.view.fragments.assignments;

import android.support.v4.app.Fragment;

/**
 * Supper class that every fragment will implement. The reason for that is because we need the
 * listener for the submit button and this button needs to notify the activity that the fragment is in
 * Created by Dennis on 7/4/2017.
 */
public abstract class AssignmentFragment extends Fragment {

    public interface onSubmitListener{
        public void onSubmitButtonPress();
    }
}
