package datastructures.map;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringJoiner;

public class HashMap<K, V> implements Map<K, V>, Iterable<Entry<K, V>> {
    private ArrayList<Entry<K, V>>[] buckets;
    private int size = 0;

    public HashMap() {
        buckets = new ArrayList[10];
    }

    public HashMap(int size) {
        this.buckets = new ArrayList[size];
    }

    @Override
    public V put(K key, V value) {
        keyNotNull(key);
        if (size > buckets.length * 0.75) {
            grow();
        }
        Entry<K, V> entry = new Entry<>(key, value);
        int bucketNumber = findBucket(key);
        ArrayList<Entry<K, V>> bucket = buckets[bucketNumber];
        if (bucket == null) {
            bucket = new ArrayList<>();
            bucket.add(entry);
            buckets[bucketNumber] = bucket;
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
    public V get(K key) {
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
    public boolean containsKey(K key) {
        keyNotNull(key);
        ArrayList<Entry<K, V>> bucket = buckets[findBucket(key)];
        return entryPresent(bucket, key);
    }

    @Override
    public V remove(K key) {
        keyNotNull(key);
        ArrayList<Entry<K, V>> bucket = buckets[findBucket(key)];
        if (bucket == null) {
            return null;
        }
        return deleteEntry(bucket, key);
    }

    @Override
    public String toString() {
        ArrayList<Entry<K, V>> entries = doEntryList();
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Entry<K, V> entry : entries) {
            stringJoiner.add(String.format("[Key = %s, Value = %s]", entry.getKey(), entry.getValue()));
        }
        return stringJoiner.toString();
    }

    private int findBucket(K key) {
        int bucketNumber = key.hashCode() % buckets.length;
        if (bucketNumber < 0) {
            return bucketNumber * (-1);
        }
        return bucketNumber;
    }

    private int findNewBucket(K key, int length) {
        int bucketNumber = key.hashCode() % length;
        if (bucketNumber < 0) {
            return bucketNumber * (-1);
        }
        return bucketNumber;
    }

    private V findEntry(ArrayList<Entry<K, V>> bucket, K key) {
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private boolean entryPresent(ArrayList<Entry<K, V>> bucket, K key) {
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    private void setEntryPresent(ArrayList<Entry<K, V>> bucket, K key, Entry<K, V> newEntry) {
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                bucket.remove(entry);
                bucket.add(newEntry);
            }
        }
    }

    private V deleteEntry(ArrayList<Entry<K, V>> bucket, K key) {
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                var result = entry.getValue();
                bucket.remove(entry);
                return result;
            }
        }
        return null;
    }

    private void keyNotNull(K key) {
        if (key == null) {
            throw new NullPointerException("Key shouldn`t be equal null");
        }
    }

    private void grow() {
        ArrayList<Entry<K, V>>[] newBuckets = new ArrayList[size * 2];
        ArrayList<Entry<K, V>> entries = doEntryList();
        buckets = newBuckets;
        for (Entry<K, V> entry : entries) {
            put(entry.getKey(), entry.getValue());
            size--;
        }
    }

    private ArrayList<Entry<K, V>> doEntryList() {
        ArrayList<Entry<K, V>> entryList = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            ArrayList<Entry<K, V>> thisBucket = buckets[i];
            if (thisBucket != null) {
                entryList.addAll(thisBucket);
            }
        }
        return entryList;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private final ArrayList<Entry<K, V>> entryArrayList = doEntryList();
            private int iterator = 0;

            @Override
            public boolean hasNext() {
                int thisPosition = iterator;
                if (entryArrayList.size() <= iterator) {
                    return false;
                } else
                    return entryArrayList.get(thisPosition++) != null;
            }

            @Override
            public Entry<K, V> next() {
                if (entryArrayList.size() <= iterator) {
                    throw new IndexOutOfBoundsException("The next item does not exist");
                }
                var result = entryArrayList.get(iterator);
                iterator++;
                return result;
            }

            @Override
            public void remove() {
                HashMap.this.remove(entryArrayList.get(iterator).getKey());
                entryArrayList.remove(iterator);
            }
        };
    }
}

class Entry<K, V> {
    private final K key;
    private final V value;

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}