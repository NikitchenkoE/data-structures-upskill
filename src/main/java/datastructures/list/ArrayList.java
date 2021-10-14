package datastructures.list;

import java.util.Arrays;

public class ArrayList implements List {
    private static final int DEFAULT_CAPACITY = 16;
    private int size = 0;
    private Object[] array;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initCapacity) {
        array = new Object[initCapacity];
    }

    @Override
    public void add(Object value) {
        if (size == array.length - 1) {
            growth();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(Object value, int index) {
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
    public Object get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(String.format("Index < 0 and > size-1, size-1 = %s", size - 1));
        }
        return array[index];
    }

    @Override
    public Object set(Object value, int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(String.format("Index < 0 and > size-1, size-1 = %s", size - 1));
        }
        Object oldValue = array[index];
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
    public Object remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(String.format("Index < 0 and > size-1, size-1 = %s", size - 1));
        }
        System.arraycopy(array, index + 1, array, index, array.length - 1 - index);
        size--;
        return array;
    }

    @Override
    public String toString() {

        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; ; i++) {
            stringBuilder.append(String.valueOf(array[i]));
            if (i == iMax)
                return stringBuilder.append(']').toString();
            stringBuilder.append(", ");
        }
    }

    private void growth() {
        Object[] newArray = new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}
