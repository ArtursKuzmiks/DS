package Ld3_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Vector;

class Queue {

    private int maxSize;
    private Vector<Integer> vec;
    private int head = 0;
    private int tail = 0;

    Queue(int maxSize) {
        if (maxSize <= 0)
            throw new IllegalArgumentException("Size must be greater 0");
        this.maxSize = maxSize;
        this.vec = new Vector<>();
    }

    void enqueue(int elem) {
        if (!isFull()) {
            vec.add(tail++, elem);
        } else System.out.println("Rinda ir pilna.");

    }

    boolean isFull() {
        return (tail == maxSize);
    }

    int size() {
        return tail;
    }

    boolean isEmpty() {
        return (tail == 0);
    }

    int peek() {
        return vec.elementAt(head);
    }

    void outQueue() {
        if (!isEmpty()) {
            System.out.println("Rinda:");
            for (int i : vec) System.out.print(i + "\t");
            System.out.println();
        } else System.out.println("\nRinda ir tuksa");
    }

    void dequeue() {
        if (!isEmpty()) {
            System.out.println("\nIr izniemts no rindas elements " + vec.elementAt(head));
            for (int i = 1; i < tail; i++) {
                vec.setElementAt(vec.get(i), i - 1);
            }
            vec.remove(--tail);
        } else System.out.println("Rinda ir tuksa");
    }

    int negNumCount() {
        int count = 0;
        for (int i = head; i < tail; i++) {
            if (vec.elementAt(i) < 0) count++;
        }
        return count;
    }

}

public class Ld3_14 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static Queue que;

    public static void main(String[] args) throws IOException {

        Random rd = new Random();
        int izvel;
        boolean queue = false;

        System.out.println("Arturs Kuzmiks 12.gr. 111REB779");
        System.out.println("Izvelne:");

        try {

            loop:
            for (; ; ) {

                if (queue) {
                    System.out.println("\nIzveidot jautu rindu                     : " + ANSI_RED + "1" + ANSI_RESET);
                    System.out.println("Pievienot elementu rindai                : " + ANSI_RED + "2" + ANSI_RESET);
                    System.out.println("Izmest elementu no rindas                : " + ANSI_RED + "3" + ANSI_RESET);
                    System.out.println("Metode Size                              : " + ANSI_RED + "4" + ANSI_RESET);
                    System.out.println("Metode Empty                             : " + ANSI_RED + "5" + ANSI_RESET);
                    System.out.println("Metode Full                              : " + ANSI_RED + "6" + ANSI_RESET);
                    System.out.println("Metode Peek                              : " + ANSI_RED + "7" + ANSI_RESET);
                    System.out.println("Negativo elementu skaits rinda           : " + ANSI_RED + "8" + ANSI_RESET);
                    System.out.println("Rindas   izvade                          : " + ANSI_RED + "9" + ANSI_RESET);
                    System.out.println("Ja velaties iziet no sistemas nospiediet : " + ANSI_RED + "0" + ANSI_RESET);
                    System.out.print("\u001B[36m" + "Ievaddati: " + "\u001B[0m");
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
                        System.out.print("Ievadiet Rindas MaxSize: ");
                        int size = Integer.parseInt(br.readLine());
                        System.out.println("Ivadit datus saraksta ar roku       : " + ANSI_RED + "1" + ANSI_RESET);
                        System.out.println("Ivadit datus saraksta ar random util: " + ANSI_RED + "2" + ANSI_RESET);
                        System.out.print("\u001B[36m" + "Ievaddati: " + "\u001B[0m");
                        String str = br.readLine();
                        que = new Queue(size);
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
                        System.out.println("Size izvada elementu skaitu rinda: " + que.size());
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
                        System.out.println("Negativo elementu skaits rinda: " + que.negNumCount());
                        break;
                    case 9:
                        que.outQueue();
                        break;
                    case 0:
                        System.out.println("Darbs ir pabeigts");
                        break loop;
                    default:
                        System.out.println(ANSI_RED + "Nav tadu variantu, meiginet vel reiz." + ANSI_RESET);
                        que.outQueue();
                        break;
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Ievaddatu kluda");
        }
    }
}
