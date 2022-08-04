package model;

public class TrackModel {
    private final int trackID;
    private final String trackName;
    private final int length;
    private final int addressNumber;
    private final String street;
    private final String zipCode;

    public TrackModel(int trackID, String trackName, int length, int addressNumber, String street, String zipCode) {
        this.trackID = trackID;
        this.trackName = trackName;
        this.length = length;
        this.addressNumber = addressNumber;
        this.street = street;
        this.zipCode = zipCode;
    }

    public int getTrackID() {
        return trackID;
    }


    public String getTrackName() {
        return trackName;
    }

    public float getLength() {
        return length;
    }


    public int getAddressNumber() {
        return addressNumber;
    }


    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }
}
