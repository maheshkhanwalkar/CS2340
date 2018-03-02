package edu.gatech.buzzshelter.model.db;

/* Database connector */

public interface Database<K, V>
{
    boolean put(K key, V value, boolean overwrite);
    V get(K key);

    boolean contains(K key);
}
