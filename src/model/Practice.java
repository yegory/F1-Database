package model;

import java.sql.Time;

public record Practice(int eventID, int trackID, Time bestLap) {
}
