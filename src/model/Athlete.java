package model;

import java.sql.Date;

public record Athlete(int athleteID, int teamID, String firstName, String lastName, Date dateOfBirth, int nRaces, Date startDate, Date endDate) {
}
