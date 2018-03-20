package edu.gatech.buzzshelter.model.facade;

import java.util.List;
import java.util.Set;

import edu.gatech.buzzshelter.model.control.ReservationMgr;
import edu.gatech.buzzshelter.model.control.ShelterMgr;
import edu.gatech.buzzshelter.model.data.Reservation;
import edu.gatech.buzzshelter.model.data.Shelter;

public class DataFacade
{
    private static final DataFacade ourInstance = new DataFacade();

    private ShelterMgr sMgr = new ShelterMgr();
    private ReservationMgr rMgr = new ReservationMgr();

    public static DataFacade getInstance()
    {
        return ourInstance;
    }

    private DataFacade() {}

    public void setup()
    {
        sMgr.setup();
        rMgr.setup();
    }

    public Shelter getShelter(String name)
    {
        return sMgr.get(name);
    }

    public boolean canReserve()
    {
        return rMgr.canReserve();
    }

    public Reservation getReservation()
    {
        return rMgr.get();
    }

    public boolean reserve(String name, String type, int amt)
    {
        /* Already reserved */
        if (rMgr.get() != null)
            return false;

        /* Make the reservation */
        return sMgr.reserve(name, type, amt)
                && rMgr.put(name, type, amt);
    }

    public boolean cancel(String name, String type, int amt)
    {
        /* Nothing to cancel */
        if(rMgr.get() == null)
            return false;

        rMgr.cancel();
        return sMgr.cancel(name, type, amt);
    }

    public Set<Shelter> matchName(String name)
    {
        return sMgr.matchName(name);
    }

    public Set<Shelter> matchGender(String gender)
    {
        return sMgr.matchGender(gender);
    }

    public Set<Shelter> matchAge(String age)
    {
        return sMgr.matchAge(age);
    }

    public List<Shelter> getShelters()
    {
        return sMgr.getShelters();
    }
}
