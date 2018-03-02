package edu.gatech.buzzshelter.model.db;
import java.util.HashMap;

public class MemDB<K, V> implements Database<K, V>
{
    private HashMap<K, V> db = new HashMap<>();

    @Override
    public boolean put(K key, V value, boolean overwrite)
    {
        /* Already exists (and write disabled) */
        if(db.containsKey(key) && !overwrite)
            return false;

        db.put(key, value);
        return true;
    }

    @Override
    public V get(K key)
    {
        return db.get(key);
    }

    @Override
    public boolean contains(K key)
    {
        return db.containsKey(key);
    }
}
