package edu.gatech.buzzshelter.model.user;

import edu.gatech.buzzshelter.model.db.Encoder;

public class Shelter
{
    /* Shelter information */
    private int key;
    private String name, restrict, capacity;
    private double latitude, longitude;
    private String address, notes, phone;

    /* Encoder for the Shelter */
    private static Encoder<Shelter> encoder = new Encoder<Shelter>()
    {
        @Override
        public String[] decode(Shelter src)
        {
            String[] raw = new String[9];

            raw[0] = Integer.toString(src.getKey());
            raw[1] = src.getName();
            raw[2] = src.getCapacity();
            raw[3] = src.getRestrict();
            raw[4] = Double.toString(src.getLongitude());
            raw[5] = Double.toString(src.getLatitude());
            raw[6] = src.getAddress();
            raw[7] = src.getNotes();
            raw[8] = src.getPhone();

            return raw;
        }

        @Override
        public Shelter encode(String[] raw)
        {
            int key = Integer.parseInt(raw[0]);
            String name = raw[1];
            String capacity = raw[2];
            String restrict = raw[3];
            double longitude = Double.parseDouble(raw[4]);
            double latitude = Double.parseDouble(raw[5]);
            String address = raw[6];
            String notes = raw[7];
            String phone = raw[8];

            return new Shelter(key, name, capacity, restrict,
                    longitude, latitude, address, notes, phone);
        }

        @Override
        public String getKey(Shelter src)
        {
            return src.getName();
        }
    };

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

    public static Encoder<Shelter> getEncoder()
    {
        return encoder;
    }

    public boolean matchName(String name)
    {
        if(name.length() > this.name.length())
            return false;

        return this.name.substring(0, name.length())
                .toLowerCase().equals(name);
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
