/*
 * Author: Arturs Kuzmiks
 */

/*
 * Author: Arturs Kuzmiks
 */

package Ld3_31;

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

    void setNext(Node next) {
        this.next = next;
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

}

class Stack {

    private Node top;
    private int maxSize;
    private int size;

    Stack(int maxSize) {
        if (maxSize <= 0)
            throw new IllegalStateException("Size must be greater 0");
        if (maxSize > 15)
            throw new IllegalStateException("MaxSize: 15");
        this.maxSize = maxSize;
        this.top = null;
        this.size = 0;
    }

    void push(int data) {
        if (!isFull()) {
            if (top == null) {
                top = new Node(data, null, null);
                size++;
            } else {
                top.setNext(new Node(data, null, top));
                top = top.getNext();
                size++;
            }
        } else System.out.println("Steks  ir pilns.");

    }

    boolean isFull() {
        return (size == maxSize);
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return (size == 0);
    }

    int peek() {
        return top.getData();
    }

    void outStack() {
        if (!isEmpty()) {
            System.out.println("Steks:");
            Node node = top;
            while (node.getPrev() != null) {
                System.out.print(node.getData() + "\t");
                node = node.getPrev();
            }
            System.out.print(node.getData() + "\n");
        } else {
            System.out.println("Steks ir tukss");
        }
    }

    void pop() {
        if (!isEmpty()) {
            System.out.println("\nIr izniemts no steka elements " + top.getData());
            top = top.getPrev();
            top.setNext(null);
            size--;
        } else System.out.println("Steks ir tukss");
    }

    int intervalCount() {
        Node node = top;
        int count = 0;
        while (node.getPrev() != null) {
            if (node.getData() >= -10 && node.getData() <= 10) count++;
            node = node.getPrev();
        }

        return count + (node.getData() >= -10 && node.getData() <= 10 ? 1 : 0);
    }

}

public class Ld3_31 {

    private static Stack stack;

    public static void main(String[] args) throws IOException {

        Random random = new Random();
        int menu;
        int size = 0;
        boolean create = false;

        System.out.println("INFO");
        System.out.println("Izvelne:");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            loop:
            for (; ; ) {

                if (create) {
                    System.out.println("\nIzveidot jaunu steku                     : 1");
                    System.out.println("Pievienot elementu steka                 : 2");
                    System.out.println("Izmest elementu no steka                 : 3");
                    System.out.println("Metode Size                              : 4");
                    System.out.println("Metode Empty                             : 5");
                    System.out.println("Metode Full                              : 6");
                    System.out.println("Metode Peek                              : 7");
                    System.out.println("Elementu skaits, no intervala [-10;10]   : 8");
                    System.out.println("Steka   izvade                           : 9");
                    System.out.println("Ja velaties iziet no sistemas nospiediet : 0");
                    System.out.print("Ievaddati: ");
                    menu = Integer.parseInt(br.readLine());
                } else {
                    System.out.print("Steks nav izveidots, izvedot steku(y/n): ");
                    String str = br.readLine();
                    if (str.equals("Y") || str.equals("y"))
                        menu = 1;
                    else menu = 0;
                }

                switch (menu) {
                    case 1:
                        System.out.print("Ievadiet steka size: ");
                        size = Integer.parseInt(br.readLine());
                        stack = new Stack(size);
                        System.out.println("Ivadit datus ar roku       : 1");
                        System.out.println("Ivadit datus ar random util: 2");
                        System.out.print("Ievaddati: ");
                        String str = br.readLine();
                        if (Integer.parseInt(str) == 1) {
                            System.out.println("Ivadiet vertibas:");
                            while (!stack.isFull()) {
                                stack.push(Integer.parseInt(br.readLine()));
                            }
                        } else {
                            if (Integer.parseInt(str) == 2) {
                                while (!stack.isFull()) {
                                    stack.push(random.nextInt(35) - 15);
                                }
                            } else {
                                System.out.println("Darbs pabeigts");
                                break loop;
                            }
                        }
                        create = true;
                        stack.outStack();
                        break;
                    case 2:
                        System.out.print("Ievadiet elementu: ");
                        stack.push(Integer.parseInt(br.readLine()));
                        stack.outStack();
                        break;
                    case 3:
                        stack.pop();
                        stack.outStack();
                        break;
                    case 4:
                        System.out.println("Size izvada elementu skaitu steka: " + stack.size());
                        break;
                    case 5:
                        System.out.println("Method Empty ir patiess ja steks ir tukss: " + stack.isEmpty());
                        break;
                    case 6:
                        System.out.println("Method Full ir patiess ja steka ir " + size + " elementi: " + stack.isFull());
                        break;
                    case 7:
                        if (stack.isEmpty())
                            System.out.println("Steks ir tukss");
                        else
                            System.out.println("Izvada steka pedejo elementu: " + stack.peek());
                        break;
                    case 8:
                        System.out.println("Elementu skaits, no intervala [-10;10]: " + stack.intervalCount());
                        break;
                    case 9:
                        stack.outStack();
                        break;
                    case 0:
                        System.out.println("Darbs ir pabeigts");
                        break loop;
                    default:
                        System.out.println("Nav tadu variantu, meiginet vel reiz.");
                        stack.outStack();
                        break;
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Ievaddatu kluda");
        } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
