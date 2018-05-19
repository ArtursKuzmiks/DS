/*
 * Author: Arturs Kuzmiks
 */

package Ld3_2;

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
            throw new IllegalStateException("Size must be greater 0");
        if (maxSize > 15)
            throw new IllegalStateException("MaxSize: 15");
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

public class Ld3_2 {
    private static Queue que;

    public static void main(String[] args) throws IOException {

        Random random = new Random();
        int menu;
        int size = 0;
        boolean queue = false;

        System.out.println("INFO");
        System.out.println("Izvelne:");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            loop:
            for (; ; ) {

                if (queue) {
                    System.out.println("\nIzveidot jautu rindu                     : 1");
                    System.out.println("Pievienot elementu rindai                : 2");
                    System.out.println("Izmest elementu no rindas                : 3");
                    System.out.println("Metode Size                              : 4");
                    System.out.println("Metode Empty                             : 5");
                    System.out.println("Metode Full                              : 6");
                    System.out.println("Metode Peek                              : 7");
                    System.out.println("Negativo elementu skaits rinda           : 8");
                    System.out.println("Rindas   izvade                          : 9");
                    System.out.println("Ja velaties iziet no sistemas nospiediet : 0");
                    System.out.print("Ievaddati: ");
                    menu = Integer.parseInt(br.readLine());
                } else {
                    System.out.print("Rinda nav izveidota, izvedot rindu(y/n): ");
                    String str = br.readLine();
                    if (str.equals("Y") || str.equals("y"))
                        menu = 1;
                    else menu = 0;
                }

                switch (menu) {
                    case 1:
                        System.out.print("Ievadiet rindas size: ");
                        size = Integer.parseInt(br.readLine());
                        que = new Queue(size);
                        System.out.println("Ivadit datus saraksta ar roku       : 1");
                        System.out.println("Ivadit datus saraksta ar random util: 2");
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
                                    que.enqueue(random.nextInt(35) - 15);
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
                        System.out.println("Method Full ir patiess ja rinda ir " + size + " elementi: " + que.isFull());
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
                        System.out.println("Nav tadu variantu, meiginet vel reiz.");
                        que.outQueue();
                        break;
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Ievaddatu kluda");
        }catch (IllegalStateException ex){
            System.out.println(ex.getMessage());
        }
    }
}
