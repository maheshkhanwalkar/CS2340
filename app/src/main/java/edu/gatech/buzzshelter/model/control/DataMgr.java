package edu.gatech.buzzshelter.model.control;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.gatech.buzzshelter.model.data.DataElement;
import edu.gatech.buzzshelter.model.data.Location;
import edu.gatech.buzzshelter.model.db.util.Toolkit;
import edu.gatech.buzzshelter.model.facade.DataFacade;
import edu.gatech.buzzshelter.model.data.Shelter;

/**
 * Data manager
 */
public class DataMgr
{
    private final DataFacade manager;

    private String nameFilter;
    private String genderFilter;
    private String ageFilter;

    /**
     * Initialize data manager
     */
    public DataMgr()
    {
        manager = DataFacade.getInstance();
        nameFilter = "";
        genderFilter = "Any";
        ageFilter = "Any";
    }

    /**
     * Get all the data elements
     * @return list of data
     */
    public List<DataElement> getData()
    {
        List<DataElement> data = new ArrayList<>();
        List<Shelter> shelterList = manager.getShelters();
        Set<Shelter> shelterSet = new HashSet<>(shelterList);

        Set<Shelter> nameSet = nameMatch(shelterSet);
        Set<Shelter> genderSet = genderMatch(shelterSet);
        Set<Shelter> ageSet = ageMatch(shelterSet);
        Set<Shelter> filteredShelters = Toolkit.intersect(nameSet, genderSet, ageSet);

        for (Shelter shelter : filteredShelters)
        {
            String name = shelter.getName();
            String description = shelter.getNotes();
            Location location = new Location(shelter.getLatitude(), shelter.getLongitude());
            DataElement element = new DataElement(name, description, location);
            data.add(element);
        }
        return data;
    }

    private Set<Shelter> nameMatch(Set<Shelter> shelters)
    {
        if ("".equals(nameFilter))
        {
            return shelters;
        }
        else
        {
            return manager.matchName(nameFilter);
        }
    }

    private Set<Shelter> genderMatch(Set<Shelter> shelters)
    {
        if ("Any".equals(genderFilter))
        {
            return shelters;
        }
        else
        {
            return manager.matchGender(genderFilter);
        }
    }

    private Set<Shelter> ageMatch(Set<Shelter> shelters)
    {
        if ("Any".equals(ageFilter))
        {
            return shelters;
        }
        else
        {
            return manager.matchAge(ageFilter);
        }
    }

    /**
     * Set the filter
     * @param name - name filter
     * @param gender - gender filter
     * @param age - age filter
     */
    public void setFilter(String name, String gender, String age)
    {
        setNameFilter(name);
        setGenderFilter(gender);
        setAgeFilter(age);
    }

    /**
     * Set name filter
     * @param name - new name
     */
    public void setNameFilter(String name)
    {
        nameFilter = name;
    }

    /**
     * Set gender filter
     * @param gender - new gender
     */
    public void setGenderFilter(String gender)
    {
        genderFilter = gender;
    }

    /**
     * Set age filter
     * @param age - new age
     */
    public void setAgeFilter(String age)
    {
        ageFilter = age;
    }

}
