package datastructures.list;

public class Node {
    Object value;

    Node prev;
    Node next;

    public Node(Object value) {
        this.value = value;
    }

    public Node(Object value, Node prev) {
        this.value = value;
        this.prev = prev;
    }
}
