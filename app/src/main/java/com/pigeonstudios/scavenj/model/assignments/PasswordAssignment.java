package com.pigeonstudios.scavenj.model.assignments;

/**
 * Created by Dennis on 7/1/2017.
 */
public class PasswordAssignment extends Assignment {
    private String question;
    private String answer;

    public PasswordAssignment(int assignmentIdentifier) {
        super(assignmentIdentifier);
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
