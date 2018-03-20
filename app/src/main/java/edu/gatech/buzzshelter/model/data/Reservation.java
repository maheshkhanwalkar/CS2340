package edu.gatech.buzzshelter.model.data;

import java.io.Serializable;

public class Reservation implements Serializable
{
    private String shelter;
    private String type;
    private int amt;

    public Reservation() {}

    public Reservation(String shelter, String type, int amt)
    {
        this.shelter = shelter;
        this.type = type;
        this.amt = amt;
    }

    public String getShelter()
    {
        return shelter;
    }

    public String getType()
    {
        return type;
    }

    public int getAmt()
    {
        return amt;
    }
}
