package Tests;

import cs2321.ArrayList;
import cs2321.LinkedBinaryTree;
import net.datastructures.Position;

import static org.junit.Assert.*;

/**
 * @author Lucas Buccilli
 * Class:
 * Date: 3/7/2018
 * Time: 7:25 PM
 * Project Name: Huffman
 * File Name: LinkedBinaryTreeTest
 */
public class LinkedBinaryTreeTest {
    private LinkedBinaryTree<Integer> linkedBinaryTree;
    @org.junit.Before
    public void setUp() throws Exception {
         linkedBinaryTree = new LinkedBinaryTree<>();
    }

    @org.junit.Test
    public void root() throws Exception {
        assertTrue(linkedBinaryTree.root() == null);
        linkedBinaryTree.addRoot(5);
        assertTrue(linkedBinaryTree.root().getElement() == 5);
    }

    @org.junit.Test
    public void parent() throws Exception {

        linkedBinaryTree.addRoot(0);
        LinkedBinaryTree<Integer> left = new LinkedBinaryTree<>();
        LinkedBinaryTree<Integer> right = new LinkedBinaryTree<>();
        left.addRoot(1);
        right.addRoot(2);

        linkedBinaryTree.attach(linkedBinaryTree.root(), left, right);

        assertTrue(linkedBinaryTree.size() == 3);
    }

    @org.junit.Test
    public void children() throws Exception {
        linkedBinaryTree.addRoot(0);
        LinkedBinaryTree<Integer> left = new LinkedBinaryTree<>();
        LinkedBinaryTree<Integer> right = new LinkedBinaryTree<>();
        left.addRoot(1);
        right.addRoot(2);

        linkedBinaryTree.attach(linkedBinaryTree.root(), left, right);

        ArrayList<Position<Integer>> list = new ArrayList<>();

        for (Position<Integer> p : linkedBinaryTree.children(linkedBinaryTree.root())){
            list.addLast(p);
        }

        assertTrue(list.get(0).getElement() == 1);
        assertTrue(list.get(1).getElement() == 2);
    }

    @org.junit.Test
    public void numChildren() throws Exception {

        linkedBinaryTree.addRoot(0);
        LinkedBinaryTree<Integer> left = new LinkedBinaryTree<>();
        LinkedBinaryTree<Integer> right = new LinkedBinaryTree<>();
        left.addRoot(1);
        right.addRoot(2);

        linkedBinaryTree.attach(linkedBinaryTree.root(), left, right);

        assertTrue(linkedBinaryTree.numChildren(linkedBinaryTree.root()) == 2);
    }

    @org.junit.Test
    public void isInternal() throws Exception {
        linkedBinaryTree.addRoot(0);

        assertTrue(linkedBinaryTree.isInternal(linkedBinaryTree.root()) != true);
        LinkedBinaryTree<Integer> left = new LinkedBinaryTree<>();
        LinkedBinaryTree<Integer> right = new LinkedBinaryTree<>();
        left.addRoot(1);
        right.addRoot(2);

        linkedBinaryTree.attach(linkedBinaryTree.root(), left, right);
        assertTrue(linkedBinaryTree.isInternal(linkedBinaryTree.root()) == true);

        assertTrue(linkedBinaryTree.isInternal(linkedBinaryTree.left(linkedBinaryTree.root())) != true);
        assertTrue(linkedBinaryTree.isInternal(linkedBinaryTree.right(linkedBinaryTree.root())) != true);


    }

    @org.junit.Test
    public void isExternal() throws Exception {
        linkedBinaryTree.addRoot(0);

        assertTrue(linkedBinaryTree.isExternal(linkedBinaryTree.root()) == true);
        LinkedBinaryTree<Integer> left = new LinkedBinaryTree<>();
        LinkedBinaryTree<Integer> right = new LinkedBinaryTree<>();
        left.addRoot(1);
        right.addRoot(2);

        linkedBinaryTree.attach(linkedBinaryTree.root(), left, right);
        assertTrue(linkedBinaryTree.isExternal(linkedBinaryTree.root()) != true);

        assertTrue(linkedBinaryTree.isExternal(linkedBinaryTree.left(linkedBinaryTree.root()))== true);
        assertTrue(linkedBinaryTree.isExternal(linkedBinaryTree.right(linkedBinaryTree.root()))== true);
    }

    @org.junit.Test
    public void isRoot() throws Exception {


        Position<Integer> node = linkedBinaryTree.addRoot(0);

        assertTrue(linkedBinaryTree.isRoot(node));

        linkedBinaryTree.addLeft(linkedBinaryTree.root(), 5);

        assertTrue(linkedBinaryTree.isRoot(linkedBinaryTree.left(linkedBinaryTree.root())) != true);
    }

    @org.junit.Test
    public void size() throws Exception {

        assertTrue(linkedBinaryTree.size() == 0);


        linkedBinaryTree.addRoot(0);
        LinkedBinaryTree<Integer> left = new LinkedBinaryTree<>();
        LinkedBinaryTree<Integer> right = new LinkedBinaryTree<>();
        left.addRoot(1);
        right.addRoot(2);
        linkedBinaryTree.attach(linkedBinaryTree.root(), left, right);

        assertTrue(linkedBinaryTree.size() == 3);

    }

    @org.junit.Test
    public void isEmpty() throws Exception {

        assertTrue(linkedBinaryTree.isEmpty());
        linkedBinaryTree.addRoot(0);
        assertTrue(!linkedBinaryTree.isEmpty());
    }

    @org.junit.Test
    public void iterator() throws Exception {

        linkedBinaryTree.addRoot(0);
        LinkedBinaryTree<Integer> left = new LinkedBinaryTree<>();
        LinkedBinaryTree<Integer> right = new LinkedBinaryTree<>();
        left.addRoot(1);
        right.addRoot(2);
        linkedBinaryTree.attach(linkedBinaryTree.root(), left, right);


        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> complist = new ArrayList<>();
        complist.addLast(0);
        complist.addLast(1);
        complist.addLast(2);
        for (Integer key : linkedBinaryTree){
            list.addLast(key);
        }

        for (int i = 0 ; i < 3 ; i++){
            assertTrue(complist.get(i) == list.get(i));
        }


    }


    @org.junit.Test
    public void left() throws Exception {

        linkedBinaryTree.addRoot(0);
        Position<Integer> node = linkedBinaryTree.addLeft(linkedBinaryTree.root(), 1);

        assertTrue(linkedBinaryTree.left(linkedBinaryTree.root()) == node);
    }

    @org.junit.Test
    public void right() throws Exception {
        linkedBinaryTree.addRoot(0);
        Position<Integer> node = linkedBinaryTree.addRight(linkedBinaryTree.root(), 2);

        assertTrue(linkedBinaryTree.right(linkedBinaryTree.root()) == node);
    }

    @org.junit.Test
    public void sibling() throws Exception {
        linkedBinaryTree.addRoot(0);
        Position<Integer> right = linkedBinaryTree.addRight(linkedBinaryTree.root(), 2);
        Position<Integer> left = linkedBinaryTree.addLeft(linkedBinaryTree.root(), 1);

        assertTrue(linkedBinaryTree.sibling(right) == left);

    }

}