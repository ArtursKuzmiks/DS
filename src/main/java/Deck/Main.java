package Deck;

/*
 * Author: Arturs Kuzmiks
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

class Deck {

    private int maxSize;
    private Vector<Integer> vec;
    private int head = 0;
    private int tail = 0;

    Deck(int maxSize) {
        if (maxSize <= 0)
            throw new IllegalStateException("Size must be greater 0");
        if (maxSize > 15)
            throw new IllegalStateException("MaxSize: 15");
        this.maxSize = maxSize;
        this.vec = new Vector<>();
    }

    void addLast(int elem) {
        if (!isFull()) {
            vec.add(tail++, elem);
        } else
            System.out.println("Deks ir pilns");

    }

    void addFirst(int elem) {
        if (!isFull()) {
            vec.add(0, elem);
            tail++;
        } else
            System.out.println("Deks ir pilns");
    }

    boolean isFull() {
        return (tail == maxSize);
    }

    boolean isEmpty() {
        return (tail == 0);
    }

    int peekFirst() {
        return vec.elementAt(head);
    }

    int peekLast() {
        return vec.elementAt(tail - 1);
    }

    void printDeck() {
        if (!isEmpty()) {
            System.out.println("Rinda:");
            for (int i : vec) System.out.print(i + "\t");
            System.out.println();
        }
    }

    void dequeueLast() {
        if (!isEmpty()) {
            System.out.println("\nIr izniemts no rindas elements " + vec.elementAt(--tail));
            vec.remove(tail);
        } else
            System.out.println("Deks ir tukšā");
    }

    void dequeueFirst() {
        if (!isEmpty()) {
            System.out.println("\nIr izniemts no rindas elements " + vec.elementAt(head));
            for (int i = 1; i < tail; i++) {
                vec.setElementAt(vec.get(i), i - 1);
            }
            vec.remove(--tail);
        } else
            System.out.println("Deks ir tukšā");
    }


}

public class Main {
    private static Deck deck;

    public static void main(String[] args) throws IOException {

        int menu;
        boolean queue = false;

        System.out.println("Izvelne:");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            loop:
            for (; ; ) {

                if (queue) {
                    System.out.println("\nDeka operācijas:");
                    System.out.println("1: Ievietot sākumā");
                    System.out.println("2: Ievietot beigās");
                    System.out.println("3: Dzēst pirmo");
                    System.out.println("4: Dzēst pēdējo");
                    System.out.println("5: Pirmais elements");
                    System.out.println("6: Pēdējais elements");
                    System.out.println("7: Vai ir tukšs");
                    System.out.println("8: Vai ir pilns");
                    System.out.println("0: Izeja");
                    System.out.print("Ievaddati: ");
                    menu = Integer.parseInt(br.readLine());
                } else {
                    System.out.print("Rinda nav izveidota, izvedot rindu (y/n): ");
                    String str = br.readLine();
                    if (str.equals("Y") || str.equals("y")) {
                        System.out.print("Size: ");
                        int size = Integer.parseInt(br.readLine());
                        deck = new Deck(size);
                        queue = true;
                        continue;
                    } else menu = 0;
                }

                switch (menu) {
                    case 1:
                        System.out.print("Vertiba: ");
                        deck.addFirst(Integer.parseInt(br.readLine()));
                        deck.printDeck();
                        break;
                    case 2:
                        System.out.print("Vertiba: ");
                        deck.addLast(Integer.parseInt(br.readLine()));
                        deck.printDeck();
                        break;
                    case 3:
                        deck.dequeueFirst();
                        deck.printDeck();
                        break;
                    case 4:
                        deck.dequeueLast();
                        deck.printDeck();
                        break;
                    case 5:
                        System.out.println("Pirmais: " + deck.peekFirst());
                        break;
                    case 6:
                        System.out.println("Pedejais: " + deck.peekLast());
                        break;
                    case 7:
                        System.out.println("Deks ir tukšs: " + deck.isEmpty());
                        break;
                    case 8:
                        System.out.println("Deks ir pilns: " + deck.isFull());
                        break;
                    case 0:
                        System.out.println("Darbs ir pabeigts");
                        break loop;
                    default:
                        System.out.println("Nav tadu variantu, meiginet vel reiz.");
                        deck.printDeck();
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
