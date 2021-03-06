package datastructures.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    HashMap<Integer, Integer> hashMap = new HashMap();

    @Test
    void put_ValueReturnedWhePut_True() {
        hashMap.put(1, 1);
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);
        hashMap.put(3, 1500);
        hashMap.put(12, null);
        hashMap.put(4, 4);
        hashMap.put(5, 5);
        hashMap.put(6, 6);
        hashMap.put(6, 1500);
        hashMap.put(7, null);
        hashMap.put(null, null);

        assertEquals(1, hashMap.get(1));
        assertEquals(2, hashMap.get(2));
        assertEquals(1500, hashMap.get(3));
        assertEquals(4, hashMap.get(4));
        assertEquals(5, hashMap.get(5));
        assertEquals(1500, hashMap.get(6));
        assertNull(hashMap.get(null));
    }

    @Test
    void put_WithTheSameKey_True() {
        hashMap.put(1, 1);
        hashMap.put(1, 2);
        hashMap.put(1, 3);
        hashMap.put(1, 4);
        hashMap.put(1, 5);
        assertEquals(5, hashMap.get(1));
    }


    @Test
    void get_FromStartOfMap_True() {
        hashMap.put(1, 1);
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);
        hashMap.put(3, 1500);
        hashMap.put(12, null);
        hashMap.put(4, 4);
        hashMap.put(5, 5);
        hashMap.put(6, 6);
        hashMap.put(6, 1500);
        hashMap.put(7, null);

        assertEquals(1, hashMap.get(1));
    }

    @Test
    void get_FromMiddleOfMap_True() {
        hashMap.put(1, 1);
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);
        hashMap.put(3, 1500);
        hashMap.put(12, null);
        hashMap.put(4, 4);
        hashMap.put(5, 5);
        hashMap.put(6, 6);
        hashMap.put(6, 1500);
        hashMap.put(7, null);

        assertEquals(4, hashMap.get(4));
    }

    @Test
    void get_FromEndOfMap_True() {
        hashMap.put(1, 1);
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);
        hashMap.put(3, 1500);
        hashMap.put(12, null);
        hashMap.put(4, 4);
        hashMap.put(5, 5);
        hashMap.put(6, 6);
        hashMap.put(6, 1500);
        hashMap.put(7, null);

        assertNull(hashMap.get(7));
    }

    @Test
    void get_NotPresentElement_Null() {
        assertNull(hashMap.get(1));
    }

    @Test
    void size_SizeOfMap_True() {
        for (int i = 0; i < 10000; i++) {
            hashMap.put(i, i);
        }

        assertEquals(10000, hashMap.size());
    }

    @Test
    void containsKey_Contains_True() {
        for (int i = 0; i < 10000; i++) {
            hashMap.put(i, i);
            assertTrue(hashMap.containsKey(i));
        }
    }

    @Test
    void containsKey_DoNotContains_False() {
        hashMap.put(1, 1);
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);
        hashMap.put(3, 1500);
        hashMap.put(12, null);
        hashMap.put(4, 4);
        hashMap.put(5, 5);
        hashMap.put(6, 6);
        hashMap.put(6, 1500);
        hashMap.put(7, null);

        assertFalse(hashMap.containsKey(15));
        assertFalse(hashMap.containsKey(44));
        assertFalse(hashMap.containsKey(null));
    }

    @Test
    void remove_KeyNotContainsAfterRemoved_False() {
        hashMap.put(1, 1);
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);
        hashMap.put(3, 1500);
        hashMap.put(12, null);
        hashMap.put(4, 4);
        hashMap.put(5, 5);
        hashMap.put(6, 6);
        hashMap.put(6, 1500);
        hashMap.put(7, null);
        hashMap.put(null, null);

        hashMap.remove(1);
        hashMap.remove(2);
        hashMap.remove(12);
        hashMap.remove(7);
        hashMap.remove(null);

        assertFalse(hashMap.containsKey(1));
        assertFalse(hashMap.containsKey(2));
        assertFalse(hashMap.containsKey(12));
        assertFalse(hashMap.containsKey(7));
        assertFalse(hashMap.containsKey(null));
    }

    @Test
    void remove_ReturnValueDeletedElement_True() {
        hashMap.put(1, 1);
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);
        hashMap.put(3, 1500);
        hashMap.put(12, null);
        hashMap.put(4, 4);
        hashMap.put(5, 5);
        hashMap.put(6, 6);
        hashMap.put(6, 1500);
        hashMap.put(7, null);

        assertEquals(1, hashMap.remove(1));
        assertEquals(2, hashMap.remove(2));
        assertNull(hashMap.remove(12));
        assertEquals(1500, hashMap.remove(6));
    }

    @Test
    void remove_ReturnNullWhenRemoveNotPresentElement_Null() {
        assertNull(hashMap.remove(1));
    }

    @Test
    void getFromDifferentPositions() {
        for (int i = 0; i < 1000; i++) {
            hashMap.put(i, i);
        }

        for (int i = 0; i < 1000; i++) {
            assertEquals(i, hashMap.get(i));
        }
    }

    @Test
    public void testHasNext_ReturnTrueIfHaz_TestHas() {
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);

        var iterator = hashMap.iterator();

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNext_ReturnNextEntry_Entry() {
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);
        var iterator = hashMap.iterator();
        assertEquals(1, iterator.next().getValue());
        assertEquals(2, iterator.next().getValue());
        assertEquals(3, iterator.next().getValue());
    }

    @Test
    public void testNext_ReturnIndexOutOfBoundIfHastNextValue() {
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);

        var iterator = hashMap.iterator();
        iterator.next();
        iterator.next();
        iterator.next();

        Exception exception = assertThrows(IndexOutOfBoundsException.class, iterator::next);
        String expectedMessage = "The next item does not exist";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testRemove_RemovePresentEntryFromHashMap() {
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);

        var iterator = hashMap.iterator();
        iterator.next();
        iterator.remove();

        String expected = "[Key = 2, Value = 2], [Key = 3, Value = 3]";
        String actual = hashMap.toString();
        assertEquals(expected, actual);
    }


}