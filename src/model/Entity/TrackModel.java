package model.Entity;
import model.EntityInterfaces.Track;

public class TrackModel implements Track {
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
        this. addressNumber = addressNumber;
        this.street = street;
        this.zipCode = zipCode;
    }
    @Override
    public int getTrackID() {
        return trackID;
    }

    @Override
    public String getTrackName() {
        return trackName;
    }

    @Override
    public float getLength() {
        return length;
    }

    @Override
    public int getAddressNumber() {
        return addressNumber;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public String getZipCode() {
        return zipCode;
    }
}
