package datastructures.list;

public class LinkedList implements List {
    private Node head;
    private Node tail;
    private int size = 0;

    @Override
    public void add(Object value) {
//        Node last = tail;
//        Node newNode = new Node(value);
//        tail = newNode;
//        if (last == null) {
//            head = newNode;
//            size++;
//        } else {
//            last.next = newNode;
//            newNode.prev = last;
//            size++;
//        }
        add(value, size);

    }

    @Override
    public void add(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.format("Index < 0 and > size-1, size-1 = %s", size - 1));
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
        }else
        if (listObject.prev != null) {
            Node newNode = new Node(value, listObject.prev, listObject);
            listObject.prev.next = newNode;
            listObject.prev = newNode;
        }
        size++;
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(String.format("Index < 0 and > size-1, size-1 = %s", size - 1));
        }
        Node listObject = find(index);
        if (index == 0) {
            head = listObject.next;
        } else {
            listObject.prev.next = listObject.next;
            listObject.next.prev = listObject.prev;
        }
        size--;
        return listObject.value;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(String.format("Index < 0 and > size-1, size-1 = %s", size - 1));
        }
        return find(index).value;
    }

    @Override
    public Object set(Object value, int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(String.format("Index < 0 and > size-1, size-1 = %s", size - 1));
        }

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
        boolean result = false;
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (first.value == null) {
                    result = true;
                    break;
                }
                first = first.next;
            }
        } else
            for (int i = 0; i < size; i++) {
                if (first.value==value) {
                    result = true;
                    break;
                }
                first = first.next;
            }

        return result;
    }

    @Override
    public int indexOf(Object value) {
        int index = -1;
        Node listObject = head;
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (listObject.value == null) {
                    index = i;
                    break;
                }
                listObject = listObject.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (listObject.value.equals(value)) {
                    index = i;
                    break;
                }
                listObject = listObject.next;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object value) {
        int index = -1;
        Node listObject = tail;
        if (value == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (listObject.value == null) {
                    index = i;
                    break;
                }
                listObject = listObject.prev;
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (listObject.value == value) {
                    index = i;
                    break;
                }
                listObject = listObject.prev;
            }
        }
        return index;
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
            stringBuilder.append(String.valueOf(first.value));
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
}
