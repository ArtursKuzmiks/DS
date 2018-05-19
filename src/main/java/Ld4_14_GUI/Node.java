/*
 * Author: Arturs Kuzmiks
 */

/*
 * Author: Arturs Kuzmiks
 */

/*
 * Author: Arturs Kuzmiks
 */

package Ld4_14_GUI;

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
