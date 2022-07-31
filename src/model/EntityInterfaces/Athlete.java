package model.EntityInterfaces;

import java.text.SimpleDateFormat;

public interface Athlete {
    int getAthleteID();
    int getTeamID();
    String getFirstName();
    String getLastName();
    SimpleDateFormat getDOB();
    SimpleDateFormat getStartDate();
    SimpleDateFormat getEndDate();
    int getNRaces();
}
