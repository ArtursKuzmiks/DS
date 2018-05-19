/*
 * Author: Arturs Kuzmiks
 */

package Ld2_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

class Node {
    private int data;
    private Node next;

    Node(int data, Node n) {
        this.data = data;
        this.next = n;
    }

    void setData(int data) {
        this.data = data;
    }

    void setNext(Node next) {
        this.next = next;
    }

    int getData() {
        return data;
    }

    Node getNext() {
        return next;
    }
}

class CircularLinkedList {

    private Node start;
    private int size;
    private int maxSize;

    CircularLinkedList(int maxSize) {
        this.maxSize = maxSize;
        start = null;
        size = 0;
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

    private void insertAtFirst(int data) {
        start = new Node(data, start);
        getNodeAt(size - 1).getNext().setNext(start);
        if (size < maxSize) size++;
        else {
            System.out.println("\nElements " + size + " pozicija ar vertibu " +
                    getNodeAt(size - 1).getNext().getData() + " tiek izniemts no saraksta");
            getNodeAt(size - 1).setNext(start);
        }

    }

    private void insertAtLast(int data) {
        Node currentNode = getNodeAt(size - 1);
        if (size < maxSize) {
            currentNode.setNext(new Node(data, start));
            size++;
        } else {
            System.out.println("\nElements " + size + " pozicija ar vertibu " +
                    getElementAt(size - 1) + " tiek izniemts no saraksta");
            currentNode.setData(data);
        }
    }

    private void insertAt(int pos, int data) {
        Node currentNode = getNodeAt(pos);
        currentNode.setNext(new Node(data, currentNode.getNext()));
        if (size < maxSize)
            size++;
        else
            System.out.println("\nElements " + size + " pozicija ar vertibu " +
                    getNodeAt(size - 1).getNext().getData() + " tiek izniemts no saraksta");
        getNodeAt(size - 1).setNext(start);
    }

    boolean isEmpty() {
        return (start == null);
    }

    boolean isFull() {
        return (size == maxSize);
    }

    void insert(int data) {
        if (start == null) {
            start = new Node(data, null);
            start.setNext(start);
            size++;
        } else {
            if (getNodeAt(size - 1).getData() <= data) {
                insertAtLast(data);
            } else {
                int pos = size - 1;
                while (pos >= 0 && getNodeAt(pos).getData() > data) {
                    pos--;
                }
                if (pos < 0) {
                    insertAtFirst(data);
                } else {
                    insertAt(pos, data);
                }
            }
        }
    }

    void deleteElementAt(int pos) {
        if (pos < 1 || pos > size) {
            System.out.println("Nekorekta izveleta pozicija(1-" + size + ")");
        } else {
            System.out.println("\nElements " + pos + " pozicija ar vertibu " +
                    getElementAt(pos - 1) + " tiek izniemts no saraksta");
            if (pos - 1 == 0 && size - 1 == 0) {
                start = null;
                size--;
            } else {
                if (pos - 1 == 0) {
                    start = getNodeAt(pos);
                    getNodeAt(size - 2).setNext(start);
                } else {
                    if (pos == size) {
                        getNodeAt(pos - 2).setNext(start);
                    } else {
                        Node tempnode = getNodeAt(pos - 2);
                        tempnode.setNext(tempnode.getNext().getNext());
                    }
                }
                size--;
            }
        }
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

    int nullCount() {
        Node node = start;
        int count = 0;
        while (node.getNext() != start) {
            if (node.getData() == 0) count++;
            node = node.getNext();
        }

        return count + (node.getData() == 0 ? 1 : 0);
    }

    int getSize() {
        return size;
    }
}

public class Ld2_14 {

    private static CircularLinkedList cirkulList;

    public static void main(String[] args) throws IOException {

        Random rd = new Random();
        int izvel;
        boolean listCreate = false;

        System.out.println("Arturs Kuzmiks 12.gr. 111REB779");
        System.out.println("Izvelne:");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            loop:
            for (; ; ) {

                if (listCreate) {
                    System.out.println("\nIzveidot jautu sarakstu                  : 1");
                    System.out.println("Pievienot elementu sarakstam             : 2");
                    System.out.println("Izmest elementu no saraksta              : 3");
                    System.out.println("Metode Size                              : 4");
                    System.out.println("Metode Empty                             : 5");
                    System.out.println("Metode isFull                            : 6");
                    System.out.println("Homogenu elementu skaits saraksta        : 7");
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
                        System.out.print("Ievadiet rindas Size: ");
                        int size = Integer.parseInt(br.readLine());
                        System.out.println("Ivadit datus saraksta ar roku       : 1");
                        System.out.println("Ivadit datus saraksta ar random util: 2");
                        System.out.print("Ievaddati: ");
                        String str = br.readLine();
                        cirkulList = new CircularLinkedList(size);
                        if (Integer.parseInt(str) == 1) {
                            System.out.println("Ivadiet vertibas:");
                            while (!cirkulList.isFull()) {
                                cirkulList.insert(Integer.parseInt(br.readLine()));
                            }
                        } else {
                            if (Integer.parseInt(str) == 2) {
                                while (!cirkulList.isFull()) {
                                    cirkulList.insert(rd.nextInt(35) - 15);
                                }
                            } else {
                                System.out.println("Darbs pabeigts");
                                break loop;
                            }
                        }
                        listCreate = true;
                        cirkulList.listOut();
                        break;
                    case 2:
                        System.out.print("Ievadiet elementu: ");
                        cirkulList.insert(Integer.parseInt(br.readLine()));
                        cirkulList.listOut();
                        break;
                    case 3:
                        try {
                            System.out.print("Izmest uzdoto poziciju: ");
                            cirkulList.deleteElementAt(Integer.parseInt(br.readLine()));
                            cirkulList.listOut();
                            break;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Saraksts ir tukss");
                            listCreate = false;
                            break;
                        }
                    case 4:
                        System.out.println("Size parada elementu skaits saraksta: "
                                + cirkulList.getSize());
                        break;
                    case 5:
                        System.out.println("Method Empty ir patiess ja sarakst ir tukss: "
                                + cirkulList.isEmpty());
                        break;
                    case 6:
                        System.out.println("Method isFull ir patiess ja saraksa ir 10 elementi: "
                                + cirkulList.isFull());
                        break;
                    case 7:
                        System.out.println("Homogenu elementu skaits saraksta: "
                                + cirkulList.nullCount());
                        break;
                    case 8:
                        cirkulList.listOut();
                        break;
                    case 0:
                        System.out.println("Darbs ir pabeigts");
                        break loop;
                    default:
                        System.out.println("Nav tadu variantu, meiginet vel reiz.");
                        cirkulList.listOut();
                        break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ievaddatu kluda");
        }
    }


}
