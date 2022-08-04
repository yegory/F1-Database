package model;

import java.util.Date;

public record Event(int eventID, int trackID, Date date, String name, int laps) {
}
