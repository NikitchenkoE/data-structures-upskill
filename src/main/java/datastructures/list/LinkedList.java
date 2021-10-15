package datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList implements List, Iterable {
    private Node head;
    private Node tail;
    private int size = 0;

    @Override
    public void add(Object value) {
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.format("Index < 0 and > size, size = %s", size));
        }
        if (head == null) {
            head = tail = new Node(value, null, null);
            size++;
            return;
        } else if (head == tail) {
            tail = new Node(value, head, null);
            head.next = tail;
            size++;
            return;
        } else if (index == size) {
            Node newNode = new Node(value, tail, null);
            tail.next = newNode;
            tail = newNode;
            size++;
            return;
        }

        Node listObject = find(index);

        if (listObject == head) {
            head = new Node(value, null, listObject);
            listObject.prev = head;
            size++;
            return;
        } else if (listObject.prev != null) {
            Node newNode = new Node(value, listObject.prev, listObject);
            listObject.prev.next = newNode;
            listObject.prev = newNode;
        }
        size++;
    }

    @Override
    public Object remove(int index) {
        exceptionChecker(index);

        Node listObject = find(index);
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
    public Object get(int index) {
        exceptionChecker(index);

        return find(index).value;
    }

    @Override
    public Object set(Object value, int index) {
        exceptionChecker(index);

        if (index <= size / 2) {
            Node first = head;
            for (int i = 0; i < index; i++) {
                first = first.next;
            }
            first.value = value;
            return first.value;
        } else {
            Node last = tail;
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
    public boolean contains(Object value) {
        Node first = head;
        for (int i = 0; i < size; i++) {
            if (first.value == value) {
                return true;
            }
            first = first.next;
        }
        return false;
    }

    @Override
    public int indexOf(Object value) {
        int index = 0;
        for (Node thisNode = head; thisNode != null; index++) {
            if (Objects.equals(value, thisNode.value)) {
                return index;
            }
            thisNode = thisNode.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        int index = size - 1;
        for (Node thisNode = tail; thisNode != null; index--) {
            if (Objects.equals(value, thisNode.value)) {
                return index;
            }
            thisNode = thisNode.prev;
        }
        return -1;
    }

    @Override
    public String toString() {
        Node first = head;
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

    private Node find(int index) {
        if (index <= size / 2) {
            Node first = head;
            for (int i = 0; i < index; i++) {
                first = first.next;
            }
            return first;
        } else {
            Node last = tail;
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
    public Iterator iterator() {
        return new Iterator() {
            Node current = head;

            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public Object next() {
                noSuchElement();

                Object value = current.value;
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
                    Node newCurrent = current.next;
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


class Node {
    Object value;

    Node prev;
    Node next;

    public Node(Object value) {
        this.value = value;
    }

    public Node(Object value, Node prev, Node next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}
