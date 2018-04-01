package edu.gatech.buzzshelter.model.facade;
import java.util.List;

import edu.gatech.buzzshelter.model.data.DataElement;
import edu.gatech.buzzshelter.model.control.DataMgr;

public final class DataServiceFacade
{
    private static final DataServiceFacade INSTANCE = new DataServiceFacade();

    public static DataServiceFacade getInstance()
    {
        return INSTANCE;
    }

    private final DataMgr dataMgr;

    private DataServiceFacade()
    {
        dataMgr = new DataMgr();
    }

    public List<DataElement> getData()
    {
        return dataMgr.getData();
    }

    public void setNameFilter(String name)
    {
        dataMgr.setNameFilter(name);
    }

    public void setGenderFilter(String gender)
    {
        dataMgr.setGenderFilter(gender);
    }

    public void setAgeFilter(String age)
    {
        dataMgr.setAgeFilter(age);
    }

    public void resetFilter()
    {
        dataMgr.setFilter("", "Any", "Any");
    }
}
