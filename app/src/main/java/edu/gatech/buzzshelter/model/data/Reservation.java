package edu.gatech.buzzshelter.model.data;

import java.io.Serializable;

/**
 * Reservation information
 */
public class Reservation implements Serializable
{
    private String shelter;
    private String type;
    private int amt;

    /**
     * Initialize default reservation
     */
    public Reservation() {}

    /**
     * Initialize reservation
     * @param shelter - shelter at
     * @param type - type (beds, apartments, etc.)
     * @param amt - amount to reserve
     */
    public Reservation(String shelter, String type, int amt)
    {
        this.shelter = shelter;
        this.type = type;
        this.amt = amt;
    }

    /**
     * Get shelter
     * @return shelter
     */
    public String getShelter()
    {
        return shelter;
    }

    /**
     * Get type
     * @return type
     */
    public String getType()
    {
        return type;
    }

    /**
     * Get amount
     * @return amount reserved
     */
    public int getAmt()
    {
        return amt;
    }
}
