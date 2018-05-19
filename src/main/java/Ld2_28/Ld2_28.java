package Ld2_28;

/*
 * Author: Arturs Kuzmiks
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

class Node {
    private int data;
    private Node next;
    private Node prev;

    Node(int data, Node next, Node prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    int getData() {
        return data;
    }

    Node getNext() {
        return next;
    }

    Node getPrev() {
        return prev;
    }

    void setData(int data) {
        this.data = data;
    }

    void setNext(Node next) {
        this.next = next;
    }

    void setPrev(Node prev) {
        this.prev = prev;
    }

}

class DoubleLinkedList {

    private Node start;
    private int maxSize;
    private int size;

    DoubleLinkedList(int maxSize) {
        this.maxSize = maxSize;
        this.start = null;
        size = 0;
    }

    boolean isEmpty() {
        return start == null;
    }

    int getSize() {
        return size;
    }

    boolean isFull() {
        return size == maxSize;
    }

    void listOut() {
        if (!isEmpty()) {
            System.out.println("Saraksts:");
            Node node = start;
            while (node.getNext() != null) {
                System.out.print(node.getData() + "\t");
                node = node.getNext();
            }
            System.out.print(node.getData() + "\n");
        } else {
            System.out.println("Rinda ir tuksa");
        }
    }

    void deleteElementAt(int pos) {
        if (pos < 1 || pos > size) {
            System.out.println("Tadu elementu nav. (1-" + size + ")");
        } else {
            System.out.println("\nElements " + pos + " pozicija ar vertibu " +
                    getElementAt(pos - 1) + " tiek izniemts no saraksta");
            if (pos - 1 == 0 && size - 1 == 0) {
                start = null;
                size--;
            } else {
                if (pos == 1) {
                    start = getNodeAt(pos);
                    start.setPrev(null);
                } else {
                    if (pos == size) {
                        getNodeAt(pos - 2).setNext(null);
                    } else {
                        Node temp = getNodeAt(pos - 2);
                        temp.getNext().getNext().setPrev(temp.getNext().getPrev());
                        temp.setNext(temp.getNext().getNext());

                    }
                }
                size--;
            }
        }
    }

    void insert(int data) {
        if (start == null) {
            start = new Node(data, null, null);
            size++;
        } else {
            if (getNodeAt(size - 1).getData() <= data) {
                insertAtLast(data);
            } else {
                int pos = size - 1;
                while (pos >= 0 && getNodeAt(pos).getData() > data) {
                    pos--;
                }
                if (pos < 0) insertAtFirst(data);
                else insertAt(data, pos);
            }
        }
    }

    int intervalCount() {
        Node node = start;
        int count = 0;
        while (node.getNext() != null) {
            if (node.getData() >= -10 && node.getData() <= 10) count++;
            node = node.getNext();
        }

        return count + (node.getData() >= -10 && node.getData() <= 10 ? 1 : 0);
    }

    private void insertAtFirst(int data) {
        Node newNode = new Node(data, start, null);
        start.setPrev(newNode);
        start = newNode;
        if (size < maxSize) {
            size++;
        } else {
            System.out.println("\nElements " + size + " pozicija ar vertibu " +
                    getNodeAt(size - 1).getNext().getData() + " tiek izniemts no saraksta");
            getNodeAt(size - 1).setNext(null);
        }
    }

    private void insertAtLast(int data) {
        Node lastNode = getNodeAt(size - 1);
        if (size < maxSize) {
            lastNode.setNext(new Node(data, null, lastNode));
            size++;
        } else {
            System.out.println("\nElements " + size + " pozicija ar vertibu " +
                    getElementAt(size - 1) + " tiek izniemts no saraksta");
            lastNode.setData(data);
            lastNode.setNext(null);
        }

    }

    private void insertAt(int data, int pos) {
        Node curentNode = getNodeAt(pos);
        curentNode.setNext(new Node(data, curentNode.getNext(), curentNode));
        curentNode.getNext().getNext().setPrev(curentNode.getNext());
        if (size < maxSize) size++;
        else {
            System.out.println("\nElements " + size + " pozicija ar vertibu " +
                    getNodeAt(size - 1).getNext().getData() + " tiek izniemts no saraksta");
            getNodeAt(size - 1).setNext(null);
        }


    }

    private int getElementAt(int pos) throws ArrayIndexOutOfBoundsException {
        if (pos >= size || pos < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node temp = start;
        for (int counter = 0; counter < pos; counter++) {
            temp = temp.getNext();
        }
        return temp.getData();
    }

    private Node getNodeAt(int pos) throws ArrayIndexOutOfBoundsException {
        if (pos >= size || pos < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node temp = start;
        for (int counter = 0; counter < pos; counter++) {
            temp = temp.getNext();
        }
        return temp;
    }

}


public class Ld2_28 {

    private static DoubleLinkedList list = new DoubleLinkedList(10);

    public static void main(String[] args) throws IOException {
        Random rn = new Random();
        int invel;
        boolean create = false;

        System.out.println("INFO");
        System.out.println("Menu:");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            loop:
            for (; ; ) {

                if (create) {
                    System.out.println("\nIzveidot jautu sarakstu                  : 1");
                    System.out.println("Pievienot elementu sarakstam             : 2");
                    System.out.println("Izmest elementu no saraksta              : 3");
                    System.out.println("Metode Size                              : 4");
                    System.out.println("Metode Empty                             : 5");
                    System.out.println("Metode isFull                            : 6");
                    System.out.println("Elementu skaits kas pieder [-10;10]      : 7");
                    System.out.println("Saraksta izvade                          : 8");
                    System.out.println("Ja velaties iziet no sistemas nospiediet : 0");
                    System.out.print("Ievaddati: ");
                    invel = Integer.parseInt(br.readLine());
                } else {
                    System.out.print("Sarakst nav izveidots, izvedot sarakstu(y/n): ");
                    String str = br.readLine();
                    if (str.equals("Y") || str.equals("y"))
                        invel = 1;
                    else invel = 0;
                }

                switch (invel) {
                    case 1:
                        System.out.println("Ivadit datus saraksta ar roku       : 1");
                        System.out.println("Ivadit datus saraksta ar random util: 2");
                        System.out.print("Ievaddati: ");
                        String str = br.readLine();
                        if (create)
                            list = new DoubleLinkedList(10);
                        if (Integer.parseInt(str) == 1) {
                            System.out.println("Ivadiet vertibas:");
                            while (!list.isFull()) {
                                list.insert(Integer.parseInt(br.readLine()));
                            }
                        } else {
                            if (Integer.parseInt(str) == 2) {
                                while (!list.isFull()) {
                                    list.insert(rn.nextInt(40) - 15);
                                }
                            } else {
                                System.out.println("Darbs pabeigts");
                                break loop;
                            }
                        }
                        create = true;
                        list.listOut();
                        break;
                    case 2:

                        System.out.print("Ievadiet elementu: ");
                        list.insert(Integer.parseInt(br.readLine()));
                        list.listOut();
                        break;
                    case 3:
                        try {
                            System.out.print("Izmest uzdoto poziciju: ");
                            list.deleteElementAt(Integer.parseInt(br.readLine()));
                            list.listOut();
                            break;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Saraksts ir tukss");
                            create = false;
                            break;
                        }
                    case 4:
                        System.out.println("Size parada elementu skaits saraksta: "
                                + list.getSize());
                        break;
                    case 5:
                        System.out.println("Method Empty ir patiess ja sarakst ir tukss: "
                                + list.isEmpty());
                        break;
                    case 6:
                        System.out.println("Method isFull ir patiess ja saraksa ir 10 elementi: "
                                + list.isFull());
                        break;
                    case 7:
                        System.out.println("Elementu skaits kas pieder [-10;10]: "
                                + list.intervalCount());
                        break;
                    case 8:
                        list.listOut();
                        break;
                    case 0:
                        System.out.println("Darbs ir pabeigts");
                        break loop;
                    default:
                        System.out.println("Nav tadu variantu, meiginet vel reiz.");
                        list.listOut();
                        break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ievaddatu kluda");
        }
    }
}
