/*
 * Author: Arturs Kuzmiks
 */

/*
 * Author: Arturs Kuzmiks
 */

package Ld2_31;

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

class DoubleCircLinkedList {

    private Node start;
    private int maxSize;
    private int size;

    DoubleCircLinkedList(int maxSize) {
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
                    getNodeAt(pos - 1).getData() + " tiek izniemts no saraksta");
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

    int negNumCount() {
        Node node = start;
        int count = 0;
        while (node.getNext() != start) {
            if (node.getData() < 0) count++;
            node = node.getNext();
        }

        return count + (node.getData() < 0 ? 1 : 0);
    }

    void insertAtFirst(int data) {
        if (!isFull()) {
            if (start == null) {
                start = new Node(data, null, null);
                start.setNext(start);
                start.setPrev(start);
            } else {
                start = new Node(data, start, start.getPrev());
                getNodeAt(size-1).getNext().setNext(start);
            }
            size++;
        } else System.out.println("Saraksts ir pilns");
    }

    void insertAtLast(int data) {
        if (!isFull()) {
            Node lastNode = getNodeAt(size - 1);
            lastNode.setNext(new Node(data, start, lastNode));
            start.setPrev(lastNode.getNext());
            size++;
        } else System.out.println("Saraksts ir pilns");

    }

    void insertAt(int data, int pos) {
        if (pos < 1 || pos > size) {
            System.out.println("Pievienosanas kluda");
        } else {
            if (!isFull()) {
                Node curentNode = getNodeAt(pos - 1);
                curentNode.setNext(new Node(data, curentNode.getNext(), curentNode));
                curentNode.getNext().getNext().setPrev(curentNode.getNext());
                size++;
            } else System.out.println("Saraksts ir pilns");
        }


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


public class Ld2_31 {

    private static DoubleCircLinkedList list = new DoubleCircLinkedList(10);

    public static void main(String[] args) throws IOException {

        Random random = new Random();
        int menu;
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
                    System.out.println("Negativo Elementu skaits saraksta        : 7");
                    System.out.println("Saraksta izvade                          : 8");
                    System.out.println("Ja velaties iziet no sistemas nospiediet : 0");
                    System.out.print("Ievaddati: ");
                    menu = Integer.parseInt(br.readLine());
                } else {
                    System.out.print("Sarakst nav izveidots, izvedot sarakstu(Y/N): ");
                    String str = br.readLine();
                    if (str.equals("Y") || str.equals("y"))
                        menu = 1;
                    else menu = 0;
                }

                switch (menu) {
                    case 1:
                        System.out.println("Ivadit datus saraksta ar roku       : 1");
                        System.out.println("Ivadit datus saraksta ar random util: 2");
                        System.out.print("Ievaddati: ");
                        String str = br.readLine();
                        if (create)
                            list = new DoubleCircLinkedList(10);
                        if (Integer.parseInt(str) == 1) {
                            System.out.print("Ivadiet pirma elementa vertibu: ");
                            list.insertAtFirst(Integer.parseInt(br.readLine()));
                            System.out.print("Ivadiet pedeja elementa vertibu: ");
                            list.insertAtLast(Integer.parseInt(br.readLine()));

                            int i = 1;
                            while (!list.isFull()) {
                                System.out.print("Ivadiet " + (i + 1) + " elementa vertibu: ");
                                list.insertAt(Integer.parseInt(br.readLine()), i);
                                i++;
                            }

                        } else {

                            if (Integer.parseInt(str) == 2) {

                                list.insertAtFirst(random.nextInt(30) - 15);
                                list.insertAtLast(random.nextInt(30) - 15);

                                int i = 1;
                                while (!list.isFull()) {
                                    list.insertAt(random.nextInt(30) - 15, i);
                                    i++;
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
                        if (!list.isFull()) {
                            System.out.println("Pievienot pirmu elementu                :1");
                            if (!list.isEmpty())
                                System.out.println("Pievienot pedejo elementu               :2");
                            if (!list.isEmpty() && list.getSize() != 1)
                                System.out.println("Pievienot elementu uz noteikto poziciju :3");
                            System.out.print("Jusu izvele: ");

                            switch (Integer.parseInt(br.readLine())) {
                                case 1:
                                    System.out.print("Ivadiet pirma elementa vertibu: ");
                                    list.insertAtFirst(Integer.parseInt(br.readLine()));
                                    break;
                                case 2:
                                    if (list.isEmpty()) {
                                        System.out.println("Nav pirma elementa");
                                    } else {
                                        System.out.print("Ivadiet pedeja elementa vertibu: ");
                                        list.insertAtLast(Integer.parseInt(br.readLine()));
                                    }
                                    break;
                                case 3:
                                    if (list.getSize() < 2) {
                                        System.out.println("Pievienot uz konkreto poziciju nav iespejams");
                                    } else {
                                        System.out.print("Ievadiet poziciju(2-" + list.getSize() + "): ");
                                        int pos = Integer.parseInt(br.readLine());
                                        System.out.print("Ievadiet vertubu: ");
                                        list.insertAt(Integer.parseInt(br.readLine()), pos - 1);
                                    }
                                    break;
                                default:
                                    System.out.println("Ievaddatu kluda");
                            }
                        } else System.out.println("Saraksts ir pilns");
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
                        System.out.println("Size: " + list.getSize());
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
                        System.out.println("Negativo Elementu skaits saraksta: "
                                + list.negNumCount());
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
