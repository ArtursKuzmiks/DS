/*
 * Author: Arturs Kuzmiks
 */

/*
 * Author: Arturs Kuzmiks
 */


package Heap;


class Heap {
    private int[] data;

    Heap() {
        this.data = new int[1000];
        this.data[0] = 0;
    }

    void insert(int data) {
        this.data[++this.data[0]] = data;
        bubbleUp(this.data[0]);
    }

    private void bubbleUp(int index) {
        int parIndex = index / 2;

        if (index == 1 || data[parIndex] < data[index])
            return;

        int parValue = data[parIndex];
        data[parIndex] = data[index];
        data[index] = parValue;

        bubbleUp(parIndex);
    }

    int remove() {
        int minValue = data[1];
        int lastValue = data[data[0]--];
        data[1] = lastValue;
        sinkDown(1);
        return minValue;
    }

    private boolean isLeaf(int index) {
        return (index * 2) > data[0];
    }

    private void sinkDown(int index) {
        if (isLeaf(index)) return;

        int leftChildIndex = index * 2;
        int rightChildIndex = leftChildIndex + 1;
        int minChildIndex;

        if (rightChildIndex > data[0]) {
            minChildIndex = leftChildIndex;
        } else {
            minChildIndex = (data[leftChildIndex] <
                    data[rightChildIndex]) ? leftChildIndex : rightChildIndex;
        }

        if (data[index] < data[minChildIndex]) return;

        int childValue = data[minChildIndex];
        data[minChildIndex] = data[index];
        data[index] = childValue;

        sinkDown(minChildIndex);
    }

    int minValue() {
        return data[1];
    }

    int length() {
        return data[0];
    }

    int[] toArray() {
        int[] arr = new int[data[0]];
        System.arraycopy(data, 1, arr, 0, data[0]);
        return arr;
    }

    void printHeapList() {
        System.out.println();
        for (int i = 1; i <= data[0]; i++)
            System.out.print(data[i] + "\t");
    }

}
