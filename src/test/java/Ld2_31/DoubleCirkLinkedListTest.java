package Ld2_31;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DoubleCirkLinkedListTest {

    private DoubleCirkLinkedList doubleCirkLinkedList = new DoubleCirkLinkedList(10);

    @Test
    void testIsEmpty() {
        assertTrue(doubleCirkLinkedList.isEmpty());
        assertEquals(doubleCirkLinkedList.getSize(), 0);
    }

    @Test
    void testInsertWhileIsNotFull() {
        Random rd = new Random();
        doubleCirkLinkedList.insertAtFirst(rd.nextInt(30) - 15);
        doubleCirkLinkedList.listOut();
        doubleCirkLinkedList.insertAtLast(rd.nextInt(30) - 15);
        doubleCirkLinkedList.listOut();

        int i = 1;
        while (!doubleCirkLinkedList.isFull()) {
            doubleCirkLinkedList.insertAt(rd.nextInt(30) - 15, i);
            doubleCirkLinkedList.listOut();
            i++;
        }
        assertTrue(doubleCirkLinkedList.isFull());
        assertEquals(doubleCirkLinkedList.getSize(), 10);
        System.out.println("\nSize: "+ doubleCirkLinkedList.getSize());
    }

    @Test
    void testDeleteToEmpty() {
        Random rd = new Random();

        doubleCirkLinkedList.insertAtFirst(rd.nextInt(30) - 15);
        doubleCirkLinkedList.insertAtLast(rd.nextInt(30) - 15);

        int i = 1;
        while (!doubleCirkLinkedList.isFull()) {
            doubleCirkLinkedList.insertAt(rd.nextInt(30) - 15, i);
            i++;
        }

        doubleCirkLinkedList.listOut();

        while (!doubleCirkLinkedList.isEmpty()) {
            int n = rd.nextInt(doubleCirkLinkedList.getSize()) + 1;
            doubleCirkLinkedList.deleteElementAt(n);
            doubleCirkLinkedList.listOut();
        }

        assertTrue(doubleCirkLinkedList.isEmpty());
        assertEquals(doubleCirkLinkedList.getSize(), 0);

    }
}
