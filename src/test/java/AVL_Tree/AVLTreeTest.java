/*
 * Author: Arturs Kuzmiks
 */

package AVL_Tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {
    private AVLTree avl = new AVLTree();

    @Test
    public void isEmptyTest() {
        assertTrue(avl.isEmpty());
        assertEquals(0, avl.getSize());
    }

    @Test
    public void deleteSizeOneTest(){
        avl.insert(1);
        avl.delete(0);
        assertEquals(1,avl.getSize());
        avl.delete(1);
        assertTrue(avl.isEmpty());
        assertEquals(0, avl.getSize());
        avl.traverseInOrder();
    }

    @Test
    public void deleteTest(){
        avl.insert(1);
        avl.insert(2);
        avl.insert(3);
        assertEquals(2,avl.getRootData());

        avl.delete(2);
        assertEquals(1,avl.getRootData());

        avl.insert(-1);
        avl.insert(2);
        avl.insert(4);
        avl.delete(1);
        assertEquals(3,avl.getRootData());

        avl.insert(1);
        assertEquals(3,avl.getRootData());

        avl.delete(1);
        assertEquals(3,avl.getRootData());

    }


    @Test
    public void insertTest() {
        for (int i = 1; i <= 5; i++) {
            avl.insert(i);
        }
        assertTrue(!avl.isEmpty());
        assertEquals(5,avl.getSize());
    }

}