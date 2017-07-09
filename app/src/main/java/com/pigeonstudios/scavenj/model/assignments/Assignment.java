package com.pigeonstudios.scavenj.model.assignments;

/**
 * Supper class for all assignments
 * Created by Dennis on 7/1/2017.
 */
public abstract class Assignment {
    /**
     * A variable which will tell us what kind of assignment it is
     */
    private int assignmentType;
    private boolean answered;

    public Assignment(int assignmentType){
        this.assignmentType = assignmentType;
        this.answered = false;
    }

    public int getAssignmentIdentifier(){
        return assignmentType;
    }

    public boolean isAnswered(){
        return answered;
    }

    public void setAnswered(boolean answered){
        this.answered = answered;
    }

}
