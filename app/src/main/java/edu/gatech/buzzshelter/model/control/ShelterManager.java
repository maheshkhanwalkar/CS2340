package edu.gatech.buzzshelter.model.control;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import edu.gatech.buzzshelter.model.db.Database;
import edu.gatech.buzzshelter.model.db.KVPair;
import edu.gatech.buzzshelter.model.db.types.CsvDB;
import edu.gatech.buzzshelter.model.user.Shelter;

public class ShelterManager
{
    private Database<Shelter> shelters;

    /* Parse shelter information from CSV */
    public void parseShelter(InputStream stream)
    {
        shelters = new CsvDB<>(Shelter.getEncoder(), stream);
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
