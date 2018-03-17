package edu.gatech.buzzshelter.model.db;

public interface Encoder<V>
{
    /* Decode src into its fields */
    String[] decode(V src);

    /* Encode raw fields into the object */
    V encode(String[] raw);

    /* Get the 'key' for src */
    String getKey(V src);
}
