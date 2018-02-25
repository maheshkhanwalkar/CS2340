package edu.gatech.buzzshelter.model.user;

public class Shelter
{
    /* Shelter information */
    private int key, capacity;
    private String name, restrict;
    private double latitude, longitude;
    private String address, notes, phone;

    /* Construct Shelter object */
    public Shelter(int key, int capacity,
        String name, String restrict, double latitude, double longitude,
        String address, String notes, String phone)
    {
        this.key = key;
        this.capacity = capacity;
        this.name = name;
        this.restrict = restrict;
        this.latitude = latitude;
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

    public int getCapacity()
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
