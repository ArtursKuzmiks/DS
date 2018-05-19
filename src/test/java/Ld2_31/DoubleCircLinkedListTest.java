/*
 * Author: Arturs Kuzmiks
 */

/*
 * Author: Arturs Kuzmiks
 */

package Ld2_31;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DoubleCircLinkedListTest {

    private DoubleCircLinkedList doubleCircLinkedList = new DoubleCircLinkedList(10);

    @Test
    void testIsEmpty() {
        assertTrue(doubleCircLinkedList.isEmpty());
        assertEquals(doubleCircLinkedList.getSize(), 0);
    }

    @Test
    void testInsertWhileIsNotFull() {
        Random rd = new Random();
        doubleCircLinkedList.insertAtFirst(rd.nextInt(30) - 15);
        doubleCircLinkedList.listOut();
        doubleCircLinkedList.insertAtLast(rd.nextInt(30) - 15);
        doubleCircLinkedList.listOut();

        int i = 1;
        while (!doubleCircLinkedList.isFull()) {
            doubleCircLinkedList.insertAt(rd.nextInt(30) - 15, i);
            doubleCircLinkedList.listOut();
            i++;
        }
        assertTrue(doubleCircLinkedList.isFull());
        assertEquals(doubleCircLinkedList.getSize(), 10);
        System.out.println("\nSize: "+ doubleCircLinkedList.getSize());
    }

    @Test
    void testDeleteToEmpty() {
        Random rd = new Random();

        doubleCircLinkedList.insertAtFirst(rd.nextInt(30) - 15);
        doubleCircLinkedList.insertAtLast(rd.nextInt(30) - 15);

        int i = 1;
        while (!doubleCircLinkedList.isFull()) {
            doubleCircLinkedList.insertAt(rd.nextInt(30) - 15, i);
            i++;
        }

        doubleCircLinkedList.listOut();

        while (!doubleCircLinkedList.isEmpty()) {
            int n = rd.nextInt(doubleCircLinkedList.getSize()) + 1;
            doubleCircLinkedList.deleteElementAt(n);
            doubleCircLinkedList.listOut();
        }

        assertTrue(doubleCircLinkedList.isEmpty());
        assertEquals(doubleCircLinkedList.getSize(), 0);

    }
}
