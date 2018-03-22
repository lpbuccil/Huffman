/**
 * Name:  Lucas Buccilli
 * Class: CS2321
 * Description: Binary tree implementation.
 */

package cs2321;

import net.datastructures.BinaryTree;
import net.datastructures.Position;

import java.util.Iterator;


public class LinkedBinaryTree<E> implements BinaryTree<E> {

    private Node root;
    private int size;

    public LinkedBinaryTree() {
        root = null;
    }

    public LinkedBinaryTree(E data, Node left, Node right, Node parent) {
        root = new Node(data, left, right, parent);
        size++;
    }

    @Override
    public Position<E> root() {
        return root;
    }


    /**
     * Returns parent of node
     *
     * @param p A valid Position within the tree
     * @return Parent of node
     * @throws IllegalArgumentException if not a valid node
     */
    @TimeComplexity("O(1)")
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        /* TCJ
         * No loops
		 */

        if (p instanceof Node) {
            return ((Node) p).getParent();
        } else {
            throw new IllegalArgumentException("Node is not valid");
        }
    }


    /**
     * @param p A valid Position within the tree
     * @return List of children
     * @throws IllegalArgumentException
     */
    @TimeComplexity("O(1)")
    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        /* TCJ
         * No loops
		 */
        ArrayList<Position<E>> list = new ArrayList<>();
        if (p instanceof Node) {
            if (((Node) p).hasLeft()) {
                list.addLast(((Node) p).getLeft());
            }
            if (((Node) p).hasRight()) {
                list.addLast(((Node) p).getRight());
            }
        } else {
            throw new IllegalArgumentException("Node is not valid");
        }

        return list;
    }

    /**
     * @param p A valid Position within the tree
     * @return int number of children
     * @throws IllegalArgumentException
     */
    @TimeComplexity("O(1)")
    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        /* TCJ
         * No loops
		 */
        int count = 0;
        if (p instanceof Node) {
            if (((Node) p).hasLeft()) {
                count++;
            }
            if (((Node) p).hasRight()) {
                count++;
            }
        } else {
            throw new IllegalArgumentException("Node is not valid");
        }

        return count;
    }

    /**
     * Returns true if p has any children
     *
     * @param p A valid Position within the tree
     * @return
     * @throws IllegalArgumentException
     */
    @TimeComplexity("O(1)")
    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        /* TCJ
         * No loops
		 */
        if (p instanceof Node) {
            return ((Node) p).hasRight() || ((Node) p).hasLeft();
        } else {
            throw new IllegalArgumentException("Node is not valid");
        }
    }

    /**
     * Checks if the node is external
     *
     * @param p A valid Position within the tree
     * @return true if the node doesnt have any children
     * @throws IllegalArgumentException
     */
    @TimeComplexity("O(1)")
    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        /* TCJ
         * No loops
		 */
        if (p instanceof Node) {
            return !((Node) p).hasLeft() && !((Node) p).hasRight();
        } else {
            throw new IllegalArgumentException("Node is not valid");
        }
    }


    /**
     * Returns whether p is root
     *
     * @param p A valid Position within the tree
     * @return true if p is root
     * @throws IllegalArgumentException if p is not a valid node
     */
    @TimeComplexity("O(1)")
    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
       /* TCJ
         * No loops
		 */
        if (p instanceof Node) {
            return p == root;
        } else {
            throw new IllegalArgumentException("Node is not valid");
        }
    }

    @TimeComplexity("O(1)")
    @Override
    public int size() {
        return size;
    }

    @TimeComplexity("O(1)")
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return Returns list of all elements
     */
    @TimeComplexity("O(n)")
    @Override
    public Iterator<E> iterator() {
        /* TCJ
         * Adds each element of binary tree's nodes to list
		 */
        ArrayList<E> list = new ArrayList<>();
        for (Position<E> node : positions()) {
            list.addLast(node.getElement());
        }
        Iterator iterator = list.iterator();
        return iterator;
    }

    /**
     * @return list of all nodes in tree
     */
    @TimeComplexity("O(n)")
    @Override
    public Iterable<Position<E>> positions() {
        return Traversal(root);
    }

    /**
     * Helper method for pre order traversal
     *
     * @param node
     * @return
     */
    private ArrayList<Node<E>> Traversal(Node<E> node) {

        ArrayList<Node<E>> list = new ArrayList<>();

        if (node == null) {
            return list;
        }

        ArrayList<Node<E>> left = Traversal(node.getLeft());
        ArrayList<Node<E>> right = Traversal(node.getRight());


        list.addLast(node);
        for (Node<E> ln : left) {
            list.addLast(ln);
        }

        for (Node<E> rn : right) {
            list.addLast(rn);
        }

        return list;
    }

    /**
     * Returns the left position of the node
     *
     * @param p A valid Position within the tree
     * @return left child of p
     * @throws IllegalArgumentException if not of type Node
     */
    @TimeComplexity("O(1)")
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        /* TCJ
         * No loops
		 */
        if (p instanceof Node) {
            return ((Node) p).getLeft();
        } else {
            throw new IllegalArgumentException("Node is not valid");
        }
    }


    /**
     * Returns right position of node
     *
     * @param p A valid Position within the tree
     * @return right child of p
     * @throws IllegalArgumentException if not of type Node
     */
    @TimeComplexity("O(1)")
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        /* TCJ
         * No loops
		 */
        if (p instanceof Node) {
            return ((Node) p).getRight();
        } else {
            throw new IllegalArgumentException("Node is not valid");
        }
    }


    /**
     * Returns the other child of the parent
     *
     * @param p A valid Position within the tree
     * @return Other child
     * @throws IllegalArgumentException if p is not of type node
     */
    @TimeComplexity("O(1)")
    @Override
    public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
        /* TCJ
         * No loops
		 */
        if (p instanceof Node) {

            Node parent = ((Node) p).getParent();

            if (parent.hasLeft() && parent.hasRight()) {
                if (p == parent.getLeft()) {
                    return parent.getRight();
                } else {
                    return parent.getLeft();
                }
            } else {
                return null;
            }


        } else {
            throw new IllegalArgumentException("Node is not valid");
        }
    }

    /* creates a root for an empty tree, storing e as element, and returns the
     * position of that root. An error occurs if tree is not empty.
     */

    /**
     * Creates a root for an empty tree storing e as element.
     *
     * @param e element to store as root
     * @return position of new root
     * @throws IllegalStateException if tree is not empty;
     */
    @TimeComplexity("O(1)")
    public Position<E> addRoot(E e) throws IllegalStateException {
        /* TCJ
         * No loops
		 */
        if (size != 0) {
            throw new IllegalStateException("Tree is not empty");
        } else {
            root = new Node(e, null, null, null);
            size++;
        }

        return root();
    }


    /**
     * Adds left child
     *
     * @param p node to add left to
     * @param e element of left node
     * @return p with left child
     * @throws IllegalArgumentException
     */
    @TimeComplexity("O(1)")
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        /* TCJ
         * No loops
		 */
        if (p instanceof Node) {

            if (((Node) p).hasLeft()) {
                throw new IllegalArgumentException("Node already has a left child");
            } else {
                ((Node) p).setLeft(new Node(e, null, null, (Node) p));
                size++;
            }

        } else {
            throw new IllegalArgumentException("Node is not valid");
        }

        return ((Node) p).getLeft();
    }

    /**
     * Adds right child
     *
     * @param p node to add right to
     * @param e element of right node
     * @return p with right node attached
     * @throws IllegalArgumentException
     */
    @TimeComplexity("O(1)")
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        /* TCJ
         * No loops
		 */
        if (p instanceof Node) {

            if (((Node) p).hasRight()) {
                throw new IllegalArgumentException("Node already has a right child");
            } else {
                ((Node) p).setRight(new Node(e, null, null, (Node) p));
                size++;
            }

        } else {
            throw new IllegalArgumentException("Node is not valid");
        }

        return ((Node) p).getRight();
    }

    /**
     * Combines two binary trees into one
     *
     * @param p  node of new root
     * @param t1 binary tree
     * @param t2 binary tree
     * @throws IllegalArgumentException
     */
    @TimeComplexity("O(1)")
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        /* TCJ
         * No loops
		 */
        if (p == null) {
            throw new IllegalArgumentException("t1 node is null");
        }

        if (p instanceof Node) {

            if (isExternal(p)) {
                ((Node) p).setLeft(t1.root);
                ((Node) p).setRight(t2.root);
                t1.root.setParent((Node) p);
                t2.root.setParent((Node) p);
                size += t1.size + t2.size;
            } else {
                throw new IllegalArgumentException("Node is not external");
            }

        } else {
            throw new IllegalArgumentException("Node is not valid");
        }

    }


    public static class Node<E> implements Position<E> {
        private E Element;
        private Node Left;
        private Node Right;
        private Node Parent;

        Node(E data, Node left, Node right, Node parent) {
            Element = data;
            Left = left;
            Right = right;
            Parent = parent;
        }

        @Override
        public E getElement() {
            return Element;
        }

        public void setElement(E element) {
            Element = element;
        }

        Node getLeft() {
            return Left;
        }

        void setLeft(Node left) {
            Left = left;
        }

        Node getRight() {
            return Right;
        }

        void setRight(Node right) {
            Right = right;
        }

        Node getParent() {
            return Parent;
        }

        void setParent(Node parent) {
            Parent = parent;
        }

        boolean hasRight() {
            return Right != null;
        }

        boolean hasLeft() {
            return Left != null;
        }
    }
}
