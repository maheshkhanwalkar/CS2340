package edu.gatech.buzzshelter.model.data;

/**
 * Data element for map display
 */
public class DataElement
{
    private final String name;
    private final String description;
    private final Location location;

    /**
     * Initialize data element
     * @param name - name
     * @param description - description
     * @param location - location information
     */
    public DataElement(String name, String description, Location location)
    {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    /**
     * Get name
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get description
     * @return description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Get latitude
     * @return lat
     */
    public double getLatitude()
    {
        return location.getLatitude();
    }

    /**
     * Get longitude
     * @return long
     */
    public double getLongitude()
    {
        return location.getLongitude();
    }
}
