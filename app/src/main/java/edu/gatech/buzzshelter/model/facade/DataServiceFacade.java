package edu.gatech.buzzshelter.model.facade;
import java.util.List;

import edu.gatech.buzzshelter.model.data.DataElement;
import edu.gatech.buzzshelter.model.control.DataMgr;

/**
 * Data service facade
 */
public final class DataServiceFacade
{
    private static final DataServiceFacade INSTANCE = new DataServiceFacade();

    /**
     * Obtain singleton instance
     * @return the instance
     */
    public static DataServiceFacade getInstance()
    {
        return INSTANCE;
    }

    private final DataMgr dataMgr;

    private DataServiceFacade()
    {
        dataMgr = new DataMgr();
    }

    /**
     * Get data elements
     * @return list of data
     */
    public List<DataElement> getData()
    {
        return dataMgr.getData();
    }

    /**
     * Set the name filter
     * @param name - name to filter by
     */
    public void setNameFilter(String name)
    {
        dataMgr.setNameFilter(name);
    }

    /**
     * Set the gender filter
     * @param gender - name to filter by
     */
    public void setGenderFilter(String gender)
    {
        dataMgr.setGenderFilter(gender);
    }

    /**
     * Set the age filter
     * @param age - name to filter by
     */
    public void setAgeFilter(String age)
    {
        dataMgr.setAgeFilter(age);
    }

    /**
     * Reset filter
     */
    public void resetFilter()
    {
        dataMgr.setFilter("", "Any", "Any");
    }
}
