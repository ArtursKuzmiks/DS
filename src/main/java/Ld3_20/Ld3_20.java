package Ld3_20;

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

class QueueLink {

    private Node head;
    private int maxSize;
    private Node tail;
    private int size;

    QueueLink(int maxSize) {
        if (maxSize <= 0)
            throw new IllegalStateException("Size must be greater 0");
        if (maxSize > 15)
            throw new IllegalStateException("MaxSize: 15");
        this.maxSize = maxSize;
        this.head = null;
        this.size = 0;
    }

    void enqueue(int data) {
        if (!isFull()) {
            if (head == null) {
                head = new Node(data, null);
                tail = head;
                size++;
            } else {
                tail.setNext(new Node(data, null));
                tail = tail.getNext();
                size++;
            }
        } else System.out.println("Rinda ir pilna.");

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
        return head.getData();
    }

    void outQueue() {
        if (!isEmpty()) {
            System.out.println("Saraksts:");
            Node node = head;
            while (node.getNext() != null) {
                System.out.print(node.getData() + "\t");
                node = node.getNext();
            }
            System.out.print(node.getData() + "\n");
        } else {
            System.out.println("Rinda ir tuksa");
        }
    }

    void dequeue() {
        if (!isEmpty()) {
            System.out.println("\nIr izniemts no rindas elements " + head.getData());
            head = head.getNext();
        } else System.out.println("Rinda ir tuksa");
    }

    int evenNumberCount() {
        Node node = head;
        int count = 0;
        while (node.getNext() != null) {
            if (node.getData() % 2 == 0) count++;
            node = node.getNext();
        }
        return count + (node.getData() == 0 ? 1 : 0);
    }

}

public class Ld3_20 {

    private static QueueLink que;

    public static void main(String[] args) throws IOException {

        Random rd = new Random();
        int izvel;
        boolean queue = false;

        System.out.println("Info");
        System.out.println("Izvelne:");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            loop:
            for (; ; ) {

                if (queue) {
                    System.out.println("\nIzveidot jautu rindu                     :1");
                    System.out.println("Pievienot elementu rindai                :2");
                    System.out.println("Izmest elementu no rindas                :3");
                    System.out.println("Metode Size                              :4");
                    System.out.println("Metode Empty                             :5");
                    System.out.println("Metode Full                              :6");
                    System.out.println("Metode Peek                              :7");
                    System.out.println("Para     elementu skaits rinda           :8");
                    System.out.println("Rindas   izvade                          :9");
                    System.out.println("Ja velaties iziet no sistemas nospiediet :0");
                    System.out.print("Ievaddati: ");
                    izvel = Integer.parseInt(br.readLine());
                } else {
                    System.out.print("Rinda nav izveidota, izvedot rindu(y/n): ");
                    String str = br.readLine();
                    if (str.equals("Y") || str.equals("y"))
                        izvel = 1;
                    else izvel = 0;
                }

                switch (izvel) {
                    case 1:
                        System.out.print("Ievadiet rindas size: ");
                        int size = Integer.parseInt(br.readLine());
                        que = new QueueLink(size);
                        System.out.println("Ivadit datus saraksta ar roku       :1");
                        System.out.println("Ivadit datus saraksta ar random util:2");
                        System.out.print("Ievaddati: ");
                        String str = br.readLine();
                        if (Integer.parseInt(str) == 1) {
                            System.out.println("Ivadiet vertibas:");
                            while (!que.isFull()) {
                                que.enqueue(Integer.parseInt(br.readLine()));
                            }
                        } else {
                            if (Integer.parseInt(str) == 2) {
                                while (!que.isFull()) {
                                    que.enqueue(rd.nextInt(35) - 15);
                                }
                            } else {
                                System.out.println("Darbs pabeigts");
                                break loop;
                            }
                        }
                        queue = true;
                        que.outQueue();
                        break;
                    case 2:
                        System.out.print("Ievadiet elementu: ");
                        que.enqueue(Integer.parseInt(br.readLine()));
                        que.outQueue();
                        break;
                    case 3:
                        que.dequeue();
                        que.outQueue();
                        break;
                    case 4:
                        System.out.println("Size: " + que.size());
                        break;
                    case 5:
                        System.out.println("Method Empty ir patiess ja rinda ir tukssa: " + que.isEmpty());
                        break;
                    case 6:
                        System.out.println("Method Full ir patiess ja rinda ir 10 elementi: " + que.isFull());
                        break;
                    case 7:
                        if (que.isEmpty())
                            System.out.println("Rinda ir tuksa");
                        else
                            System.out.println("Izvada rindas pirmo elementu: " + que.peek());
                        break;
                    case 8:
                        System.out.println("Paru elementu skaits rinda: " + que.evenNumberCount());
                        break;
                    case 9:
                        que.outQueue();
                        break;
                    case 0:
                        System.out.println("Darbs ir pabeigts");
                        break loop;
                    default:
                        System.out.println("Nav tadu variantu, meiginet vel reiz.");
                        que.outQueue();
                        break;
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Ievaddatu kluda");
        } catch (IllegalStateException ex){
            System.out.println(ex.getMessage());
        }
    }
}
