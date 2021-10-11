package datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    private final ArrayList arrayList = new ArrayList(10);

    @Test
    void add() {

        for (int i = 0; i < 10000; i++) {
            arrayList.add(String.format("%s", i), i);
        }

        assertEquals(arrayList.get(0), "0");
        assertTrue(arrayList.contains("6500"));
    }

    @Test
    void addWithIndex() {
        for (int i = 0; i < 10000; i++) {
            arrayList.add(String.format("%s", i), i);
        }
        assertEquals(arrayList.get(0), "0");
    }

    @Test
    void addWithIndexEx() {
        for (int i = 0; i < 100; i++) {
            arrayList.add(String.format("%s", i), i);
        }

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            arrayList.add("105", 101);
        });

        String expectedMessage = "Index < 0 and > size, size = 100";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void get() {
        for (int i = 0; i < 10000; i++) {
            arrayList.add(String.format("%s", i), i);
        }

        assertEquals(arrayList.get(150), "150");
        assertNotEquals(arrayList.get(150), "166");
    }

    @Test
    void set() {
        for (int i = 0; i < 10000; i++) {
            arrayList.add(String.format("%s", i), i);
        }
        arrayList.set("Hello", 28);
        assertNotEquals(arrayList.get(28), "28");
        assertEquals(arrayList.get(28), "Hello");
    }

    @Test
    void clear() {
        for (int i = 0; i < 10000; i++) {
            arrayList.add(String.format("%s", i), i);
        }

        assertEquals(arrayList.get(2558), "2558");
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
        assertEquals(arrayList.lastIndexOf("A"), 3);
    }

    @Test
    void remove() {
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("A");

        arrayList.remove(0);
        assertNotEquals("A",arrayList.get(0));
        assertEquals("B",arrayList.get(0));
        assertEquals(3,arrayList.size());
    }

    @Test
    void toStringTest(){
        arrayList.add("A");
        arrayList.add(null);
        arrayList.add("C");
        arrayList.add("A");
        String expectedStr = "[A, null, C, A]";
        assertEquals(expectedStr,arrayList.toString());
    }
}