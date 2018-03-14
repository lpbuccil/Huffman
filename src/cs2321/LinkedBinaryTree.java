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



    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {

        if (p instanceof Node){
            return ((Node) p).getParent();
        }else {
            throw new IllegalArgumentException("Node is not valid");
        }
    }


    //Not sure if this is only direct children or not
    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        ArrayList<Position<E>> list = new ArrayList<>();
        if (p instanceof Node){
            if (((Node) p).hasLeft()){
                list.addLast(((Node) p).getLeft());
            }
            if (((Node) p).hasRight()){
                list.addLast(((Node) p).getRight());
            }
        }else {
            throw new IllegalArgumentException("Node is not valid");
        }

        return list;
    }

    @Override
    /* count only direct child of the node, not further descendant. */
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        int count = 0;
        if (p instanceof Node){
            if (((Node) p).hasLeft()){
                count++;
            }
            if (((Node) p).hasRight()){
                count++;
            }
        }else {
            throw new IllegalArgumentException("Node is not valid");
        }

        return count;
    }

    /**
     * Returns true if p has any children
     * @param p    A valid Position within the tree
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        if (p instanceof Node){
            if (((Node) p).hasRight() || ((Node) p).hasLeft()){
                return true;
            }else {
                return false;
            }
        }else {
            throw new IllegalArgumentException("Node is not valid");
        }
    }

    /**
     * Checks if the node is external
     * @param p    A valid Position within the tree
     * @return  true if the node doesnt have any children
     * @throws IllegalArgumentException
     */
    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        if (p instanceof Node){
            if(!((Node) p).hasLeft() && !((Node) p).hasRight()){
                return true;
            }else {
                return false;
            }
        }else {
            throw new IllegalArgumentException("Node is not valid");
        }
    }



    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        if (p instanceof Node){
            if(p == this.root){
                return true;
            }else {
                return false;
            }
        }else {
            throw new IllegalArgumentException("Node is not valid");
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Iterator<E> iterator() {

        ArrayList<E> list = new ArrayList<>();
        for (Position<E> node : positions()){
            list.addLast(node.getElement());
        }
        Iterator iterator = list.iterator();
        return iterator;
    }

    @Override
    public Iterable<Position<E>> positions() {
        return Traversal(root);
    }

    public ArrayList<Node<E>> Traversal(Node<E> node){

        ArrayList<Node<E>> list = new ArrayList<>();

        if (node == null){
            return list;
        }

        ArrayList<Node<E>> left = Traversal(node.getLeft());
        ArrayList<Node<E>> right = Traversal(node.getRight());


        list.addLast(node);
        for (Node<E> ln : left){
            list.addLast(ln);
        }

        for (Node<E> rn : right){
            list.addLast(rn);
        }

        return list;
    }

    /**
     * Returns the left position of the node
     * @param p A valid Position within the tree
     * @return left child of p
     * @throws IllegalArgumentException if not of type Node
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        if (p instanceof Node){
            return ((Node) p).getLeft();
        }else{
            throw new IllegalArgumentException("Node is not valid");
        }
    }


    /**
     * Returns right position of node
     * @param p A valid Position within the tree
     * @return right child of p
     * @throws IllegalArgumentException if not of type Node
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        if (p instanceof Node){
            return ((Node) p).getRight();
        }else{
            throw new IllegalArgumentException("Node is not valid");
        }
    }


    /**
     * Returns the other child of the parent
     * @param p A valid Position within the tree
     * @return Other child
     * @throws IllegalArgumentException if p is not of type node
     */
    @Override
    public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
        if (p instanceof Node){

            Node parent = ((Node) p).getParent();

            if (parent.hasLeft() && parent.hasRight()) {
                if (p == parent.getLeft()) {
                    return parent.getRight();
                } else {
                    return parent.getLeft();
                }
            }else {
                return null;
            }


        }else{
            throw new IllegalArgumentException("Node is not valid");
        }
    }

    /* creates a root for an empty tree, storing e as element, and returns the
     * position of that root. An error occurs if tree is not empty.
     */

    /**
     * Creates a root for an empty tree storing e as element.
     * @param e element to store as root
     * @return position of new root
     * @throws IllegalStateException if tree is not empty;
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (size != 0){
            throw new IllegalStateException("Tree is not empty");
        }else {
            this.root = new Node(e , null,null, null);
            size++;
        }

        return root();
    }

    /* creates a new left child of Position p storing element e, return the left child's position.
     * If p has a left child already, throw exception IllegalArgumentExeption.
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        if (p instanceof Node){

            if (((Node) p).hasLeft()){
                throw new IllegalArgumentException("Node already has a left child");
            }else {
                ((Node) p).setLeft(new Node(e , null, null, (Node) p));
                size++;
            }

        }else {
            throw new IllegalArgumentException("Node is not valid");
        }

        return ((Node) p).getLeft();
    }

    /* creates a new right child of Position p storing element e, return the right child's position.
     * If p has a right child already, throw exception IllegalArgumentExeption.
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        if (p instanceof Node){

            if (((Node) p).hasRight()){
                throw new IllegalArgumentException("Node already has a right child");
            }else {
                ((Node) p).setRight(new Node(e , null, null, (Node) p));
                size++;
            }

        }else {
            throw new IllegalArgumentException("Node is not valid");
        }

        return ((Node) p).getRight();
    }

    /* Attach trees t1 and t2 as left and right subtrees of external Position.
     * if p is not external, throw IllegalArgumentExeption.
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException{

        if (p == null){
            throw new IllegalArgumentException("t1 node is null");
        }

        if (p instanceof Node){

            if (isExternal(p)){
                ((Node) p).setLeft(t1.root);
                ((Node) p).setRight(t2.root);
                t1.root.setParent((Node) p);
                t2.root.setParent((Node) p);
                size += t1.size + t2.size;
            }else {
                throw new IllegalArgumentException("Node is not external");
            }

        }else {
            throw new IllegalArgumentException("Node is not valid");
        }

    }



    public static class Node<E> implements Position<E> {
        private E Element;
        private Node Left;
        private Node Right;
        private Node Parent;

        public Node(E data, Node left, Node right, Node parent) {
            this.Element = data;
            this.Left = left;
            this.Right = right;
            this.Parent = parent;
        }

        @Override
        public E getElement() {
            return Element;
        }

        public void setElement(E element) {
            Element = element;
        }

        public Node getLeft() {
            return Left;
        }

        public void setLeft(Node left) {
            Left = left;
        }

        public Node getRight() {
            return Right;
        }

        public void setRight(Node right) {
            Right = right;
        }

        public Node getParent() {
            return Parent;
        }

        public void setParent(Node parent) {
            Parent = parent;
        }

        public boolean hasRight(){
            if (this.Right == null){
                return false;
            }else {
                return true;
            }
        }

        public boolean hasLeft(){
            if (this.Left == null){
                return false;
            }else {
                return true;
            }
        }
    }
}
