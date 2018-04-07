package edu.gatech.buzzshelter.model.facade;

import java.util.List;
import java.util.Set;

import edu.gatech.buzzshelter.model.control.ReservationMgr;
import edu.gatech.buzzshelter.model.control.ShelterMgr;
import edu.gatech.buzzshelter.model.data.Reservation;
import edu.gatech.buzzshelter.model.data.Shelter;

/**
 * Main data pass-through facade
 */
public final class DataFacade
{
    private static final DataFacade ourInstance = new DataFacade();

    private final ShelterMgr sMgr = new ShelterMgr();
    private final ReservationMgr rMgr = new ReservationMgr();

    /**
     * Get singleton instance
     * @return the instance
     */
    public static DataFacade getInstance()
    {
        return ourInstance;
    }

    private DataFacade() {}

    /**
     * Setup facade
     */
    public void setup()
    {
        sMgr.setup();
        rMgr.setup();
    }

    /**
     * Get shelter
     * @param name - name of shelter
     * @return the shelter
     */
    public Shelter getShelter(String name)
    {
        return sMgr.get(name);
    }

    /**
     * Check if the user can reserve
     * @return true, if they can
     */
    public boolean canReserve()
    {
        return rMgr.canReserve();
    }

    /**
     * Get current reservation
     * @return the reservation
     */
    public Reservation getReservation()
    {
        return rMgr.get();
    }

    /**
     * Make a reservation
     * @param name - shelter name
     * @param type - bed type
     * @param amt - amount
     * @return true, if successful
     */
    public boolean reserve(String name, String type, int amt)
    {
        /* Already reserved */
        if (rMgr.get() != null)
            return false;

        /* Make the reservation */
        return sMgr.reserve(name, type, amt)
                && rMgr.put(name, type, amt);
    }

    /**
     * Cancel a reservation
     * @param name - shelter name
     * @param type - bed type
     * @param amt - amount
     */
    public void cancel(String name, String type, int amt)
    {
        /* Nothing to cancel */
        if(rMgr.get() == null)
            return;

        rMgr.cancel();
        sMgr.cancel(name, type, amt);
    }

    /**
     * Get matching shelters
     * @param name - search by name
     * @return matching shelters
     */
    public Set<Shelter> matchName(CharSequence name)
    {
        return sMgr.matchName(name);
    }

    /**
     * Get matching shelters
     * @param gender - search by gender
     * @return matching shelters
     */
    public Set<Shelter> matchGender(String gender)
    {
        return sMgr.matchGender(gender);
    }

    /**
     * Get matching shelters
     * @param age - search by age
     * @return matching shelters
     */
    public Set<Shelter> matchAge(String age)
    {
        return sMgr.matchAge(age);
    }

    /**
     * Get list of all shelters
     * @return all shelters
     */
    public List<Shelter> getShelters()
    {
        return sMgr.getShelters();
    }
}
