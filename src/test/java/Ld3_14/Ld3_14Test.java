/*
 * Author: Arturs Kuzmiks
 */

package Ld3_14;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Ld3_14Test {

    private Queue queue = new Queue(10);

    @Test
    void testIsEmpty(){
        assertTrue(queue.isEmpty());
        assertEquals(queue.size(),0);
    }

    @Test
    void testInsertWhileIsNotFull(){
        Random rd = new Random();
        while(!queue.isFull()){
            queue.enqueue(rd.nextInt(35)-15);
            queue.outQueue();
        }
        assertTrue(queue.isFull());
        assertEquals(queue.size(),10);
        System.out.println("\nSize: "+queue.size());
    }

    @Test
    void testDequeToEmpty(){
        Random rd = new Random();
        while(!queue.isFull()){
            queue.enqueue(rd.nextInt(35)-15);
        }
        queue.outQueue();
        while (!queue.isEmpty()){
            queue.dequeue();
            queue.outQueue();
        }
        assertTrue(queue.isEmpty());
        assertEquals(queue.size(),0);
    }


}
