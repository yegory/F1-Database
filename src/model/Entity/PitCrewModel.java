package model.Entity;

import java.text.SimpleDateFormat;

public record PitCrewModel(int pitCrewID, String role, String firstName, String lastName, int teamID, SimpleDateFormat endDate) {

}
