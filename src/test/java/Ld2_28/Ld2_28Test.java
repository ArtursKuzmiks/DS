package Ld2_28;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


class Ld2_28Test {

    private DoubleLinkedList doubleList = new DoubleLinkedList(10);

    @Test
    void testIsEmpty() {
        assertTrue(doubleList.isEmpty());
        assertEquals(doubleList.getSize(), 0);
    }

    @Test
    void testInsertWhileIsNotFull() {
        Random rd = new Random();
        while (!doubleList.isFull()) {
            doubleList.insert(rd.nextInt(35) - 15);
            doubleList.listOut();
        }
        assertTrue(doubleList.isFull());
        assertEquals(doubleList.getSize(), 10);
        System.out.println("\nSize: "+doubleList.getSize());
    }

    @Test
    void testDeleteToEmpty() {
        Random rd = new Random();

        while (!doubleList.isFull()) {
            doubleList.insert(rd.nextInt(35) - 15);
        }
        doubleList.listOut();

        while (!doubleList.isEmpty()) {
            int n = rd.nextInt(doubleList.getSize()) + 1;
            doubleList.deleteElementAt(n);
            doubleList.listOut();
        }

        assertTrue(doubleList.isEmpty());
        assertEquals(doubleList.getSize(), 0);

    }
}
