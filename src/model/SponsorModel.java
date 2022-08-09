package model;

public class SponsorModel {
    private final int sponsorID;
    private final String name;

    public SponsorModel(int sponsorID, String name) {
        this.sponsorID = sponsorID;
        this.name = name;
    }

    public int getSponsorID() {
        return sponsorID;
    }

    public String getName() {
        return name;
    }
}
