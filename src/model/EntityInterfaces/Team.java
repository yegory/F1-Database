package model.EntityInterfaces;

import java.text.SimpleDateFormat;

public interface Team {
    int getTeamID();
    int getDirectorID();
    String getTeamName();
    SimpleDateFormat getStartDate();
    SimpleDateFormat getEndDate();
    int getPoints();
}