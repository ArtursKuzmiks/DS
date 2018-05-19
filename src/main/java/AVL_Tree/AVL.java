/*
 * Author: Arturs Kuzmiks
 */

package AVL_Tree;

class AVLTree {

    private class Node {
        private int data;
        private Node left;
        private Node right;
        private Node parent;
        private int height;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 0;
        }

        int getData() {
            return data;
        }

        void setData(int data) {
            this.data = data;
        }

        Node getLeft() {
            return left;
        }

        void setLeft(Node left) {
            this.left = left;
            if (left != null)
                left.parent = this;
        }

        Node getRight() {
            return right;
        }

        void setRight(Node right) {
            this.right = right;
            if (right != null)
                right.parent = this;
        }

        int getHeight() {
            return height;
        }

        void setHeight(int height) {
            this.height = height;
        }

        Node getParent() {
            return parent;
        }

        void setNullParent() {
            this.parent = null;
        }
    }

    private Node root;
    private int size;

    AVLTree() {
        this.root = null;
        this.size = 0;
    }

    void insert(int data) {
        insert(root, data);
    }

    void delete(int key) {
        Node node = search(key);
        if (node != null) {
            node = deleteNode(node);
            try {
                if (node.getParent() == null)
                    balanceTree(root);
                else
                    balanceTree(node.getParent());
            } catch (NullPointerException ignored) {
            }
        }
    }

    void traverseInOrder() {
        inOrder(root);
    }

    void traversPreOrder() {
        preOrder(root);
    }

    void traversPostOrder() {
        postOrder(root);
    }

    int getSize() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    int getRootData() {
        return root.getData();
    }

    private Node search(int key) {
        return binarySearch(root, key);
    }

    private int height(Node node) {
        return node == null ? -1 : node.getHeight();
    }

    private void reHeight(Node node) {
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
    }

    private void insert(Node node, int data) {
        if (node == null) {
            size++;
            root = new Node(data);
            return;
        }

        if (data < node.getData()) {
            if (node.getLeft() == null) {
                node.setLeft(new Node(data));
                size++;
            } else {
                insert(node.getLeft(), data);
            }
            if (height(node.getLeft()) - height(node.getRight()) == 2) {
                if (data < node.getLeft().getData()) {
                    rotateR(node);
                } else {
                    rotateLR(node);
                }
            }
        } else {
            if (data > node.getData()) {
                if (node.getRight() == null) {
                    node.setRight(new Node(data));
                    size++;
                } else {
                    insert(node.getRight(), data);
                }
                if (height(node.getRight()) - height(node.getLeft()) == 2) {
                    if (data > node.getRight().getData()) {
                        rotateL(node);
                    } else {
                        rotateRL(node);
                    }
                }
            }
        }
        reHeight(node);
    }

    private void rotateR(Node node) {
        Node parent = node.getParent();
        Node leftChild = node.getLeft();
        Node rightChildOfLeftChild = leftChild.getRight();
        node.setLeft(rightChildOfLeftChild);
        leftChild.setRight(node);

        if (parent == null) {
            this.root = leftChild;
            root.setNullParent();
            return;
        }

        if (parent.getLeft() == node) {
            parent.setLeft(leftChild);
        } else {
            parent.setRight(leftChild);


        }

        reHeight(node);
        reHeight(leftChild);
    }

    private void rotateL(Node node) {
        Node parent = node.getParent();
        Node rightChild = node.getRight();
        Node leftChildOfRightChild = rightChild.getLeft();
        node.setRight(leftChildOfRightChild);
        rightChild.setLeft(node);

        if (parent == null) {
            this.root = rightChild;
            root.setNullParent();
            return;
        }
        if (parent.getLeft() == node) {
            parent.setLeft(rightChild);
        } else {
            parent.setRight(rightChild);
        }

        reHeight(node);
        reHeight(rightChild);
    }

    private void rotateLR(Node node) {
        rotateL(node.getLeft());
        rotateR(node);
    }

    private void rotateRL(Node node) {
        rotateR(node.getRight());
        rotateL(node);
    }

    private Node binarySearch(Node node, int key) {
        if (node == null) return null;

        if (key == node.getData())
            return node;

        if (key < node.getData() && node.getLeft() != null)
            return binarySearch(node.getLeft(), key);

        if (key > node.getData() && node.getRight() != null)
            return binarySearch(node.getRight(), key);

        return null;
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.print(node.getData() + "\t");
            inOrder(node.getRight());
        }
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.getData() + "\t");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.getData() + "\t");
        }
    }

    private void balanceTree(Node node) {
        int diff = height(node.getRight()) - height(node.getLeft());
        Node parent = node.getParent();
        if (diff == -2) {
            if (height(node.getLeft().getLeft()) >= height(node.getLeft().getRight()))
                rotateR(node);
            else
                rotateLR(node);
        } else {
            if (diff == 2) {
                if (height(node.getRight().getRight()) >= height(node.getRight().getLeft()))
                    rotateL(node);
                else
                    rotateRL(node);
            }

        }

        if (parent != null)
            balanceTree(parent);

        reHeight(node);
    }

    private Node deleteNode(Node node) {
        if (isLeaf(node)) {
            if (node == root) {
                root = null;
                size--;
                return root;
            } else {
                if (isLeftChild(node))
                    node.getParent().setLeft(null);
                else
                    node.getParent().setRight(null);
            }
            size--;
        } else {
            if (node.getLeft() == null || node.getRight() == null) {
                Node temp = node.getLeft() == null ? node.getRight() : node.getLeft();
                if (isLeftChild(node))
                    if (node == root) {
                        temp.setRight(node.getRight());
                        root = temp;
                        root.setNullParent();
                    } else {
                        node.getParent().setLeft(temp);
                    }
                else if (node == root) {
                    temp.setLeft(node.getLeft());
                    root = temp;
                    root.setNullParent();
                } else {
                    node.getParent().setRight(temp);
                }
            } else {
                Node temp = predInOrd(node);
                node.setData(temp.getData());
                node = deleteNode(temp);
            }
            size--;
        }
        if (node.getParent() == null)
            reHeight(root);
        else
            reHeight(node.getParent());
        return node;
    }

    private Node predInOrd(Node node) {
        Node temp = node.getLeft();
        while (temp.getRight() != null) {
            temp = temp.getRight();
        }
        return temp;
    }

    private boolean isLeaf(Node node) {
        return node.getLeft() == null && node.getRight() == null;
    }

    private boolean isLeftChild(Node node) {
        if (node == root) {
            return root.getLeft() == node;
        } else
            return node.getParent().getLeft() == node;
    }


}


public class AVL {
    public static void main(String[] args) {
        AVLTree avl = new AVLTree();
        avl.insert(1);
        avl.delete(1);
        avl.traverseInOrder();

    }
}
