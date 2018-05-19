/*
 * Author: Arturs Kuzmiks
 */

package Ld3_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Vector;

class Stack {

    private int maxSize;
    private Vector<Integer> vec;
    private int top = 0;

    Stack(int maxSize) {
        if (maxSize <= 0)
            throw new IllegalStateException("Size must be greater 0");
        if (maxSize > 15)
            throw new IllegalStateException("MaxSize: 15");
        this.maxSize = maxSize;
        this.vec = new Vector<>();
    }

    void push(int elem) {
        if (!isFull()) {
            vec.add(top++, elem);
        } else System.out.println("Steks ir pilns.");

    }

    boolean isFull() {
        return (top == maxSize);
    }

    int size() {
        return top;
    }

    boolean isEmpty() {
        return (top == 0);
    }

    int peek() {
        return vec.elementAt(top-1);
    }

    void outStack() {
        if (!isEmpty()) {
            System.out.println("Steks:");
            for (int i = top-1; i >= 0; i--) {
                System.out.print(vec.get(i) + "\t");
            }
            System.out.println();
        } else System.out.println("\nSteks ir tukss");
    }

    void pop() {
        if (!isEmpty()) {
            System.out.println("\nIr izniemts no steka elements " + vec.elementAt(top-1));
            vec.remove(--top);
        } else System.out.println("Steks ir tukss");
    }

    int nullNumCount() {
        int count = 0;
        for (int i = 0; i < top; i++) {
            if (vec.elementAt(i) == 0) count++;
        }
        return count;
    }

}

public class Ld3_1 {
    private static Stack stack;

    public static void main(String[] args) throws IOException {

        Random random = new Random();
        int menu;
        int size = 0;
        boolean queue = false;

        System.out.println("Valters Barčs 111RDB328");
        System.out.println("Izvelne:");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            loop:
            for (; ; ) {

                if (queue) {
                    System.out.println("\nIzveidot jautu steku                     : 1");
                    System.out.println("Pievienot elementu steka                 : 2");
                    System.out.println("Izmest elementu no steka                 : 3");
                    System.out.println("Metode Size                              : 4");
                    System.out.println("Metode Empty                             : 5");
                    System.out.println("Metode Full                              : 6");
                    System.out.println("Metode Peek                              : 7");
                    System.out.println("Elementu skaits, kas vienads ar 0 steka  : 8");
                    System.out.println("Steka   izvade                           : 9");
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
                        System.out.print("Ievadiet steka size: ");
                        size = Integer.parseInt(br.readLine());
                        stack = new Stack(size);
                        System.out.println("Ivadit datus saraksta ar roku       : 1");
                        System.out.println("Ivadit datus saraksta ar random util: 2");
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
                        queue = true;
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
                            System.out.println("Izvada steka pirmo elementu: " + stack.peek());
                        break;
                    case 8:
                        System.out.println("Elementu skaits, kas vienads ar 0 steka: " + stack.nullNumCount());
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
