package edu.gatech.buzzshelter.model.db;

/* Database connector */

import java.util.List;
import java.util.Set;

/**
 * Database interface
 * @param <V> - data type of value
 */
public abstract class Database<V>
{
    /**
     * Put (key, value) into the database
     * @param key - key to insert
     * @param value - value associated with key
     * @return true, if put was successful
     */
    public abstract boolean put(String key, V value);

    /**
     * Get the value associated with key
     * @param key - key to search against
     * @return the value, or null if the key does not exist
     */
    public abstract V get(String key);

    /**
     * Check if the database contains key
     * @param key - key to check against
     * @return true, if the db contains the key
     */
    public abstract boolean contains(String key);


    /**
     * Remove the entry corresponding to key
     * @param key - key to search against
     * @return true, if remove was successful
     */
    public abstract boolean remove(String key);

    /**
     * Return all the items in the db
     * @return the entire collection
     */
    public abstract Set<KVPair<String, V>> items();

    /**
     * Return a set of all the keys in the db
     * @return set of keys
     */
    public abstract Set<String> keySet();

    /**
     * Return a list of all the values in the db
     * @return list of values
     */
    public abstract List<V> values();
}
