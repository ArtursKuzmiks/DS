/*
 * Author: Arturs Kuzmiks
 */

/*
 * Author: Arturs Kuzmiks
 */

package Ld2_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

class Node<Integer> {
    private int data;
    private Node<Integer> next;

    /*  Constructor  */
    Node(int data, Node<Integer> next) {
        this.data = data;
        this.next = next;
    }

    /*  Method to get data from current Node  */
    public int getData() {
        return data;
    }

    /*  Method to get link to next node  */
    public Node<Integer> getNext() {
        return next;
    }

    /*  Method to set data to current Node  */
    public void setData(int data) {
        this.data = data;
    }

    /*  Method to set link to next Node  */
    public void setNext(Node<Integer> next) {
        this.next = next;

    }
}

class SinglyLinkedList {
    private Node start;
    private int maxSize;
    private int size;

    /*  Constructor  */
    public SinglyLinkedList(int maxSize) {
        this.maxSize = maxSize;
        this.start = null;
        this.size = 0;

    }

    /*  Method to get size of list  */
    public int getSize() {
        return size;
    }

    /*  Method to check if list is empty  */
    public boolean isEmpty() {
        return start == null;
    }

    /*  Method to check if list is full  */
    public boolean isFull() {
        return size == maxSize;
    }

    /*  Method to create node and set it to first  */
    public void insertAtFirst(int data) {
        if (start == null) {
            start = new Node(data, null);
        } else {
            start = new Node(data, start);
        }
        if (!isFull()) {
            size++;
        } else {
            System.out.println("\nElements " + size + " pozicija ar vertibu " +
                    getNodeAt(size - 1).getNext().getData() + " tiek izniemts no saraksta");
            getNodeAt(size - 1).setNext(null);
        }
    }

    /*  Method to create node and set it to last  */
    public void insertAtLast(int data) {
        Node lastNode = getNodeAt(size - 1);
        if (!isFull()) {
            lastNode.setNext(new Node(data, null));
            size++;
        } else {
            System.out.println("\nElements " + size + " pozicija ar vertibu " +
                    getElementAt(size - 1) + " tiek izniemts no saraksta");
            lastNode.setData(data);
            lastNode.setNext(null);
        }
    }

    /*  Method to create node at position  */
    public void insertAt(int data, int pos) {
        if (pos < 1 || pos > size) {
            System.out.println("Pievienosanas kluda");
        } else {
            Node curentNode = getNodeAt(pos - 1);
            curentNode.setNext(new Node(data, curentNode.getNext()));
            if (!isFull()) {
                size++;
            } else {
                System.out.println("\nElements " + size + " pozicija ar vertibu " +
                        getNodeAt(size - 1).getNext().getData() + " tiek izniemts no saraksta");
                getNodeAt(size - 1).setNext(null);
            }
        }
    }

    /*  Method to delete node at position  */
    public void deleteElementAt(int pos) {
        if (pos < 1 || pos > size) {
            System.out.println("Tadu elementu nav. (1-" + size + ")");
            if (size == 0) throw new ArrayIndexOutOfBoundsException();
        } else {
            System.out.println("\nElements " + pos + " pozicija ar vertibu " +
                    getElementAt(pos - 1) + " tiek izniemts no saraksta");
            if (pos - 1 == 0 && size - 1 == 0) {
                start = null;
                size--;
            } else {
                if (pos == 1) {
                    start = getNodeAt(pos);
                } else {
                    if (pos == size) {
                        getNodeAt(pos - 2).setNext(null);
                    } else {
                        Node temp = getNodeAt(pos - 2);
                        temp.setNext(temp.getNext().getNext());

                    }
                }
                size--;
            }
        }
    }

    /*  Method to get ever number count  */
    public int evenNumberCount() {
        Node node = start;
        int count = 0;
        while (node.getNext() != null) {
            if (node.getData() % 2 == 0) count++;
            node = node.getNext();
        }
        return count + (node.getData() == 0 ? 1 : 0);
    }

