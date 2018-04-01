package edu.gatech.buzzshelter.model.db;

/**
 * Key-Value pairing
 * @param <K> key type
 * @param <V> value type
 */
public final class KVPair<K, V>
{
    private final K key;
    private final V value;

    public KVPair(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public K getKey()
    {
        return key;
    }

    public V getValue()
    {
        return value;
    }
}
