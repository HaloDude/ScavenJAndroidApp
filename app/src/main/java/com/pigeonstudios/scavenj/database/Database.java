package com.pigeonstudios.scavenj.database;

import com.pigeonstudios.scavenj.model.assignments.Assignment;
import com.pigeonstudios.scavenj.model.assignments.QAAssignment;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will access the database and create a list of assignments for
 * the view to use to reconstruct assignments
 * Created by Dennis on 7/1/2017.
 */
public abstract class Database {

    /**
     * Method to retrieve and rebuild the list of assignments from the server. The request will be placed
     * and ideally a response will be received with JSON. This method will parse the response
     * and build the list.
     * @param scavenJPositionOnScreen - position of the scavenJ
     * @return - List of assignments for a specific scavenJ
     */
    public static List<Assignment> getListOfAssignments(int scavenJPositionOnScreen){
        List<Assignment> a = new ArrayList<>();

        //use the id to get the appropriate list from the database
        if(scavenJPositionOnScreen == 0){
            a.add(new QAAssignment(1, "How old am I?", "21"));
        } else if(scavenJPositionOnScreen == 5){
            a.add(new QAAssignment(1, "How old am I?", "21"));
            a.add(new QAAssignment(1, "Who Am I?", "Dennis"));
        } else {
            a.add(new QAAssignment(1, "How old am I?", "21"));
            a.add(new QAAssignment(1, "Who Am I?", "Dennis"));
            a.add(new QAAssignment(1, "How did I get here", "Bus"));
        }

        return  a;

    }

}
