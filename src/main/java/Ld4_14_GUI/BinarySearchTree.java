package Ld4_14_GUI;

class BinarySearchTree {

    private Node root;
    private int size;
    private int maxSize;
    private StringBuilder str = new StringBuilder();


    BinarySearchTree(int maxSize) {
        this.root = null;
        this.maxSize = maxSize;
        this.size = 0;
    }

    void add(int data) {
        if (!isFull()) {
            root = insert(root, data);
            size++;
        }
    }

    boolean isFull() {
        return size == maxSize;
    }

    private int perCountTwoChild(Node node) {
        int count = 0;
        if (node != null) {
            if (node.getLeft() != null && node.getRight() != null) {
                /*System.out.println("Node: " + node.getData());
                System.out.println("Left: " + node.getLeft().getData());
                System.out.println("Right: " + node.getRight().getData() + "\n");*/
                count++;
            }
            count += perCountTwoChild(node.getLeft());
            count += perCountTwoChild(node.getRight());
        }
        return count;
    }

    private int evenNumberCount(Node node) {
        int count = 0;
        if (node != null) {
            if (node.getData() % 2 == 0) count++;
            count += evenNumberCount(node.getLeft());
            count += evenNumberCount(node.getRight());
        }
        return count;

    }

    private void traversePostOrd(Node node) {
        if (node != null) {
            traversePostOrd(node.getLeft());
            traversePostOrd(node.getRight());
            str.append(node.getData()).append(" ");
        }
    }

    private Node insert(Node current, int data) {
        if (current == null) {
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

    int twoChildPerCount() {
        return perCountTwoChild(root);
    }

    int evenCount() {
        return evenNumberCount(root);
    }

    String postOrder() {
        traversePostOrd(root);
        return str.toString();
    }

}