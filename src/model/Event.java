package model;

import java.sql.Date;

public record Event(int eventID, int trackID, Date date, String name, int laps) {
}
