package datastructures.map;


import java.util.ArrayList;

public class HashMap implements Map {
    private ArrayList[] buckets;
    private int size = 0;

    public HashMap() {
        this.buckets = new ArrayList[10];
    }

    public HashMap(int size) {
        this.buckets = new ArrayList[size];
    }

    @Override
    public Object put(Object key, Object value) {
        keyNotNull(key);

        Entry entry = new Entry(key, value);
        ArrayList bucket = buckets[findBucket(key)];
        if (bucket == null) {
            bucket = new ArrayList();
            bucket.add(entry);
            buckets[findBucket(key)] = bucket;
            size++;
        } else if (entryPresent(bucket, key)) {
            setEntryPresent(bucket, key, entry);
        } else {
            bucket.add(entry);
            size++;
        }
        return value;
    }

    @Override
    public Object get(Object key) {
        keyNotNull(key);
        var bucket = buckets[findBucket(key)];
        if (bucket == null) {
            return null;
        }
        return findEntry(bucket, key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(Object key) {
        keyNotNull(key);
        ArrayList bucket = buckets[findBucket(key)];
        return entryPresent(bucket, key);
    }

    @Override
    public Object remove(Object key) {
        keyNotNull(key);
        ArrayList bucket = buckets[findBucket(key)];
        if (bucket == null) {
            return null;
        }
        return deleteEntry(bucket, key);
    }

    private int findBucket(Object key) {
        int bucketNumber = key.hashCode() % buckets.length;
        if (bucketNumber < 0) {
            return bucketNumber * -1;
        }
        return bucketNumber;
    }

    private Object findEntry(ArrayList<Entry> bucket, Object key) {
        for (Entry entry : bucket) {
            if (entry.getKey() == key) {
                return entry.getValue();
            }
        }
        return null;
    }

    private boolean entryPresent(ArrayList<Entry> bucket, Object key) {
        for (Entry entry : bucket) {
            if (entry.getKey() == key) {
                return true;
            }
        }
        return false;
    }

    private void setEntryPresent(ArrayList<Entry> bucket, Object key, Entry newEntry) {
        for (Entry entry : bucket) {
            if (entry.getKey().equals(key)) {
                bucket.remove(entry);
                bucket.add(newEntry);
            }
        }
    }

    private Object deleteEntry(ArrayList<Entry> bucket, Object key) {
        for (Entry entry : bucket) {
            if (entry.getKey().equals(key)) {
                Object result = entry.getValue();
                bucket.remove(entry);
                return result;
            }
        }
        return null;
    }

    private void keyNotNull(Object key) {
        if (key == null) {
            throw new NullPointerException("Key shouldn`t be equal null");
        }
    }
}

class Entry {
    private final Object key;
    private Object value;

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public Entry(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}