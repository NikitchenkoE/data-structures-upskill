package datastructures.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T>,Iterable<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private int size = 0;
    private T[] array;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initCapacity) {
        array =(T[]) new Object[initCapacity];
    }

    @Override
    public void add(T value) {
        if (size == array.length - 1) {
            growth();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.format("Index < 0 and > size, size = %s", size));
        } else if (size == array.length - 1) {
            growth();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(String.format("Index < 0 and > size-1, size-1 = %s", size - 1));
        }
        return array[index];
    }

    @Override
    public T set(T value, int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(String.format("Index < 0 and > size-1, size-1 = %s", size - 1));
        }
        T oldValue = array[index];
        array[index] = value;
        return oldValue;
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (array[i] == null) {
            } else if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        if (value == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        }
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(String.format("Index < 0 and > size-1, size-1 = %s", size - 1));
        }
        System.arraycopy(array, index + 1, array, index, array.length - 1 - index);
        size--;
        return (T) array;
    }

    @Override
    public String toString() {

        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; ; i++) {
            stringBuilder.append((array[i]));
            if (i == iMax)
                return stringBuilder.append(']').toString();
            stringBuilder.append(", ");
        }
    }

    private void growth() {
        T[] newArray = (T[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator(){
        int present=0;
            @Override
            public boolean hasNext() {
                return array[present+1]!=null;
            }

            @Override
            public T next() {
                noSuchElement();

                    var value = array[present];
                    present++;
                    return value;
            }

            @Override
            public void remove() {
                noSuchElement();
                System.arraycopy(array, present + 1, array, present, array.length - 1 - present);
                size--;
            }

            private void noSuchElement(){
                if (array[present]==null) {
                    throw new NoSuchElementException("No such element");
                }
            }

        };
    }
}
