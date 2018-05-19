/*
 * Author: Arturs Kuzmiks
 */

/*
 * Author: Arturs Kuzmiks
 */

/*
 * Author: Arturs Kuzmiks
 */

/*
 * Author: Arturs Kuzmiks
 */

package Ld2_14;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


class Ld2_14Test {

    private CircularLinkedList circList = new CircularLinkedList(10);

    @Test
    void testIsEmpty() {
        assertTrue(circList.isEmpty());
        assertEquals(circList.getSize(), 0);
    }

    @Test
    void testInsertWhileIsNotFull() {
        Random rd = new Random();
        while (!circList.isFull()) {
            circList.insert(rd.nextInt(35) - 15);
            circList.listOut();
        }
        assertTrue(circList.isFull());
        assertEquals(circList.getSize(), 10);
        System.out.println("\nSize: "+circList.getSize());
    }

    @Test
    void testDeleteToEmpty() {
        Random rd = new Random();

        while (!circList.isFull()) {
            circList.insert(rd.nextInt(35) - 15);
        }
        circList.listOut();

        while (!circList.isEmpty()) {
            int n = rd.nextInt(circList.getSize()) + 1;
            circList.deleteElementAt(n);
            circList.listOut();
        }

        assertTrue(circList.isEmpty());
        assertEquals(circList.getSize(), 0);

    }
}
