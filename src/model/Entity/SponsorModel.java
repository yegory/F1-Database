package model.Entity;

import model.EntityInterfaces.Sponsor;

public class SponsorModel implements Sponsor {
    private final int sponsorID;
    private final String name;

    public SponsorModel(int sponsorID, String name) {
        this.sponsorID = sponsorID;
        this.name = name;
    }

    @Override
    public int getSponsorID() {
        return sponsorID;
    }

    @Override
    public String getName() {
        return name;
    }
}
