package edu.gatech.buzzshelter.model.db.types;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import edu.gatech.buzzshelter.model.db.Database;
import edu.gatech.buzzshelter.model.db.Encoder;
import edu.gatech.buzzshelter.model.db.KVPair;

public class CsvDB<V> extends Database<V>
{
    private Encoder<V> encoder;
    private InputStream source;

    /* In-memory copy */
    private Database<V> mem = new MemDB<>();

    public CsvDB(Encoder<V> encoder, InputStream source)
    {
        this.encoder = encoder;
        this.source = source;

        parse();
    }

    /* Parse the CSV */
    private void parse()
    {
        try
        {
            CSVParser parser = new CSVParser(new InputStreamReader(source), CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();

            /* Skip first record (header) */
            for(int i = 1; i < records.size(); i++)
            {
                CSVRecord record = records.get(i);
                String[] items = new String[record.size()];

                for(int j = 0; j < items.length; j++)
                    items[j] = record.get(j);

                V value = encoder.encode(items);
                String key = encoder.getKey(value);

                mem.put(key, value);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean put(String key, V value)
    {
        return mem.put(key, value);
    }

    @Override
    public V get(String key)
    {
        return mem.get(key);
    }

    @Override
    public boolean contains(String key)
    {
        return mem.contains(key);
    }

    @Override
    public boolean remove(String key)
    {
        return mem.remove(key);
    }

    @Override
    public Set<String> keySet()
    {
        return mem.keySet();
    }

    @Override
    public List<V> values()
    {
        return mem.values();
    }

    @Override
    public Set<KVPair<String, V>> items()
    {
        return mem.items();
    }
}
