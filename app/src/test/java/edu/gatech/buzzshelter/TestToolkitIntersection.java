package edu.gatech.buzzshelter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.gatech.buzzshelter.model.data.Shelter;
import edu.gatech.buzzshelter.model.db.util.Toolkit;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Kyle Perras
 * testing of Toolkit's intersection method
 */

public class TestToolkitIntersection {

    private static Set<Integer> set1and2;
    private static Set<Integer> set2and3;
    private static Set<Integer> set1and3;
    private static Set<Integer> set12and3;
    private static Set<Integer> expectedSet1and2;
    private static Set<Integer> expectedSet2and3;
    private static Set<Integer> expectedSet1and3;
    private static Set<Integer> expectedSet12and3;

    private static final Set<Shelter> nullInputSet = Toolkit.intersect(null);
    private static final Set<Shelter> emptySet = Toolkit.intersect(new HashSet<Shelter>());

    @Before
    public  void setup (){

        Set<Integer> set1;
        Set<Integer> set2;
        Set<Integer> set3;

        set1 = new HashSet<Integer>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2 = new HashSet<Integer>();
        set2.add(3);
        set2.add(4);
        set2.add(5);

        set3 = new HashSet<Integer>();
        set3.add(5);
        set3.add(6);
        set3.add(7);

        expectedSet1and2 = new HashSet<Integer>();
        expectedSet1and2.add(3);
        expectedSet2and3 = new HashSet<Integer>();
        expectedSet2and3.add(5);
        expectedSet1and3 = new HashSet<Integer>();
        expectedSet12and3 = new HashSet<Integer>();

        set1and2 = Toolkit.intersect(set1, set2);
        set2and3 = Toolkit.intersect(set2, set3);
        set1and3 = Toolkit.intersect(set1, set3);
        set12and3 = Toolkit.intersect(set1, set2, set3);

    }

    @Test
    public void testNullInput() {
        assertEquals(null, nullInputSet);
    }

    @Test
    public void testEmpty() {
        assertEquals((Set) new HashSet<Integer>(), emptySet);
    }

    @Test
    public void test1and2() {
        assertEquals(expectedSet1and2, set1and2);
    }

    @Test
    public void test2and3() {
        assertEquals(expectedSet2and3, set2and3);
    }

    @Test
    public void test1and3() {
        assertEquals(expectedSet1and3, set1and3);
    }

    @Test
    public void test12and3() {
        assertEquals(expectedSet12and3, set12and3);
    }
}

