package edu.gatech.buzzshelter.model.control;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.buzzshelter.model.facade.DataFacade;
import edu.gatech.buzzshelter.model.data.Shelter;

/**
 * Created by Kyle on 3/24/2018.
 */

public class DataManager {

    private ArrayList<DataElement> data;

    public DataManager() {
        data = new ArrayList<DataElement>();
        populate();
    }

    private void populate(){
        DataFacade manager = DataFacade.getInstance();
        List<Shelter> shelters = manager.getShelters();
        for (Shelter shelter : shelters) {
            String name = shelter.getName();
            String description = shelter.getNotes();
            Location location = new Location(shelter.getLatitude(), shelter.getLongitude());
            DataElement element = new DataElement(name, description, location);
            data.add(element);
        }
    }

    public ArrayList<DataElement> getData() {return data;}
}
