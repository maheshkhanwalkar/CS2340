package edu.gatech.buzzshelter;

/**
 * Created by Kancheng Wang on 2018/4/3.
 * Test the method Shelter.matchAge(String age)
 */

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.buzzshelter.model.data.Shelter;

import static org.junit.Assert.*;


public class TestShelterMatchAge {
    private final static List<Shelter.Capacity> MOCK_CAPACITY = new ArrayList<Shelter.Capacity>();
    private static Shelter mock_shelter = new Shelter();
    private final static String restrict = "Children/Woman";

    @Before
    public void setup() {
        mock_shelter = new Shelter(2, "Name", MOCK_CAPACITY, "Woman/Children",
                1.0, 1.0, "Address", "Note", "404-888-8888");
    }

    @Test
    public void testTrue() {
        boolean result = mock_shelter.matchAge("Children");
        assertEquals(result, true);
    }

    @Test
    public void testFalse() {
        boolean result = mock_shelter.matchAge("Yound Adults");
        assertEquals(result, false);
    }
}
