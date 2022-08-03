package model.Entity;

import model.EntityInterfaces.PitCrew;

import java.util.ArrayList;

public class PitCrewModel extends Entity {
    private Integer pitCrewID;
    private String role;
    private String firstName;
    private String lastName;
    private Integer teamID;
    private String startDate;
    private String endDate;

    public PitCrewModel(int pitCrewID, String role, String firstName, String lastName, int teamID, String startDate, String endDate) {
        tableName = "PitCrew";
        this.pitCrewID = pitCrewID;
        primaryKey = this.pitCrewID.toString();
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teamID = teamID;
        this.startDate = startDate;
        this.endDate=endDate;
        addAttributes();
    }

    private void addAttributes() {
        attributes = new ArrayList<>();
        attributes.add(pitCrewID.toString());
        attributes.add(role);
        attributes.add(firstName);
        attributes.add(lastName);
        attributes.add(teamID.toString());
        attributes.add(startDate);
        attributes.add(endDate);
    }

    public Integer getPitCrewID() {
        return pitCrewID;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getTeamID() {
        return teamID;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}