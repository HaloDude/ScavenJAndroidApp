package com.pigeonstudios.scavenj.model.assignments;

/**
 * Supper class for all assignments
 * Created by Dennis on 7/1/2017.
 */
public abstract class Assignment {
    /**
     * A variable which will tell us what kind of assignment it is
     */
    private int assignmentIdentifier;

    public Assignment(int assignmentIdentifier){
        this.assignmentIdentifier = assignmentIdentifier;
    }

    public int getAssignmentIdentifier(){
        return assignmentIdentifier;
    }
}
