package Ld4_7;

/*
 * Author: Arturs Kuzmiks
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

class Node {
    private int data;
    private Node left;
    private Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;

    }

    int getData() {
        return data;
    }

    Node getLeft() {
        return left;
    }

    void setLeft(Node left) {
        this.left = left;
    }

    Node getRight() {
        return right;
    }

    void setRight(Node right) {
        this.right = right;
    }
}

class BinarySearchTree {

    private Node root;
    private int size;
    private int maxSize;

    BinarySearchTree(int maxSize) {
        this.root = null;
        this.size = 0;
        this.maxSize = maxSize;
        if (maxSize <= 0)
            throw new IllegalStateException("Size must be greater 0");
        if (maxSize > 15)
            throw new IllegalStateException("MaxSize: 15");
    }

    boolean isFull() {
        return size == maxSize;
    }

    void add(int data) {
        if (!isFull())
            root = insert(root, data);
    }

    private int perOnlyLeftChildCount(Node node) {
        int count = 0;
        if (node != null) {
            if (node.getLeft() != null && node.getRight() == null) {
                count++;
            }
            count += perOnlyLeftChildCount(node.getLeft());
            count += perOnlyLeftChildCount(node.getRight());
        }
        return count;
    }

    private int elementSum(Node node) {
        int count = 0;
        if (node != null) {
            count+=node.getData();
            count += elementSum(node.getLeft());
            count += elementSum(node.getRight());
        }
        return count;

    }

    private void traversePreOrd(Node node) {
        if (node != null) {
            System.out.print(node.getData() + "\t");
            traversePreOrd(node.getLeft());
            traversePreOrd(node.getRight());
        }
    }

    private Node insert(Node current, int data) {
        if (current == null) {
            size++;
            return new Node(data);
        }
        if (data < current.getData()) {
            current.setLeft(insert(current.getLeft(), data));
        } else {
            if (data > current.getData()) {
                current.setRight(insert(current.getRight(), data));
            } else return current;
        }
        return current;
    }

    int getSize() {
        return size;
    }

    boolean isEmpty() {
        return root == null;
    }

    int perOnlyLeftChildCount() {
        return perOnlyLeftChildCount(root);
    }

    int elementSum() {
        return elementSum(root);
    }

    void preOrder() {
        traversePreOrd(root);
    }

}

public class Ld4_7 {

    private static BinarySearchTree bt;

    public static void main(String[] args) throws IOException {

        Random rd = new Random();
        int menu;
        boolean treeCreate = false;

        System.out.println("INFO");
        System.out.println("Izvelne:");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            loop:
            for (; ; ) {

                if (treeCreate) {
                    System.out.println("\nIzveidot jaunu koku                      : 1");
                    System.out.println("Virsotnes skaits tikai ar kreiso bernu   : 2");
                    System.out.println("Elementu summa                           : 3");
                    System.out.println("Koka izvade                              : 4");
                    System.out.println("Pabeigt darbu                            : 0");
                    System.out.print("Ievaddati: ");
                    menu = Integer.parseInt(br.readLine());
                } else {
                    System.out.print("Koks nav izveidots, izvedot koku(y/n): ");
                    String str = br.readLine();
                    if (str.equals("Y") || str.equals("y"))
                        menu = 1;
                    else menu = 0;
                }

                switch (menu) {
                    case 1:
                        System.out.print("Elementu skaits: ");
                        int size = Integer.parseInt(br.readLine());
                        bt = new BinarySearchTree(size);
                        System.out.println("Ivadit datus koka ar roku       : 1");
                        System.out.println("Ivadit datus koka ar random util: 2");
                        System.out.print("Ievaddati: ");
                        String str = br.readLine();
                        if (Integer.parseInt(str) == 1) {
                            System.out.println("Ivadiet vertibas:");
                            while (!bt.isFull()) {
                                bt.add(Integer.parseInt(br.readLine()));
                                size--;
                            }
                        } else {
                            if (Integer.parseInt(str) == 2) {
                                while (!bt.isFull()) {
                                    bt.add(rd.nextInt(40) - 20);
                                    size--;
                                }
                            } else {
                                System.out.println("Darbs pabeigts");
                                break loop;
                            }
                        }
                        treeCreate = true;
                        bt.preOrder();
                        break;
                    case 2:
                        System.out.println("Virsotnes skaits tikai ar kreiso bernu: " +
                                bt.perOnlyLeftChildCount() + "\n");
                        bt.preOrder();
                        break;
                    case 3:
                        System.out.println("Elementu summa: " +
                                bt.elementSum() + "\n");
                        bt.preOrder();
                        break;
                    case 4:
                        bt.preOrder();
                        break;
                    case 0:
                        System.out.println("Darbs ir pabeigts");
                        break loop;
                    default:
                        System.out.println("Nav tadu variantu, meiginet vel reiz.");
                        bt.preOrder();
                        break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ievaddatu kluda");
        } catch (IllegalStateException es) {
            System.out.println(es.getMessage());
        }
    }
}
