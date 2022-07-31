package model.EntityInterfaces;

import java.text.SimpleDateFormat;

public interface Event {
    int getEventID();
    int getTrackID();
    SimpleDateFormat getDate();
    int getLaps();
}