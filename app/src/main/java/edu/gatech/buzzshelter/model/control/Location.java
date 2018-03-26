package edu.gatech.buzzshelter.model.control;

/**
 * Created by Kyle on 3/24/2018.
 */

public class Location {

    private double latitude;
    private double longitude;

    public Location (double lat, double lon) {
        latitude = lat;
        longitude = lon;
    }

    public double getLatitude(){return latitude;}

    public double getLongitude(){return longitude;}
}
