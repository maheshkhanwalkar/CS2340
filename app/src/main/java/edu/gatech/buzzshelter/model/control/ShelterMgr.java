package edu.gatech.buzzshelter.model.control;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import edu.gatech.buzzshelter.model.db.Database;
import edu.gatech.buzzshelter.model.db.types.FirebaseDB;
import edu.gatech.buzzshelter.model.data.Shelter;

public class ShelterMgr
{
    private Database<Shelter> shelters;

    /* Load shelter information  */
    public void setup()
    {
        /* Don't do anything */
        if(shelters != null)
            return;

        shelters = new FirebaseDB<>("shelters", Shelter.class);
    }

    public Shelter get(String key)
    {
        return shelters.get(key);
    }

    public boolean reserve(String name, String type, int amt)
    {
        boolean result = shelters.get(name).reserve(type, amt);
        return result && put(name, shelters.get(name));
    }

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

    public Set<Shelter> matchName(CharSequence name)
    {
        return matcher(x -> x.matchName(name));
    }

    public Set<Shelter> matchGender(String gender)
    {
        return matcher(x -> x.matchGender(gender));
    }

    public Set<Shelter> matchAge(String age)
    {
        return matcher(x -> x.matchAge(age));
    }

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
