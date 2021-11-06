package datastructures.map;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class HashMap<K, V> implements Map<K, V>, Iterable<HashMap<K, V>.Entry<K, V>> {
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
        ArrayList<Entry<K, V>> bucket = buckets[findBucket(key)];
        if (bucket == null) {
            return false;
        } else {
            return entryPresent(bucket, key);
        }
    }

    @Override
    public V remove(K key) {
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
        if (key != null) {
            int bucketNumber = key.hashCode() % buckets.length;
            if (bucketNumber < 0) {
                return bucketNumber * (-1);
            }
            return bucketNumber;
        } else return 0;
    }

    private int findNewBucket(K key, int length) {
        if (key != null) {
            int bucketNumber = key.hashCode() % length;
            if (bucketNumber < 0) {
                return bucketNumber * (-1);
            }
            return bucketNumber;
        } else return 0;
    }

    private V findEntry(ArrayList<Entry<K, V>> bucket, K key) {
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.getKey(), key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private boolean entryPresent(ArrayList<Entry<K, V>> bucket, K key) {
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.getKey(), key)) {
                return true;
            }
        }
        return false;
    }

    private void setEntryPresent(ArrayList<Entry<K, V>> bucket, K key, Entry<K, V> newEntry) {
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.getKey(), key)) {
                bucket.remove(entry);
                bucket.add(newEntry);
            }
        }
    }

    private V deleteEntry(ArrayList<Entry<K, V>> bucket, K key) {
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.getKey(), key)) {
                var result = entry.getValue();
                bucket.remove(entry);
                return result;
            }
        }
        return null;
    }


    private void grow() {
        ArrayList<Entry<K, V>>[] newBuckets = new ArrayList[size * 2];
        var iterator = iterator();
        while (iterator.hasNext()) {
            var entry = iterator.next();
            var bucketIndex = findNewBucket(entry.getKey(), newBuckets.length);
            var bucket = newBuckets[bucketIndex];
            if (bucket == null) {
                bucket = new ArrayList<>();
                newBuckets[bucketIndex] = bucket;
            }
            bucket.add(entry);
        }
        buckets = newBuckets;
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
    public Iterator<HashMap<K, V>.Entry<K, V>> iterator() {
        return new Iterator<>() {
            private int iterator = 0;
            private int bucketIterator = 0;
            private int count = 0;
            private Entry<K, V> thisEntry;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public Entry<K, V> next() {
                Entry<K, V> entry = null;
                for (int i = iterator; i < buckets.length; i++) {
                    ArrayList<Entry<K, V>> bucket = buckets[i];
                    if (bucket != null && bucketIterator < bucket.size()) {
                        for (int t = bucketIterator; t < bucket.size(); t++) {
                            if (bucket.get(t) != null) {
                                entry = bucket.get(t);
                                thisEntry = entry;
                                bucketIterator++;
                                count++;
                                break;
                            }
                        }
                        break;
                    } else {
                        iterator++;
                        bucketIterator = 0;
                    }
                }
                if (entry == null) {
                    throw new IndexOutOfBoundsException("The next item does not exist");
                }

                return entry;
            }

            @Override
            public void remove() {
                var bucket = buckets[iterator];
                bucket.remove(thisEntry);
            }
        };
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

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}