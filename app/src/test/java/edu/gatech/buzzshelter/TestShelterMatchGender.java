package edu.gatech.buzzshelter;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.buzzshelter.model.data.Shelter;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Yukun Ding on 2018/4/9.
 */

public class TestShelterMatchGender {
    private Shelter s_blankrestriction, s_singleGender, s_bothGender, s_upperCase;
    // the gender input can be any String other than "Any"
    // the input should be "male" or "female"
    @Before
    public void setup() {
        s_blankrestriction = new Shelter(0, "blank", null,
                null, 0, 0, null, null, null);
        s_singleGender = new Shelter(0, "female", null,
                "female", 0, 0, null, null, null);
        s_bothGender = new Shelter(0, "both", null,
                "male/female", 0, 0, null, null, null);
        s_upperCase = new Shelter(0, "both_uppercase", null,
                "maLe/Female", 0, 0, null, null, null);
    }

    @Test
    public void testMatchblank(){
        assertTrue(s_blankrestriction.matchGender(""));
        assertFalse(s_blankrestriction.matchGender("male"));
    }

    @Test
    public void testMatchSingle(){
        assertTrue(s_singleGender.matchGender("female"));
        assertTrue(s_singleGender.matchGender("fEmale"));
        assertFalse(s_singleGender.matchGender("male"));
        assertFalse(s_singleGender.matchGender(""));
    }

    @Test
    public void testMatchBoth(){
        assertTrue(s_bothGender.matchGender("female"));
        assertTrue(s_bothGender.matchGender("fEmale"));
        assertTrue(s_bothGender.matchGender("male"));
        assertTrue(s_bothGender.matchGender("mALe"));
        assertFalse(s_bothGender.matchGender(""));
    }
    @Test
    public void testMatchUppercase(){
        assertTrue(s_upperCase.matchGender("female"));
        assertTrue(s_upperCase.matchGender("fEmale"));
        assertTrue(s_upperCase.matchGender("male"));
        assertTrue(s_upperCase.matchGender("mALe"));
        assertFalse(s_upperCase.matchGender(""));
    }
}