    /*  Method  to display list  */
    public void listOut() {
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

    /*  Method  to get data from position  */
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

    /*  Method  to get node from position  */
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

public class Ld2_1 {

    private static SinglyLinkedList singlList = new SinglyLinkedList(10);
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        Random rd = new Random();
        int menu;
        boolean listCreate = false;

        System.out.println("INFO");
        System.out.println("Menu:");

        try {
            loop:
            for (; ; ) {
                if (listCreate) {
                    System.out.println("\nIzveidot jautu sarakstu                  : 1");
                    System.out.println("Pievienot elementu sarakstam             : 2");
                    System.out.println("Izmest elementu no saraksta              : 3");
                    System.out.println("Metode Size                              : 4");
                    System.out.println("Metode Empty                             : 5");
                    System.out.println("Metode isFull                            : 6");
                    System.out.println("Para elementu skaits saraksta            : 7");
                    System.out.println("Saraksta izvade                          : 8");
                    System.out.println("Ja velaties iziet no sistemas nospiediet : 0");
                    System.out.print("Ievaddati: ");
                    menu = Integer.parseInt(br.readLine());
                } else {
                    System.out.print("Sarakst nav izveidots, izvedot sarakstu(y/n): ");
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
                        if (listCreate)
                            singlList = new SinglyLinkedList(10);
                        if (Integer.parseInt(str) == 1) {
                            System.out.print("Ivadiet pirma elementa vertibu: ");
                            singlList.insertAtFirst(Integer.parseInt(br.readLine()));
                            System.out.print("Ivadiet pedeja elementa vertibu: ");
                            singlList.insertAtLast(Integer.parseInt(br.readLine()));

                            int i = 1;
                            while (!singlList.isFull()) {
                                System.out.print("Ivadiet " + (i + 1) + " elementa vertibu: ");
                                singlList.insertAt(Integer.parseInt(br.readLine()), i);
                                i++;
                            }

                        } else {
                            if (Integer.parseInt(str) == 2) {

                                singlList.insertAtFirst(rd.nextInt(35) - 15);
                                singlList.insertAtLast(rd.nextInt(35) - 15);

                                int i = 1;
                                while (!singlList.isFull()) {
                                    singlList.insertAt(rd.nextInt(35) - 15, i);
                                    i++;
                                }

                            } else {
                                System.out.println("Darbs pabeigts");
                                break loop;
                            }
                        }
                        listCreate = true;
                        singlList.listOut();
                        break;
                    case 2:

                        System.out.println("Pievienot pirmu elementu                :1");
                        if (!singlList.isEmpty())
                            System.out.println("Pievienot pedejo elementu               :2");
                        if (!singlList.isEmpty() && singlList.getSize() != 1)
                            System.out.println("Pievienot elementu uz noteikto poziciju :3");
                        System.out.print("Jusu izvele: ");

                        switch (Integer.parseInt(br.readLine())) {
                            case 1:
                                System.out.print("Ivadiet pirma elementa vertibu: ");
                                singlList.insertAtFirst(Integer.parseInt(br.readLine()));
                                break;
                            case 2:
                                if (singlList.isEmpty()) {
                                    System.out.println("Nav pirma elementa");
                                } else {
                                    System.out.print("Ivadiet pedeja elementa vertibu: ");
                                    singlList.insertAtLast(Integer.parseInt(br.readLine()));
                                }
                                break;
                            case 3:
                                if (singlList.getSize() < 2) {
                                    System.out.println("Pievienot uz konkreto poziciju nav iespejams");
                                } else {
                                    System.out.print("Ievadiet poziciju(2-" + singlList.getSize() + "): ");
                                    int pos = Integer.parseInt(br.readLine());
                                    System.out.print("Ievadiet vertubu: ");
                                    singlList.insertAt(Integer.parseInt(br.readLine()), pos - 1);
                                }
                                break;
                            default:
                                System.out.println("Ievaddatu kluda");
                        }
                        singlList.listOut();
                        break;
                    case 3:
                        try {
                            System.out.print("Izmest uzdoto poziciju: ");
                            singlList.deleteElementAt(Integer.parseInt(br.readLine()));
                            singlList.listOut();
                            //if (singlList.isEmpty()) listCreate = false;
                            break;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Saraksts ir tukss");
                            listCreate = false;
                            break;
                        }
                    case 4:
                        System.out.println("Size parada elementu skaits saraksta: "
                                + singlList.getSize());
                        break;
                    case 5:
                        System.out.println("Method Empty ir patiess ja sarakst ir tukss: "
                                + singlList.isEmpty());
                        break;
                    case 6:
                        System.out.println("Method isFull ir patiess ja saraksa ir 10 elementi: "
                                + singlList.isFull());
                        break;
                    case 7:
                        System.out.println("Para elementu skaits saraksta: "
                                + singlList.evenNumberCount());
                        break;
                    case 8:
                        singlList.listOut();
                        break;
                    case 0:
                        System.out.println("Darbs ir pabeigts");
                        break loop;
                    default:
                        System.out.println("Nav tadu variantu, meiginet vel reiz.");
                        singlList.listOut();
                        break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ievaddatu kluda");
        }

    }
}
