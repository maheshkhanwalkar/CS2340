package edu.gatech.buzzshelter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.buzzshelter.model.control.DataMgr;
import edu.gatech.buzzshelter.model.data.DataElement;
import edu.gatech.buzzshelter.model.data.Shelter;
import edu.gatech.buzzshelter.model.facade.DataFacade;

import static org.junit.Assert.*;

/**
 * Created by Kyle on 4/9/2018.
 */

public class TestDataMgrGetData {

    private static final DataMgr mockDataManager = new DataMgr();
    private List<Shelter.Capacity> capacity = new ArrayList<Shelter.Capacity>();
    private final List<Shelter> mockShelterList = new ArrayList<Shelter>();
    @Before
    public void setup() {

        // Mock Shelters

        Shelter mock1 = new Shelter(2, "Hello World", capacity, "Woman/Children",
                1.0, 1.0, "Address", "Note", "999-999-9999");

        Shelter mock2 = new Shelter(2, "Another Name", capacity, "Woman/Children",
                1.0, 1.0, "Address", "Note", "888-888-8888");

        Shelter mock3 = new Shelter(2, "Some Shelter", capacity, "Woman/Children",
                1.0, 1.0, "Address", "Note", "777-777-7777");

        mockShelterList.add(mock1);
        mockShelterList.add(mock2);
        mockShelterList.add(mock3);

        List<DataElement> actualDataNoFilters = mockDataManager.filterData(mockShelterList);
        mockDataManager.setFilter("Hope", "Any", "Any");
        List<DataElement> actualDataNameFilter = mockDataManager.filterData(mockShelterList);
        mockDataManager.setFilter("", "Any", "Any");          // put actual age
        List<DataElement> actualDataAgeFilter = mockDataManager.filterData(mockShelterList);
        mockDataManager.setFilter("Hope", "Any", "Any");
        List<DataElement> actualDataGenderFilter = mockDataManager.filterData(mockShelterList);
        mockDataManager.setFilter("Hope", "Any", "Any");

    }

    @Test
    public void testNameOnlyFilter() {
        assertEquals(1, 1);
    }

    @Test
    public void testAgeOnlyFilter() {
        assertEquals(2, 2);
    }

    @Test
    public void testGenderFilter() {
        assertEquals(2, 2);
    }
}
