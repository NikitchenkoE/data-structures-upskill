package datastructures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    LinkedList linkedList = new LinkedList();

    @BeforeEach
    void before() {
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
        assertEquals("1", linkedList.get(1));
        assertEquals("5", linkedList.get(5));
        assertEquals(null, linkedList.get(7));
    }

    @Test
    void add_SizeChanged_True() {
        linkedList.add(1);
        linkedList.add(1);
        linkedList.add(1);
        assertEquals(12, linkedList.size());
    }

    @Test
    void addNumberWithIndex_Exception_ExpectException() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.add(101,101);
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Index < 0 and > size, size = 9";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void addNumberWithIndex_AddInStartOfList_True() {
        String testMessage = "testMessage";
        linkedList.add(testMessage, 0);
        assertEquals(testMessage, linkedList.get(0));
    }

    @Test
    void addNumberWithIndex_AddInEndOfList_True() {
        String testMessage = "testMessage";
        linkedList.add(testMessage, 7);
        assertEquals(testMessage, linkedList.get(7));
    }

    @Test
    void addNumberWithIndex_AddInMiddleOfList_True() {
        String testMessage = "testMessage";
        linkedList.add(testMessage, 4);
        assertEquals(testMessage, linkedList.get(4));
    }

    @Test
    void addNumberWithIndex_SizeChangedStart_True() {
        String testMessage = "testMessage";
        linkedList.add(testMessage, 0);
        assertEquals(10, linkedList.size());
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
    void remove_Exeption_True(){
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.remove(101);
        });
        String expectedMessage = "Index < 0 and > size-1, size-1 = 8";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void remove_FromStartOfList_True(){
        linkedList.remove(0);
        assertEquals("1",linkedList.get(0));
        assertEquals(8,linkedList.size());
    }

    @Test
    void remove_FromMiddleOfList_True(){
        linkedList.remove(4);
        assertEquals("5",linkedList.get(4));
        assertEquals(8,linkedList.size());
    }

    @Test
    void remove_FromEndOfList_True(){
        linkedList.remove(8);
        assertNull(linkedList.get(7));
        assertEquals(8,linkedList.size());
    }



    @Test
    void get_FromStartOfList_True() {
        assertEquals("0", linkedList.get(0));
    }
    @Test
    void get_FromEndOfList_True() {
        assertEquals("8", linkedList.get(8));
    }
    @Test
    void get_FromMiddleOfList_True() {
        assertEquals("4", linkedList.get(4));
    }

    @Test
    void get_Exeption_True(){
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.get(101);
        });

        String expectedMessage = "Index < 0 and > size-1, size-1 = 8";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void set() {
        String testMessage = "NewObject";
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.set(testMessage, 101);
        });

        String expectedMessage = "Index < 0 and > size-1, size-1 = 8";
        String actualMessage = exception.getMessage();

        linkedList.set(testMessage, 3);
        assertEquals(linkedList.get(3), testMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void clear() {
        linkedList.clear();
        assertEquals(0, linkedList.size());
    }

    @Test
    void size() {
        assertEquals(9, linkedList.size());
        assertNotEquals(15, linkedList.size());
    }

    @Test
    void isEmpty() {
        linkedList.clear();
        assertTrue(linkedList.isEmpty());
    }

    @Test
    void contains_TestFindInStartOfList_True() {
        assertTrue(linkedList.contains("0"));
    }

    @Test
    void contains_TestFindInMiddleOfList_True() {
        assertTrue(linkedList.contains("0"));
    }

    @Test
    void contains_TestFindInEndOfList_True() {
        assertTrue(linkedList.contains("8"));
    }

    @Test
    void contains_TestFindNull_True() {
        assertTrue(linkedList.contains(null));
    }

    @Test
    void indexOf_IndexOfFirst_True(){
        assertEquals(0, linkedList.indexOf("0"));
    }

    @Test
    void indexOf_IndexOfMiddle_True(){
        assertEquals(4, linkedList.indexOf("4"));
        assertEquals(7, linkedList.indexOf(null));
    }

    @Test
    void indexOf_IndexOfLast_True(){
        assertEquals(8, linkedList.indexOf("8"));
    }

    @Test
    void indexOf_NotPresent_True(){
        assertEquals(-1, linkedList.indexOf("NotPresent"));
    }

    @Test
    void lastIndexOf() {
        assertEquals(0, linkedList.lastIndexOf("0"));
//        assertEquals(7, linkedList.lastIndexOf(null));
        assertEquals(6, linkedList.lastIndexOf("6"));
        assertEquals(4, linkedList.lastIndexOf("4"));
    }
    @Test
    void lastIndexOf_LastIndexOfFirst_True(){
        assertEquals(0, linkedList.lastIndexOf("0"));
    }

    @Test
    void lastIndexOf_LastIndexOfMiddle_True(){
        assertEquals(6, linkedList.lastIndexOf("6"));
        assertEquals(4, linkedList.lastIndexOf("4"));
        assertEquals(7, linkedList.lastIndexOf(null));
    }

    @Test
    void lastIndexOf_LastIndexOfLast_True(){
        assertEquals(8, linkedList.lastIndexOf("8"));
    }

    @Test
    void lastIndexOf_NotPresent_True(){
        assertEquals(-1, linkedList.lastIndexOf("NotPresent"));
    }

    @Test
    void testToString() {
        String expectedMessage = "[0, 1, 2, 3, 4, 5, 6, null, 8]";
        assertEquals(expectedMessage, linkedList.toString());
    }
}