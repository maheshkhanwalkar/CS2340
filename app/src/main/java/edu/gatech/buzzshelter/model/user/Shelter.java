package edu.gatech.buzzshelter.model.user;

public class Shelter
{
    /* Shelter information */
    private int key;
    private String name, restrict, capacity;
    private double latitude, longitude;
    private String address, notes, phone;

    /* Construct Shelter object */
    public Shelter(int key, String name, String capacity, String restrict,
         double longitude, double latitude, String address, String notes, String phone)
    {
        this.key = key;
        this.capacity = capacity;
        this.name = name;
        this.restrict = restrict;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.notes = notes;
        this.phone = phone;
    }


    /* TODO add variables */

    /* Getters */
    public int getKey()
    {
        return key;
    }

    public String getCapacity()
    {
        return capacity;
    }

    public String getName()
    {
        return name;
    }

    public String getRestrict()
    {
        return restrict;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public String getAddress()
    {
        return address;
    }

    public String getNotes()
    {
        return notes;
    }

    public String getPhone()
    {
        return phone;
    }
}
