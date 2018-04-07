package edu.gatech.buzzshelter;

import org.junit.Test;

import edu.gatech.buzzshelter.model.data.Shelter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Mahesh's JUnit test: Shelter's matchName(...) branch coverage
 */
public class TestShelterMatchName
{
    /* Together these test case cover branch coverage */

    private Shelter s = new Shelter(0, "Hello", null,
        null, 0, 0, null, null, null);

    @Test
    public void testLength()
    {
        assertFalse(s.matchName("LongerThanName"));
    }

    @Test
    public void testMatch()
    {
        assertTrue(s.matchName("He"));
        assertTrue(s.matchName("Hello"));
    }

    @Test
    public void testNoMatch()
    {
        assertFalse(s.matchName("Abc"));
        assertFalse(s.matchName("Helio"));
    }
}
