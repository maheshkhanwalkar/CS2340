package edu.gatech.buzzshelter.model.control;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import edu.gatech.buzzshelter.model.db.Database;
import edu.gatech.buzzshelter.model.db.types.FirebaseDB;
import edu.gatech.buzzshelter.model.user.Shelter;

public class ShelterManager
{
    private Database<Shelter> shelters;

    /* Load shelter information  */
    public void setup()
    {
        /* Don't do anything */
        if(shelters != null)
            return;

        shelters = new FirebaseDB<>(Shelter.class);
    }

    public Set<Shelter> matchName(String name)
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
