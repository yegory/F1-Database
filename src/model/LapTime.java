package model;

import java.sql.Time;

public record LapTime(int lapNumber, int resultID, Time time) {
}
