package edu.gatech.buzzshelter.model.control;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import edu.gatech.buzzshelter.model.db.Database;
import edu.gatech.buzzshelter.model.db.types.FirebaseDB;
import edu.gatech.buzzshelter.model.data.Shelter;

/**
 * Shelter manager
 */
public class ShelterMgr
{
    private Database<Shelter> shelters;

    /**
     * Load shelter data from Firebase
     */
    public void setup()
    {
        /* Don't do anything */
        if(shelters != null)
            return;

        shelters = new FirebaseDB<>("shelters", Shelter.class);
    }

    /**
     * Get shelter by name
     * @param key - shelter name
     * @return the shelter
     */
    public Shelter get(String key)
    {
        return shelters.get(key);
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
        boolean result = shelters.get(name).reserve(type, amt);
        return result && put(name, shelters.get(name));
    }

    /**
     * Cancel a reservation
     * @param name - shelter name
     * @param type - bed type
     * @param amt - amount
     */
    public void cancel(String name, String type, int amt)
    {
        boolean result = shelters.get(name).cancel(type, amt);
        if (result)
        {
            put(name, shelters.get(name));
        }
    }

    /* Add {key, value} to the database */
    private boolean put(String key, Shelter value)
    {
        return shelters.put(key, value);
    }

    /**
     * Match by name
     * @param name - name to match by
     * @return matching shelters
     */
    public Set<Shelter> matchName(CharSequence name)
    {
        return matcher(x -> x.matchName(name));
    }

    /**
     * Match by gender
     * @param gender - gender to match by
     * @return matching shelters
     */
    public Set<Shelter> matchGender(String gender)
    {
        return matcher(x -> x.matchGender(gender));
    }

    /**
     * Match by age
     * @param age - age to match by
     * @return matching shelters
     */
    public Set<Shelter> matchAge(String age)
    {
        return matcher(x -> x.matchAge(age));
    }

    /**
     * Get all the shelters
     * @return shelter list
     */
    public List<Shelter> getShelters()
    {
        return shelters.values();
    }

    /* Generic matching helper */
    private Set<Shelter> matcher(Predicate<Shelter> pred)
    {
        return shelters.values().stream()
                .filter(pred).collect(Collectors.toSet());
    }
}
