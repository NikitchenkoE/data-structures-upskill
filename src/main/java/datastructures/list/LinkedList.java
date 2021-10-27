package datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements List<T>, Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.format("Index < 0 and > size, size = %s", size));
        }
        if (head == null) {
            head = tail = new Node<>(value, null, null);
            size++;
            return;
        } else if (index == size) {
            Node<T> newNode = new Node<>(value, tail, null);
            tail.next = newNode;
            tail = newNode;
            size++;
            return;
        }

        Node<T> listObject = find(index);

        if (listObject == head) {
            head = new Node<>(value, null, listObject);
            listObject.prev = head;
            size++;
            return;
        } else if (listObject.prev != null) {
            Node<T> newNode = new Node<>(value, listObject.prev, listObject);
            listObject.prev.next = newNode;
            listObject.prev = newNode;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        exceptionChecker(index);

        Node<T> listObject = find(index);
        if (index == 0) {
            listObject.next.prev = null;
            head = listObject.next;
        } else if (index == size - 1) {
            listObject.prev.next = null;
            tail = listObject.prev;
        } else {
            listObject.prev.next = listObject.next;
            listObject.next.prev = listObject.prev;
        }
        size--;
        return listObject.value;
    }

    @Override
    public T get(int index) {
        exceptionChecker(index);

        return find(index).value;
    }

    @Override
    public T set(T value, int index) {
        exceptionChecker(index);

        if (index <= size / 2) {
            Node<T> first = head;
            for (int i = 0; i < index; i++) {
                first = first.next;
            }
            first.value = value;
            return first.value;
        } else {
            Node<T> last = tail;
            for (int i = size - 1; i > index; i--) {
                last = last.prev;
            }
            last.value = value;
            return last.value;
        }
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
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
    public boolean contains(T value) {
        Node<T> first = head;
        for (int i = 0; i < size; i++) {
            if (first.value == value) {
                return true;
            }
            first = first.next;
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        int index = 0;
        for (Node<T> thisNode = head; thisNode != null; index++) {
            if (Objects.equals(value, thisNode.value)) {
                return index;
            }
            thisNode = thisNode.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        int index = size - 1;
        for (Node<T> thisNode = tail; thisNode != null; index--) {
            if (Objects.equals(value, thisNode.value)) {
                return index;
            }
            thisNode = thisNode.prev;
        }
        return -1;
    }

    @Override
    public String toString() {
        Node<T> first = head;
        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (int i = 0; ; i++) {
            stringBuilder.append(first.value);
            first = first.next;
            if (i == iMax)
                return stringBuilder.append(']').toString();
            stringBuilder.append(", ");
        }
    }

    private Node<T> find(int index) {
        if (index <= size / 2) {
            Node<T> first = head;
            for (int i = 0; i < index; i++) {
                first = first.next;
            }
            return first;
        } else {
            Node<T> last = tail;
            for (int i = size - 1; i > index; i--) {
                last = last.prev;
            }
            return last;
        }
    }

    private void exceptionChecker(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(String.format("Index < 0 and > size-1, size-1 = %s", size - 1));
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator() {
            Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public T next() {
                noSuchElement();

                T value = current.value;
                current = current.next;
                return value;
            }

            public void remove() {
                noSuchElement();

                if (current.prev == null) {
                    head = current.next;
                    current.next.prev = null;
                    current = head;
                } else if (current.next == null) {
                    tail = current.prev;
                    current.prev.next = null;
                    current = tail;
                } else {
                    Node<T> newCurrent = current.next;
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    current = newCurrent;
                }
                size--;
            }

            private void noSuchElement() {
                if (current == null) {
                    throw new NoSuchElementException("No such element");
                }
            }
        };
    }
}


class Node<T> {
    T value;

    Node<T> prev;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node<T> prev, Node<T> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}
