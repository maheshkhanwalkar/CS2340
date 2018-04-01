package edu.gatech.buzzshelter.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Shelter implements Serializable
{
    /* Shelter information */
    private int key;
    private String name;
    private String restrict;

    private final List<Capacity> capacity;

    private double latitude;
    private double longitude;
    private String address;
    private String notes;
    private String phone;

    /* Capacity information */
    public static class Capacity implements Serializable
    {
        private final String category;
        private final int capacity;
        private int available;

        public Capacity()
        {
            this.category = "Beds";
            this.capacity = -1;
            this.available = capacity;
        }

        Capacity(String category, int capacity)
        {
            this.category = category;
            this.capacity = capacity;
            this.available = capacity;
        }

        public String getCategory()
        {
            return category;
        }

        public int getCapacity()
        {
            return capacity;
        }

        public int getAvailable()
        {
            return available;
        }

        private boolean reserve(int amt)
        {
            /* Range check */
            if(amt > available)
                return false;

            available -= amt;
            return true;
        }

        private boolean cancel(int amt)
        {
            /* Range check */
            if((amt + available) > capacity)
                return false;

            available += amt;
            return true;
        }
    }

    public Shelter() {
        this.capacity = new ArrayList<>();
    }

    /* Construct Shelter object */
    public Shelter(int key, String name, List<Capacity> capacity, String restrict,
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

    public boolean reserve(String type, int amt)
    {
        Shelter.Capacity match = capacity.stream()
                .filter(x -> x.getCategory().equals(type))
                .collect(Collectors.toList()).get(0);

        return match.reserve(amt);
    }

    public boolean cancel(String type, int amt)
    {
        Shelter.Capacity match = capacity.stream()
                .filter(x -> x.getCategory().equals(type))
                .collect(Collectors.toList()).get(0);

        return match.cancel(amt);
    }

    public boolean matchName(CharSequence name)
    {
        return (name.length() <= this.name.length()) &&
                this.name.substring(0, name.length()).toLowerCase().contentEquals(name);

    }

    public boolean matchGender(String gender)
    {
        String[] all = restrict.split("/");

        for(String item : all)
        {
            if(item.toLowerCase().equals(gender.toLowerCase()))
                return true;
        }

        return false;
    }

    public boolean matchAge(String age)
    {
        String[] all = restrict.split("/");

        for(String item : all)
        {
            if(item.toLowerCase().equals(age.toLowerCase()))
                return true;
        }

        return false;
    }


    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Shelter))
            return false;

        /* Check by name */
        Shelter other = (Shelter)obj;
        return this.name.equals(other.getName());
    }

    @Override
    public int hashCode()
    {
        return this.name.hashCode();
    }

    /* Getters */
    public int getKey()
    {
        return key;
    }

    public List<Capacity> getCapacity()
    {
        return Collections.unmodifiableList(capacity);
    }

    public String getName()
    {
        return name;
    }

    public CharSequence getRestrict()
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

    public CharSequence getAddress()
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
