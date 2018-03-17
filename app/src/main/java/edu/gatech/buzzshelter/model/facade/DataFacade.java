package edu.gatech.buzzshelter.model.facade;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.gatech.buzzshelter.model.control.ShelterManager;
import edu.gatech.buzzshelter.model.user.Shelter;

public class DataFacade
{
    private static final DataFacade ourInstance = new DataFacade();
    private ShelterManager manager = new ShelterManager();

    public static DataFacade getInstance()
    {
        return ourInstance;
    }
    private DataFacade() {}

    public void parseShelter(InputStream stream)
    {
         manager.parseShelter(stream);
    }

    public Set<Shelter> matchName(String name)
    {
        return manager.matchName(name);
    }

    public List<Shelter> getShelters()
    {
        return manager.getShelters();
    }
}
