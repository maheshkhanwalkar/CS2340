package edu.gatech.buzzshelter.model.control;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.gatech.buzzshelter.model.data.Reservation;
import edu.gatech.buzzshelter.model.db.Database;
import edu.gatech.buzzshelter.model.db.types.FirebaseDB;

public class ReservationMgr
{
    private Database<Reservation> reservations;

    /* Load reservation information */
    public void setup()
    {
        /* Don't do anything */
        if(reservations != null)
            return;

        reservations = new FirebaseDB<>("reservations",
                Reservation.class);
    }

    private String getUid()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null)
            return null;

        return user.getUid();
    }


    public Reservation get()
    {
        String uid = getUid();

        if(uid == null)
            return null;

        return reservations.get(uid);
    }

    public boolean cancel()
    {
        String uid = getUid();
        return reservations.remove(uid);
    }

    public boolean put(String shelterName, String type, int amt)
    {
        String uid = getUid();
        Reservation res = new Reservation(shelterName, type, amt);

        return reservations.put(uid, res);
    }

    public boolean canReserve()
    {
        return get() == null;
    }
}
