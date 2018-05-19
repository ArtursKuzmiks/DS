/*
 * Author: Arturs Kuzmiks
 */

package Heap;

import org.junit.jupiter.api.Test;
import HeapSort.*;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {

    private Heap heap = new Heap();
    private HeapSort heapSort = new HeapSort();


    @Test
    public void insertTest() {

        heap.insert(55);
        heap.insert(10);
        heap.insert(20);
        heap.insert(25);
        heap.insert(30);
        heap.insert(40);
        heap.insert(42);
        heap.insert(50);
        heap.insert(52);

    }

    @Test
    public void removeTest() {
        heap.insert(55);
        heap.insert(10);
        heap.insert(20);
        heap.insert(25);
        heap.insert(30);
        heap.insert(40);
        heap.insert(42);
        heap.insert(50);
        heap.insert(52);

        assertEquals(10, heap.remove());

    }

    @Test
    public void minValueTest() {
        heap.insert(55);
        heap.insert(10);
        heap.insert(20);
        assertEquals(10, heap.remove());
        assertEquals(20, heap.minValue());
    }

    @Test
    public void heapSortTest() {
        heap.insert(55);
        heap.insert(10);
        heap.insert(20);
        heap.insert(25);
        heap.insert(30);
        heap.insert(40);
        heap.insert(42);
        heap.insert(50);
        heap.insert(52);

        int[] arr = heap.toArray();
        heapSort.sort(arr);
        assertEquals(55, arr[arr.length - 1]);

    }

}