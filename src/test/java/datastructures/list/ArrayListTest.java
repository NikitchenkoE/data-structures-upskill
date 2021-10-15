package datastructures.list;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    private final ArrayList arrayList = new ArrayList(10);

    @Test
    void add() {

        for (int i = 0; i < 10000; i++) {
            arrayList.add(String.format("%s", i), i);
        }

        assertEquals("0", arrayList.get(0));
        assertTrue(arrayList.contains("6500"));
    }

    @Test
    void addWithIndex() {
        for (int i = 0; i < 10000; i++) {
            arrayList.add(String.format("%s", i), i);
        }
        assertEquals("0", arrayList.get(0));
    }

    @Test
    void addWithIndexEx() {
        for (int i = 0; i < 100; i++) {
            arrayList.add(String.format("%s", i), i);
        }

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add("105", 101));

        String expectedMessage = "Index < 0 and > size, size = 100";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void get() {
        for (int i = 0; i < 10000; i++) {
            arrayList.add(String.format("%s", i), i);
        }

        assertEquals("150", arrayList.get(150));
        assertNotEquals("166", arrayList.get(150));
    }

    @Test
    void set() {
        for (int i = 0; i < 10000; i++) {
            arrayList.add(String.format("%s", i), i);
        }
        arrayList.set("Hello", 28);
        assertNotEquals("28", arrayList.get(28));
        assertEquals("Hello", arrayList.get(28));
    }

    @Test
    void clear() {
        for (int i = 0; i < 10000; i++) {
            arrayList.add(String.format("%s", i), i);
        }

        assertEquals("2558", arrayList.get(2558));
        arrayList.clear();
        assertTrue(arrayList.isEmpty());
    }

    @Test
    void size() {
        int size = 250;
        for (int i = 0; i < size; i++) {
            arrayList.add(String.format("%s", i), i);
        }
        assertEquals(size, arrayList.size());
    }

    @Test
    void contains() {
        arrayList.add("3");
        arrayList.add(null);

        assertTrue(arrayList.contains("3"));
        assertTrue(arrayList.contains(null));
    }

    @Test
    void indexOf() {
        for (int i = 0; i < 10000; i++) {
            arrayList.add(String.format("%s", i));
        }
        assertEquals(155, arrayList.indexOf("155"));
    }

    @Test
    void lastIndexOf() {
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("A");
        assertEquals(3, arrayList.lastIndexOf("A"));
    }

    @Test
    void remove() {
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("A");

        arrayList.remove(0);
        assertNotEquals("A", arrayList.get(0));
        assertEquals("B", arrayList.get(0));
        assertEquals(3, arrayList.size());
    }

    @Test
    void toStringTest() {
        arrayList.add("A");
        arrayList.add(null);
        arrayList.add("C");
        arrayList.add("A");
        String expectedStr = "[A, null, C, A]";
        assertEquals(expectedStr, arrayList.toString());
    }

    @Test
    void iteratorNext_findNextElement_true(){
        var iterator = arrayList.iterator();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        assertEquals(1,iterator.next());
        assertEquals(2,iterator.next());
        assertEquals(3,iterator.next());
    }

    @Test
    void iteratorNext_NoSuchElementExc_Exception(){
        arrayList.add(1);
        arrayList.add(1);
        arrayList.add(1);

        var iterator = arrayList.iterator();
        iterator.next();
        iterator.next();
        iterator.next();

        Exception exception = assertThrows(NoSuchElementException.class, iterator::next);

        String expectedMessage = "No such element";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void iteratorHasNext_HasNextElement_True(){
        var iterator = arrayList.iterator();
        arrayList.add(1);
        arrayList.add(1);
        assertTrue(iterator.hasNext());
    }

    @Test
    void iteratorHasNotNext_HasNextElement_False(){
        var iterator = arrayList.iterator();
        arrayList.add(1);
        arrayList.add(1);
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    void iteratorRemove_RemoveFirstElement_True(){
        var iterator = arrayList.iterator();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        String expectedMessage = "[2, 3]";
        iterator.remove();
        assertEquals(expectedMessage,arrayList.toString());
    }

    @Test
    void iteratorRemove_RemoveMiddleElement_True(){
        var iterator = arrayList.iterator();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        String expectedMessage = "[1, 3]";
        iterator.next();
        iterator.remove();
        assertEquals(expectedMessage,arrayList.toString());
    }

    @Test
    void iteratorRemove_RemoveLastElement_True(){
        var iterator = arrayList.iterator();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        String expectedMessage = "[1, 2]";
        iterator.next();
        iterator.next();
        iterator.remove();
        assertEquals(expectedMessage,arrayList.toString());
    }
}