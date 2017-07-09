package com.pigeonstudios.scavenj.view.fragments.assignments;

import android.net.Uri;
import android.support.v4.app.Fragment;

/**
 * Supper class that every fragment will implement. The reason for that is because we need the
 * listener for the submit button and this button needs to notify the activity that the fragment is in
 * Created by Dennis on 7/4/2017.
 */
public abstract class AssignmentFragment extends Fragment{
    private boolean answered;

    public AssignmentFragment(){
        this.answered = false;
    }

    public boolean isAnswered(){
        return answered;
    }

    public void setAnswered(boolean answered){
        this.answered = answered;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener{
        void onFragmentInteraction(Uri uri);
        public void onSubmitButtonPress();
        public void onAnswered();
    }

}
