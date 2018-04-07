package edu.gatech.buzzshelter.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Shelter information class
 */
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

        /**
         * Initialize a default capacity
         */
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

        /**
         * Get the category
         * @return category
         */
        public String getCategory()
        {
            return category;
        }

        /**
         * Get the capacity
         * @return current capacity
         */
        public int getCapacity()
        {
            return capacity;
        }

        /**
         * Get the availability
         * @return current availability
         */
        public int getAvailable()
        {
            return available;
        }

        /**
         * Reserve space in the shelter
         * @param amt - amount to reserve
         * @return true, if successful
         */
        private boolean reserve(int amt)
        {
            /* Range check */
            if(amt > available)
                return false;

            available -= amt;
            return true;
        }

        /**
         * Cancel a reservation in the shelter
         * @param amt - amount to release
         * @return true, if successful
         */
        private boolean cancel(int amt)
        {
            /* Range check */
            if((amt + available) > capacity)
                return false;

            available += amt;
            return true;
        }
    }

    /**
     * Initialize an empty shelter
     */
    public Shelter() {
        this.capacity = new ArrayList<>();
    }

    /**
     * Initialize a shelter
     * @param key - shelter key
     * @param name - shelter name
     * @param capacity - list of capacity information
     * @param restrict - restrictions
     * @param longitude - shelter long
     * @param latitude - shelter lat
     * @param address - shelter address
     * @param notes - additional notes
     * @param phone - shelter phone #
     */
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

    /**
     * Reserve spot(s) in a shelter
     * @param type - type (beds, apartments, etc.)
     * @param amt - amt to reserve
     * @return true, if successful
     */
    public boolean reserve(String type, int amt)
    {
        Shelter.Capacity match = capacity.stream()
                .filter(x -> x.getCategory().equals(type))
                .collect(Collectors.toList()).get(0);

        return match.reserve(amt);
    }

    /**
     * Cancel a reservation
     * @param type - type (beds, apartments, etc.)
     * @param amt - amt to reserve
     * @return true, if successful
     */
    public boolean cancel(String type, int amt)
    {
        Shelter.Capacity match = capacity.stream()
                .filter(x -> x.getCategory().equals(type))
                .collect(Collectors.toList()).get(0);

        return match.cancel(amt);
    }

    /**
     * Match by name
     * @param name - name to match against
     * @return true, if there is a match
     */
    public boolean matchName(CharSequence name)
    {
        return (name.length() <= this.name.length()) &&
                this.name.substring(0, name.length()).toLowerCase()
                        .contentEquals(name.toString().toLowerCase());

    }

    /**
     * Match by gender
     * @param gender - gender to match against
     * @return true, if there is a match
     */
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

    /**
     * Match by age
     * @param age - age to match against
     * @return true, if there is a match
     */
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

    /**
     * Get key
     * @return key
     */
    public int getKey()
    {
        return key;
    }

    /**
     * Get capacities
     * @return capacity list
     */
    public List<Capacity> getCapacity()
    {
        return Collections.unmodifiableList(capacity);
    }

    /**
     * Get name
     * @return shelter name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get restrictions
     * @return shelter restrictions
     */
    public CharSequence getRestrict()
    {
        return restrict;
    }

    /**
     * Get lat
     * @return shelter lat
     */
    public double getLatitude()
    {
        return latitude;
    }

    /**
     * Get long
     * @return shelter long
     */
    public double getLongitude()
    {
        return longitude;
    }

    /**
     * Get address
     * @return shelter address
     */
    public CharSequence getAddress()
    {
        return address;
    }

    /**
     * Get additional notes
     * @return notes
     */
    public String getNotes()
    {
        return notes;
    }

    /**
     * Get phone
     * @return shelter phone #
     */
    public String getPhone()
    {
        return phone;
    }
}
