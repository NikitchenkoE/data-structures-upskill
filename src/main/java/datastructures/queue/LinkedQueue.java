package datastructures.queue;

public class LinkedQueue implements Queue {
    int size;
    Node head;


    @Override
    public void enqueue(Object value) {
        Node newNode = new Node(value);
        if (size==0){
            head=newNode;
        }else {
            Node current = head;
            while (current.next!=null){
                current = current.next;
            }
            current.next = newNode;
        }
    }

    @Override
    public Object dequeue() {
        size--;
        Object value = head.value;
        head = head.next;
        return value;
    }

    @Override
    public Object peek() {
        return head.value;
    }

    @Override
    public int size() {
        return size;
    }
}
