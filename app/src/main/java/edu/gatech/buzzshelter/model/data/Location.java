package edu.gatech.buzzshelter.model.data;

/**
 * Location information
 */
public class Location
{
    private final double latitude;
    private final double longitude;

    /**
     * Initialize location
     * @param lat - latitude
     * @param lon - longitude
     */
    public Location(double lat, double lon)
    {
        latitude = lat;
        longitude = lon;
    }

    /**
     * Get latitude
     * @return lat
     */
    public double getLatitude()
    {
        return latitude;
    }

    /**
     * Get longitude
     * @return long
     */
    public double getLongitude()
    {
        return longitude;
    }
}