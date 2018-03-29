package edu.gatech.buzzshelter.model.facade;
import java.util.List;

import edu.gatech.buzzshelter.model.data.DataElement;
import edu.gatech.buzzshelter.model.control.DataManager;

public class DataServiceFacade
{
    private static DataServiceFacade INSTANCE = new DataServiceFacade();

    public static DataServiceFacade getInstance()
    {
        return INSTANCE;
    }

    private DataManager dataManager;

    private DataServiceFacade()
    {
        dataManager = new DataManager();
    }

    public List<DataElement> getData()
    {
        return dataManager.getData();
    }

    public void setNameFilter(String name)
    {
        dataManager.setNameFilter(name);
    }

    public void setGenderFilter(String gender)
    {
        dataManager.setGenderFilter(gender);
    }

    public void setAgeFilter(String age)
    {
        dataManager.setAgeFilter(age);
    }

    public void resetFilter()
    {
        dataManager.setNameFilter("");
        dataManager.setGenderFilter("Any");
        dataManager.setAgeFilter("Any");
    }
}
