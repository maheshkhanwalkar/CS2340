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

    /**
     * Create a new key-value pair
     * @param key - key
     * @param value - value
     */
    public KVPair(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    /**
     * Get the key
     * @return key
     */
    public K getKey()
    {
        return key;
    }

    /**
     * Get the value
     * @return value
     */
    public V getValue()
    {
        return value;
    }
}
