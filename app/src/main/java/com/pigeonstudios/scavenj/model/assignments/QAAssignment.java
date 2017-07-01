package com.pigeonstudios.scavenj.model.assignments;

/**
 * Created by Dennis on 7/1/2017.
 */

public class QAAssignment extends Assignment {

    private String question;
    private String answer;

    public QAAssignment(int assignmentIdentifier, String question, String answer) {
        super(assignmentIdentifier);
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer(){
        return answer;
    }
}
