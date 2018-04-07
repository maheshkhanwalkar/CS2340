package edu.gatech.buzzshelter.model.db.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Helper utilities
 */
public final class Toolkit
{
    /**
     * Performs a union of the provided sets
     * @param sets - sets to union
     * @param <T> - type of data
     * @return the union
     */
    @SafeVarargs
    public static <T> Set<T> union(Set<T>... sets)
    {
        return Arrays.stream(sets).flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Performs an intersection of the provided sets
     * @param sets - sets to intersect
     * @param <T> - type of data
     * @return the intersection
     */
    @SafeVarargs
    public static <T> Set<T> intersect(Set<T>... sets)
    {
        /* Handle special cases */
        if(sets.length == 0)
            return new HashSet<>();

        if(sets.length == 1)
            return sets[0];

        /* Only add an item if *all* the sets contain it */
        return sets[0].stream()
                .filter(item -> Arrays.stream(sets)
                        .skip(1)
                        .map(set -> set.contains(item))
                        .reduce((x, y) -> x && y).orElse(false))
                .collect(Collectors.toSet());
    }
}
