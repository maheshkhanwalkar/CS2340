package edu.gatech.buzzshelter.model.db.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class Toolkit
{
    /* Perform a union of all the sets */
    @SafeVarargs
    public static <T> Set<T> union(Set<T>... sets)
    {
        return Arrays.stream(sets).flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    /* Perform an intersection of all the sets */
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
                        .map(elem -> elem.contains(item))
                        .reduce((x, y) -> x && y).orElse(false))
                .collect(Collectors.toSet());
    }
}
