package Ld4_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Ld4_14 {

    private static BinarySearchTree bt;

    public static void main(String[] args) throws IOException {

        Random rd = new Random();
        int ans;
        boolean treeCreate = false;

        System.out.println("Arturs Kuzmiks 12.gr. 111REB779");
        System.out.println("Izvelne:");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            loop:
            for (; ; ) {

                if (treeCreate) {
                    System.out.println("\nIzveidot jaunu koku                      : 1");
                    System.out.println("Virsotnes skaits ar diviem berniem       : 2");
                    System.out.println("Para elementu skaits koka                : 3");
                    System.out.println("Koka izvade                              : 4");
                    System.out.println("Pabeigt darbu                            : 0");
                    System.out.print("Ievaddati: ");
                    ans = Integer.parseInt(br.readLine());
                } else {
                    System.out.print("Koks nav izveidots, izvedot koku(y/n): ");
                    String str = br.readLine();
                    if (str.equals("Y") || str.equals("y"))
                        ans = 1;
                    else ans = 0;
                }

                switch (ans) {
                    case 1:
                        System.out.print("Elementu skaits: ");
                        int size = Integer.parseInt(br.readLine());
                        bt = new BinarySearchTree();
                        System.out.println("Ivadit datus koka ar roku       : 1");
                        System.out.println("Ivadit datus koka ar random util: 2");
                        System.out.print("Ievaddati: ");
                        String str = br.readLine();
                        if (Integer.parseInt(str) == 1) {
                            System.out.println("Ivadiet vertibas:");
                            while (size != 0) {
                                bt.add(Integer.parseInt(br.readLine()));
                                size--;
                            }
                        } else {
                            if (Integer.parseInt(str) == 2) {
                                while (size != 0) {
                                    bt.add(rd.nextInt(40) - 20);
                                    size--;
                                }
                            } else {
                                System.out.println("Darbs pabeigts");
                                break loop;
                            }
                        }
                        treeCreate = true;
                        bt.postOrder();
                        break;
                    case 2:
                        System.out.println("Virsotnes skaits ar diviem berniem: " +
                                bt.twoChildPerCount() + "\n");
                        bt.postOrder();
                        break;
                    case 3:
                        System.out.println("Para elementu skaits koka: " +
                                bt.evenCount() + "\n");
                        bt.postOrder();
                        break;
                    case 0:
                        System.out.println("Darbs ir pabeigts");
                        break loop;
                    default:
                        System.out.println("Nav tadu variantu, meiginet vel reiz.");
                        bt.postOrder();
                        break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ievaddatu kluda");
        } catch (IllegalStateException es) {
            System.out.println("MaxSize: 15");
        }
    }
}
