/*
 * Name: Brian Huang
 * Email: cch002@ucsd.edu
 * PID: A17694562
 * SOurce used: CSE12 PA8 write-up, Lecture side, zybook, PA8 public tester
 * 
 * This file is the custom tester file
 */
import org.junit.Before;
import org.junit.Test;

import java.beans.Transient;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * This is the custom tester class.
 */
public class CustomTester {
    MyBST<Integer, Integer> tree;

    /**
     * set up
     */
    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root =
                new MyBST.MyBSTNode<>(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two =
                new MyBST.MyBSTNode<>(2, 1, root);
        MyBST.MyBSTNode<Integer, Integer> six =
                new MyBST.MyBSTNode<>(6, 1, root);
        MyBST.MyBSTNode<Integer, Integer> one =
                new MyBST.MyBSTNode<>(1, 2, two);
        MyBST.MyBSTNode<Integer, Integer> three =
                new MyBST.MyBSTNode<>(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five =
                new MyBST.MyBSTNode<>(5, 50, six);

        this.tree = new MyBST<>();
        this.tree.root = root;
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        tree.size = 6;
    }

    /**
     * Test to see if successor returns null when it's the largest node.
     */
    @Test
    public void testSuccessorNoLarger() {
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        assertSame(null, treeRoot.getRight().successor());
    }

    
    /**
     * Test to see if a nullpointer exception is thrown when
     * key is null
     */
    @Test
    public void InsertNull(){
        assertThrows(NullPointerException.class, () ->{
            tree.insert(null, 12);
        });  
    }

    /**
     * If insert returns the value replaced if the key already exists in the tree
     */
    @Test
    public void InsertReturnValue(){
        assertSame(1, tree.insert(4, 2));
    }

    /**
     * Test to insert an element to an empty tree
     */
    @Test
    public void InsertToEmptyTree(){
        MyBST emptyTree = new MyBST<Integer, Integer>();
        emptyTree.insert(4, 1);
        assertSame(4,emptyTree.root.getKey());
    }

    /**
     * If search returns null when the key doesn't exist
     */
    @Test
    public void SearchNull(){
        assertSame(null, tree.search(10));
    }

    /**
     * Test remove on a non-leaf node
     */
    @Test
    public void RemoveRoot(){
        //return
        assertSame(1, tree.remove(4));
        //size
        assertSame(5, tree.size());
        //relation
        assertSame(5, tree.root.getKey());
        assertSame(50, tree.root.getValue());
    }

    /**
     * Test remove on a non-leaf node 
     */
     @Test
     public void RemoveNonLeaf(){
        //return
        assertSame(1, tree.remove(2));
        //size
        assertSame(5, tree.size());
        //relation
        assertSame(3, tree.root.getLeft().getKey());
        assertSame(30, tree.root.getLeft().getValue());
     }

     /**
      * Test remove a leaf that doesn't exists
      */
      @Test
      public void RemoveNotInTree(){
        assertSame(null, tree.remove(100));
      }



}
