package Ld4_14;

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
        if(!isFull())
        root = insert(root, data);
    }

    private int perTwoChildCount(Node node) {
        int count = 0;
        if (node != null) {
            if (node.getLeft() != null && node.getRight() != null) {
                count++;
            }
            count += perTwoChildCount(node.getLeft());
            count += perTwoChildCount(node.getRight());
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

    int twoChildPerCount() {
        return perTwoChildCount(root);
    }

    int evenCount() {
        return evenNumberCount(root);
    }

    void postOrder() {
        traversePostOrd(root);
    }

}
