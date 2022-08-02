package model.Entity;

import java.util.Date;

public record Athlete(int athleteID, int teamID, String firstName, String lastName, Date dateOfBirth, int nRaces, Date startDate, Date endDate) {
}
