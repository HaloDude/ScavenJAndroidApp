package com.pigeonstudios.scavenj.view.fragments.assignments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pigeonstudios.scavenj.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QAFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QAFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QAFragment extends Fragment {

    private static final String ARG_QUESTION = "No question passed as param";
    private static final String ARG_ANSWER= "";

    private String mQuestion;
    private String mAnswer;

    private OnFragmentInteractionListener mListener;

    public QAFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param question - The question to be displayed.
     * @param answer - The answer to be displayed.
     * @return A new instance of fragment QAFragment.
     */
    public static QAFragment newInstance(String question, String answer) {
        QAFragment fragment = new QAFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUESTION, question);
        args.putString(ARG_ANSWER, answer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mQuestion = getArguments().getString(ARG_QUESTION);
            mAnswer= getArguments().getString(ARG_ANSWER);
        }


    }

    /**
     * This method will set all the data there is to set by using the view we inflated
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_qa, container, false);

        //setting data
        TextView tv = (TextView)v.findViewById(R.id.QAQuestion);
        tv.setText(mQuestion);

        return v;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
