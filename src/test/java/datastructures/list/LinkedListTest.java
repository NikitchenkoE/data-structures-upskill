package datastructures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    LinkedList linkedList = new LinkedList();
    @BeforeEach
    void before(){
        linkedList.add("0");
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");
        linkedList.add("6");
        linkedList.add(null);
        linkedList.add("8");

    }

    @Test
    void add_NumberInList_True() {
        assertEquals("1",linkedList.get(1));
        assertEquals("5",linkedList.get(5));
        assertEquals(null,linkedList.get(7));
    }

    @Test
    void add_SizeChanged_True() {
        linkedList.add(1);
        linkedList.add(1);
        linkedList.add(1);
        assertEquals(12,linkedList.size());
    }

    @Test
    void addNumberWithIndex_Exception_ExpectException(){
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.remove(101);
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Index < 0 and > size-1, size-1 = 8";
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void addNumberWithIndex_AddInStartOfList_True(){
        String testMessage = "testMessage";
        linkedList.add(testMessage,0);
        assertEquals(testMessage,linkedList.get(0));
    }

    @Test
    void addNumberWithIndex_AddInEndOfList_True(){
        String testMessage = "testMessage";
        linkedList.add(testMessage,7);
        assertEquals(testMessage,linkedList.get(7));
    }

    @Test
    void addNumberWithIndex_AddInMiddleOfList_True(){
        String testMessage = "testMessage";
        linkedList.add(testMessage,4);
        assertEquals(testMessage,linkedList.get(4));
    }

    @Test
    void addNumberWithIndex_SizeChangedStart_True(){
        String testMessage = "testMessage";
        linkedList.add(testMessage,0);
        assertEquals(10,linkedList.size());
    }

    @Test
    void addNumberWithIndex_SizeChangedMiddle_True() {
        String testMessage = "testMessage";
        linkedList.add(testMessage, 4);
        assertEquals(10, linkedList.size());
    }

    @Test
    void addNumberWithIndex_SizeChangedEnd_True() {
        String testMessage = "testMessage";
        linkedList.add(testMessage, 8);
        assertEquals(10, linkedList.size());
    }

    @Test
    void remove() {
        linkedList.remove(0);
        linkedList.remove(5);
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.remove(101);
        });
        String expectedMessage = "Index < 0 and > size-1, size-1 = 6";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        assertEquals("5",linkedList.get(0));
        assertNull(linkedList.get(5));
    }

    @Test
    void get() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.get(101);
        });

        String expectedMessage = "Index < 0 and > size-1, size-1 = 8";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        assertEquals("5",linkedList.get(1));
        assertEquals("5",linkedList.get(5));
        assertEquals("6",linkedList.get(6));
        assertEquals(null,linkedList.get(7));
    }

    @Test
    void set() {
        String testMessage = "NewObject";
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.set(testMessage,101);
        });

        String expectedMessage = "Index < 0 and > size-1, size-1 = 8";
        String actualMessage = exception.getMessage();

        linkedList.set(testMessage,3);
        assertEquals(linkedList.get(3), testMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void clear() {
        linkedList.clear();
        assertEquals(0,linkedList.size());
    }

    @Test
    void size() {
        assertEquals(9,linkedList.size());
        assertNotEquals(15,linkedList.size());
    }

    @Test
    void isEmpty() {
        linkedList.clear();
        assertTrue(linkedList.isEmpty());
    }

    @Test
    void contains() {
        assertTrue(linkedList.contains("5"));
        assertTrue(linkedList.contains(null));
    }

    @Test
    void indexOf() {
        assertEquals(1,linkedList.indexOf("5"));
        assertEquals(7, linkedList.indexOf(null));
    }

    @Test
    void lastIndexOf() {
        assertEquals(5,linkedList.lastIndexOf("5"));
        assertEquals(7, linkedList.lastIndexOf(null));
        assertEquals(6, linkedList.lastIndexOf("6"));
        assertEquals(4, linkedList.lastIndexOf("2"));
    }

    @Test
    void testToString(){
        String expectedMessage = "[2, 5, 9, 8, 2, 5, 6, null, 7]";
        assertEquals(expectedMessage,linkedList.toString());
    }
}