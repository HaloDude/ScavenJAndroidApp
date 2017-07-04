package com.pigeonstudios.scavenj.view.fragments.assignments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pigeonstudios.scavenj.R;
import com.pigeonstudios.scavenj.view.activities.ScavenJAssignmentHolder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QAFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QAFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QAFragment extends AssignmentFragment {

    private static final String ARG_QUESTION = "No question passed as param";
    private static final String ARG_ANSWER= "";

    private String mQuestion;
    private String mAnswer;
    private Button submit;
    private Context context;
    private EditText answer;
    private CardView answerCard;

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

        //TODO get the stuff from the save to build the fragment
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
        final View view = inflater.inflate(R.layout.fragment_qa, container, false);

        answerCard = (CardView) view.findViewById(R.id.QAFragment_answer_card_view) ;

        //get the button
        submit = (Button) view.findViewById(R.id.QAFragment_submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String s = answer.getText().toString();

                    if(s.equals(mAnswer)){ //if the answer is correct
                        answer.setEnabled(false);
                        submit.setEnabled(false);
                        answer.getBackground().clearColorFilter();
                        ((ScavenJAssignmentHolder) getActivity()).onSubmitButtonPress();
                    } else { //if the answer is not correct
                        answer.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                        answerCard.setAnimation(AnimationUtils.loadAnimation(context, R.anim.wrong_answer_shake));
                    }

                } catch (ClassCastException e){

                }
            }
        });

        //setting data
        TextView tv = (TextView)view.findViewById(R.id.QAQuestion);
        tv.setText(mQuestion);

        //get the answer text box
        answer = (EditText) view.findViewById(R.id.QAFragment_answer);
        answer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int result = actionId & EditorInfo.IME_MASK_ACTION;
                if (result == EditorInfo.IME_ACTION_DONE){
                    submit.performClick();
                }

                return false;
            }
        });



        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;

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

    //TODO implement this for fragment destroy and create
    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
