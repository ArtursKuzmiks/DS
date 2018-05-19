package Ld2_32;

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

    void setNext(Node next) {
        this.next = next;
    }

    void setPrev(Node prev) {
        this.prev = prev;
    }

}

class DoubleCircularLinkedList {

    private Node start;
    private int maxSize;
    private int size;

    DoubleCircularLinkedList(int maxSize) {
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
            while (node.getNext() != start) {
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
                    start.setPrev(getNodeAt(size - 2));
                    getNodeAt(size - 2).setNext(start);
                } else {
                    if (pos == size) {
                        getNodeAt(pos - 2).setNext(start);
                        start.setPrev(getNodeAt(pos - 2));
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
            start.setNext(start);
            start.setPrev(start);
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
        while (node.getNext() != start) {
            if (node.getData() >= -10 && node.getData() <= 10) count++;
            node = node.getNext();
        }

        return count + (node.getData() >= -10 && node.getData() <= 10 ? 1 : 0);
    }

    private void insertAtFirst(int data) {
        if (!isFull()) {
            if (start == null) {
                start = new Node(data, null, null);
                start.setNext(start);
                start.setPrev(start);
            } else {
                start = new Node(data, start, start.getPrev());
                getNodeAt(size-1).getNext().setNext(start);
                start.getNext().setPrev(start);


            }
            size++;
        } else System.out.println("Saraksts ir pilns");
    }

    private void insertAtLast(int data) {
        if (!isFull()) {
            Node lastNode = getNodeAt(size - 1);
            lastNode.setNext(new Node(data, start, lastNode));
            start.setPrev(lastNode.getNext());
            size++;
        } else System.out.println("Saraksts ir pilns");

    }

    private void insertAt(int data, int pos) {
        if (!isFull()) {
            Node curentNode = getNodeAt(pos);
            curentNode.setNext(new Node(data, curentNode.getNext(), curentNode));
            curentNode.getNext().getNext().setPrev(curentNode.getNext());
            size++;
        } else System.out.println("Saraksts ir pilns");
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


public class Ld2_32 {

    private static DoubleCircularLinkedList list = new DoubleCircularLinkedList(10);

    public static void main(String[] args) throws IOException {
        Random rn = new Random();
        int izvel;
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
                    izvel = Integer.parseInt(br.readLine());
                } else {
                    System.out.print("Sarakst nav izveidots, izvedot sarakstu(y/n): ");
                    String str = br.readLine();
                    if (str.equals("Y") || str.equals("y"))
                        izvel = 1;
                    else izvel = 0;
                }

                switch (izvel) {
                    case 1:
                        System.out.println("Ivadit datus saraksta ar roku       : 1");
                        System.out.println("Ivadit datus saraksta ar random util: 2");
                        System.out.print("Ievaddati: ");
                        String str = br.readLine();
                        if (create)
                            list = new DoubleCircularLinkedList(10);
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
