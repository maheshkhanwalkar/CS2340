package edu.gatech.buzzshelter.model.db.types;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import edu.gatech.buzzshelter.model.db.Database;
import edu.gatech.buzzshelter.model.db.KVPair;

public class FirebaseDB<T> extends Database<T>
{
    private FirebaseDatabase database;

    private volatile MemDB<T> mem = new MemDB<>();

    /* Lock for database operations */
    private Lock lock = new ReentrantLock();

    /* Should be T.class (hack) */
    public FirebaseDB(Class<T> type)
    {
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        ref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dS)
            {
                ref.removeEventListener(this);
                Iterable<DataSnapshot> snaps = dS.getChildren();

                /* Populate all the data into memory */
                lock.lock();

                for(DataSnapshot data : snaps)
                {
                    T item = data.getValue(type);
                    mem.put(data.getKey(), item);
                }

                lock.unlock();
            }

            @Override
            public void onCancelled(DatabaseError error)
            {
                Log.w("DB", "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public boolean put(String key, T value)
    {
        lock.lock();

        boolean result = mem.put(key, value);
        database.getReference(key).setValue(value);

        lock.unlock();

        return result;
    }

    @Override
    public T get(String key)
    {
        lock.lock();
        T value = mem.get(key);
        lock.unlock();

        return value;
    }

    @Override
    public boolean contains(String key)
    {
        lock.lock();
        boolean result = mem.contains(key);
        lock.unlock();

        return result;
    }

    @Override
    public boolean remove(String key)
    {
        lock.lock();

        boolean result = mem.remove(key);
        database.getReference(key).removeValue();

        lock.unlock();

        return result;
    }

    @Override
    public Set<KVPair<String, T>> items()
    {
        lock.lock();
        Set<KVPair<String, T>> items = mem.items();
        lock.unlock();

        return items;
    }

    @Override
    public Set<String> keySet()
    {
        lock.lock();
        Set<String> keys = mem.keySet();
        lock.unlock();

        return keys;
    }

    @Override
    public List<T> values()
    {
        lock.lock();
        List<T> values = mem.values();
        lock.unlock();

        return values;
    }
}
