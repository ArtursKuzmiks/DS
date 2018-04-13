package Ld4_14;

class BinarySearchTree {

    private Node root;
    private int size;

    BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    void add(int data) {
        root = insert(root, data);
        size++;
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
            System.out.print(node.getData() + "\t");
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

    void postOrder() {
        traversePostOrd(root);
    }

}
