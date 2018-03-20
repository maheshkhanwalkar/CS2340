package edu.gatech.buzzshelter.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.gatech.buzzshelter.model.db.Encoder;

public class Shelter implements Serializable
{
    /* Shelter information */
    private int key;
    private String name, restrict;

    private List<Capacity> capacity;

    private double latitude, longitude;
    private String address, notes, phone;

    /* Capacity information */
    public static class Capacity implements Serializable
    {
        private String category;
        private int capacity, available;

        public Capacity()
        {
            this.category = "Beds";
            this.capacity = -1;
            this.available = capacity;
        }

        public Capacity(String category, int capacity)
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
            if(amt + available > capacity)
                return false;

            available += amt;
            return true;
        }
    }

    /* Encoder for the Shelter */
    private static Encoder<Shelter> encoder = new Encoder<Shelter>()
    {
        @Override
        public String[] decode(Shelter src)
        {
            String[] raw = new String[9];

            raw[0] = Integer.toString(src.getKey());
            raw[1] = src.getName();
            raw[2] = src.getCapacity().toString(); //FIXME: This doesn't work (obviously)
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
            String rCap = raw[2];

            List<Capacity> capacity = new ArrayList<>();

            if(rCap.equals(""))
            {
                /* Default configuration */
                capacity.add(new Capacity("Beds", -1));
            }
            else
            {
                String[] multiple = rCap.split(",");
                //capacity = new Capacity[multiple.length];

                for(int i = 0; i < multiple.length; i++)
                {
                    String current = multiple[i];
                    int pos = 0;

                    /* Find how long the capacity it is */
                    while(pos < current.length() &&
                            Character.isDigit(current.charAt(pos)))
                    {
                        pos++;
                    }

                    String sub = current.substring(0, pos);
                    int actual = Integer.parseInt(sub);

                    String type;

                    if(pos == current.length())
                        type = "Beds";
                    else
                        type = current.substring(pos + 1, current.length());

                    capacity.add(new Capacity(type, actual));
                }
            }

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

    public static Encoder<Shelter> getEncoder()
    {
        return encoder;
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

    public boolean matchName(String name)
    {
        if(name.length() > this.name.length())
            return false;

        return this.name.substring(0, name.length())
                .toLowerCase().equals(name);
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
