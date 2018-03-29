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
 * Created by Kyle on 3/24/2018.
 */

public class DataManager {

    private
    DataFacade manager;

    private String nameFilter;
    private String genderFilter;
    private String ageFilter;

    public DataManager() {
        manager = DataFacade.getInstance();
        nameFilter = "";
        genderFilter = "Any";
        ageFilter = "Any";
    }

    public ArrayList<DataElement> getData() {
        ArrayList<DataElement> data = new ArrayList<DataElement>();
        List<Shelter> shelterList = manager.getShelters();
        Set<Shelter> shelterSet = new HashSet<>(shelterList);

        Set<Shelter> nameSet = nameMatch(shelterSet);
        Set<Shelter> genderSet = genderMatch(shelterSet);
        Set<Shelter> ageSet = ageMatch(shelterSet);
        Set<Shelter> filteredShelters = Toolkit.intersect(nameSet, genderSet, ageSet);

        for (Shelter shelter : filteredShelters) {
            String name = shelter.getName();
            String description = shelter.getNotes();
            Location location = new Location(shelter.getLatitude(), shelter.getLongitude());
            DataElement element = new DataElement(name, description, location);
            data.add(element);
        }
        return data;
    }

    private Set<Shelter> nameMatch(Set<Shelter> shelters) {
        if (nameFilter.equals("")) {
            return shelters;
        } else {
            return manager.matchName(nameFilter);
        }
    }

    private Set<Shelter> genderMatch(Set<Shelter> shelters) {
        if (genderFilter.equals("Any")) {
            return shelters;
        } else {
            return manager.matchGender(genderFilter);
        }
    }

    private Set<Shelter> ageMatch(Set<Shelter> shelters) {
        if (ageFilter.equals("Any")) {
            return shelters;
        } else {
            return manager.matchAge(ageFilter);
        }
    }

    public void setNameFilter(String name){ nameFilter = name; }

    public void setGenderFilter(String gender) { genderFilter = gender; }

    public void setAgeFilter(String age) { ageFilter = age; }

}
