package edu.gatech.buzzshelter.model.data;

public class DataElement
{
    private final String name;
    private final String description;
    private final Location location;

    public DataElement(String name, String description, Location location)
    {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public double getLatitude()
    {
        return location.getLatitude();
    }

    public double getLongitude()
    {
        return location.getLongitude();
    }
}
