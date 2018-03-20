package edu.gatech.buzzshelter.model.db.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import edu.gatech.buzzshelter.model.db.Database;
import edu.gatech.buzzshelter.model.db.KVPair;

/**
 * In-memory key-value database
 * @param <V> value type
 */
public class MemDB<V> extends Database<V>
{
    private HashMap<String, V> db = new HashMap<>();

    @Override
    public boolean put(String key, V value)
    {
        db.put(key, value);
        return true;
    }

    @Override
    public V get(String key)
    {
        return db.get(key);
    }

    @Override
    public boolean contains(String key)
    {
        return db.containsKey(key);
    }

    @Override
    public boolean remove(String key)
    {
        return db.remove(key) != null;
    }

    @Override
    public Set<String> keySet()
    {
        return db.keySet();
    }

    @Override
    public List<V> values()
    {
        return new ArrayList<>(db.values());
    }

    @Override
    public Set<KVPair<String, V>> items()
    {
        Set<Map.Entry<String, V>> entries = db.entrySet();

        /* Convert from Map's entries to our KVPair entries */
        return entries.stream().map(x -> new KVPair<>(x.getKey(), x.getValue()))
                .collect(Collectors.toSet());
    }
}
