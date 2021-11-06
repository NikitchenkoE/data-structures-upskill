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
        V returnValue = null;
        Entry<K, V> entry = new Entry<>(key, value);
        int bucketNumber = findBucket(key);
        ArrayList<Entry<K, V>> bucket = buckets[bucketNumber];
        if (bucket == null) {
            bucket = new ArrayList<>();
            bucket.add(entry);
            buckets[bucketNumber] = bucket;
            size++;
        } else if (entryPresent(key)) {
            var presentEntry = findEntry(entry.key);
            presentEntry.value = value;
            returnValue = value;
        } else {
            bucket.add(entry);
            size++;
        }
        return returnValue;
    }

    @Override
    public V get(K key) {
        var bucket = buckets[findBucket(key)];
        if (bucket == null) {
            return null;
        }
        return findEntry(key).value;
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
            return entryPresent(key);
        }
    }

    @Override
    public V remove(K key) {
        ArrayList<Entry<K, V>> bucket = buckets[findBucket(key)];
        Entry<K, V> entry = new Entry<>(null, null);

        if (bucket == null) {
            return null;
        }

        var iterator = iterator();
        while (iterator.hasNext()) {
            entry = iterator.next();
            if (Objects.equals(entry.key, key)) {
                iterator.remove();
                break;
            }
        }

        return entry.value;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Entry<K, V> entry : this) {
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

    private Entry<K, V> findEntry(K key) {
        var iterator = iterator();
        Entry<K, V> entry = new Entry<>(null, null);
        while (iterator.hasNext()) {
            var presentEntry = iterator.next();
            if (Objects.equals(presentEntry.key, key)) {
                entry = presentEntry;
                break;
            }
        }
        return entry;
    }

    private boolean entryPresent(K key) {
        var iterator = iterator();
        boolean result = false;
        while (iterator.hasNext()) {
            var entry = iterator.next();
            if (Objects.equals(key, entry.key)) {
                result = true;
                break;
            }
        }
        return result;
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
                size--;
            }
        };
    }

    class Entry<K, V> {
        private final K key;
        private V value;

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