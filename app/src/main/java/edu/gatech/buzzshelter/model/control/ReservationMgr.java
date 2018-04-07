package edu.gatech.buzzshelter.model.control;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.gatech.buzzshelter.model.data.Reservation;
import edu.gatech.buzzshelter.model.db.Database;
import edu.gatech.buzzshelter.model.db.types.FirebaseDB;

/**
 * Reservation manager
 */
public class ReservationMgr
{
    private Database<Reservation> reservations;

    /**
     * Load reservation data from Firebase
     */
    public void setup()
    {
        /* Don't do anything */
        if(reservations != null)
            return;

        reservations = new FirebaseDB<>("reservations",
                Reservation.class);
    }

    /**
     * Get current user's UID
     * @return the UID
     */
    private String getUid()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null)
            return null;

        return user.getUid();
    }


    /**
     * Get the current reservation
     * @return the reservation
     */
    public Reservation get()
    {
        String uid = getUid();

        if(uid == null)
            return null;

        return reservations.get(uid);
    }

    /**
     * Cancel the current reservation
     */
    public void cancel()
    {
        String uid = getUid();
        reservations.remove(uid);
    }

    /**
     * Make a reservation
     * @param shelterName - shelter name
     * @param type - bed type
     * @param amt - amount
     * @return true, if successful
     */
    public boolean put(String shelterName, String type, int amt)
    {
        String uid = getUid();
        Reservation res = new Reservation(shelterName, type, amt);

        return reservations.put(uid, res);
    }

    /**
     * Return if the user can reserve
     * @return true, if possible
     */
    public boolean canReserve()
    {
        return get() == null;
    }
}
