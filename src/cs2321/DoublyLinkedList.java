/**
 * Name:  Lucas Buccilli
 * Assignment number:  1
 * Description: Creates a doubly linked list with nodes.
 */


package cs2321;

import net.datastructures.Position;
import net.datastructures.PositionalList;

import java.util.Iterator;


public class DoublyLinkedList<E> implements PositionalList<E> {

    private final Node<E> head;
    private final Node<E> tail;
    private int size;

    public DoublyLinkedList() {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, head, null);
        head.next = tail;
        size = 0;
    }

    /**
     * returns the number of elements in the doubly linked list
     *
     * @return int size
     */
    @TimeComplexity("O(1)")
    @Override
    public int size() {
        /* TCJ
        * No loops, only returning a value.
		*/
        return size;
    }

    /**
     * returns whether the doubly linked list is empty
     *
     * @return boolean if list is empty
     */
    @Override
    @TimeComplexity("O(1)")
    public boolean isEmpty() {
        /* TCJ
        * No loops, only returning a value
		*/
        return size == 0;
    }

    /**
     * Returns the fist position in the doubly linked list
     *
     * @return the first position
     */
    @TimeComplexity("O(1)")
    @Override
    public Position<E> first() {
        /* TCJ
        * No loops, only returning a value
		*/
        if (head.next == tail) {
            return null;
        } else {
            return head.next;
        }
    }

    /**
     * returns the last position in the list
     *
     * @return last position
     */
    @TimeComplexity("O(1)")
    @Override
    public Position<E> last() {
        /* TCJ
        * No loops, only returning a value
		*/
        if (tail.prev == head) {
            return null;
        } else {
            return tail.prev;
        }
    }

    /**
     * Returns the position before p
     *
     * @param p of element you want to know the previous one
     * @return position of the previous element
     */
    @TimeComplexity("O(1)")
    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        /* TCJ
        *
		* Worst case is O(1)
		*/

        Node<E> current = (Node<E>) p;

        if (current == head) {
            throw new IllegalArgumentException();
        } else if (current.prev == head) {

            return null;
        } else {
            return current.prev;
        }

    }

    /**
     * Returns the position of the element after the specified position
     *
     * @param p of element you want to find the one after
     * @return Position after the position
     */
    @TimeComplexity("O(1)")
    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        /* TCJ
        *
		*Worst case is O(1)
		*/
        Node<E> current = (Node<E>) p;


        if (current == tail) {
            throw new IllegalArgumentException();
        } else if (current.next == tail) {
            return null;
        } else {
            return current.next;
        }
    }

    /**
     * Adds a new first element and returns the position
     *
     * @param e of the new element
     * @return Position of the new node
     */
    @TimeComplexity("O(1)")
    @Override
    public Position<E> addFirst(E e) {
        /* TCJ
        * No loops, only accesses values. Worst case is O(1)
		*/
        Node<E> second = head.next;
        Node<E> newNode = new Node<>(e, head, second);
        head.next = newNode;
        second.prev = newNode;
        size++;
        return newNode;
    }

    /**
     * Adds a new element to the end of the list and returns the position
     *
     * @param e to be added
     * @return Position of new node
     */
    @TimeComplexity("O(1)")
    @Override
    public Position<E> addLast(E e) {
        /* TCJ
        * No Loops, only accesses values, worst case is O(1)
		*/
        Node<E> currentLast = tail.prev;
        Node<E> newNode = new Node<>(e, currentLast, tail);
        tail.prev = newNode;
        currentLast.next = newNode;
        size++;
        return newNode;
    }

    /**
     * Adds a node before a specified position
     *
     * @param p, node for new node to be placed behind, e, element of new node
     * @return position of new node
     */
    @TimeComplexity("O(n)")
    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        /* TCJ
        *Worst case is O(1)
		*/

        Node<E> currentNext = (Node<E>) p;

        if (p == head) {
            throw new IllegalArgumentException();
        }

        Node<E> currentPrev = currentNext.prev;
        Node<E> newNode = new Node<>(e, currentPrev, currentNext);
        currentPrev.next = newNode;
        currentNext.prev = newNode;


        size++;
        return newNode;
    }

    /**
     * Adds a new node after the specified node and returns the position
     *
     * @param p, position of where to add the node after, e, element of noe node
     * @return the position of the new node
     * @throws IllegalArgumentException if trying to add after tail node
     */
    @TimeComplexity("O(1)")
    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        /* TCJ
        *Worst case is O(1)
		*/
        Node<E> currentPrev = (Node<E>) p;

        if (currentPrev == tail) {
            throw new IllegalArgumentException();
        }


        Node<E> currentNext = currentPrev.next;
        Node<E> newNode = new Node<>(e, currentPrev, currentNext);
        currentPrev.next = newNode;
        currentNext.prev = newNode;
        size++;
        return newNode;
    }

    /**
     * Sets a new position to the specified position
     *
     * @param p, position of where to add new node, e, element of new node
     * @return the element of the replaced element
     * @throws IllegalArgumentException if trying to replace head or tail
     */
    @TimeComplexity("O(1)")
    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        /* TCJ
        *
		*Worst case is O(1)
		*/
        Node<E> current = (Node<E>) p;
        if ((current == head) || (current == tail)) {
            throw new IllegalArgumentException();
        }

        Node<E> currentNext = current.next;
        Node<E> currentPrev = current.prev;
        Node<E> newNode = new Node<>(e, currentPrev, currentNext);
        currentPrev.next = newNode;
        currentNext.prev = newNode;

        return current.getElement();
    }

    /**
     * Removes a position and returns the element of it
     *
     * @param p of node to remove
     * @return The element of the node that was removed
     * @throws IllegalArgumentException if element is not found
     */
    @TimeComplexity("O(1)")
    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        /* TCJ
        *
		*Worst case is O(1)
		*/

        Node<E> current = (Node<E>) p;

        if ((current == head) || (current == tail)) {
            throw new IllegalArgumentException();
        }

        Node<E> currentNext = current.next;
        Node<E> currentPrev = current.prev;
        currentNext.prev = currentPrev;
        currentPrev.next = currentNext;
        size--;
        return current.getElement();
    }

    /**
     * Returns an iterator of elements
     *
     * @return Iterator of elements
     */
    @TimeComplexity("O(n)")
    @Override
    public Iterator<E> iterator() {
        Iterator<E> myIterator = new Iterator<E>() {
            /* TCJ
            *Looks through every element.
			*Worst case is O(n)
			*/

            private Node<E> current = head.next;

            @Override
            public boolean hasNext() {
                return current.next != tail;
            }

            @Override
            public E next() {
                E temp = current.getElement();
                current = current.next;
                return temp;
            }

        };
        return myIterator;
    }

    /**
     * Returns an iterator of positions
     *
     * @return iterator of positions
     */
    @TimeComplexity("O(n)")
    @Override
    public Iterable<Position<E>> positions() {
        /* TCJ
        *Looks through every element.
		*Worst case is O(n)
		*/
        DoublyLinkedList<Position<E>> list = new DoublyLinkedList<>();

        Node<E> current = head.next;
        while (current != tail) {
            list.addLast(current);
            current = current.next;
        }
        return list;
    }

    /**
     * Removes the first node and returns the value
     *
     * @return element
     * @throws IllegalArgumentException if value is not found
     */
    @TimeComplexity("O(1)")
    public E removeFirst() throws IllegalArgumentException {
        /* TCJ
        * No loop, only accesses values.
		* Worst case O(1)
		*/
        Node<E> headNext = head.next;
        if (headNext == tail) {
            throw new IllegalArgumentException();
        } else {
            head.next = head.next.next;
            size--;
            return headNext.getElement();
        }

    }

    /**
     * Removes the last node and returns the element
     *
     * @return element of node being removed
     * @throws IllegalArgumentException if node is not found
     */
    @TimeComplexity("O(1)")
    public E removeLast() throws IllegalArgumentException {
        /* TCJ
        * No loop, only accesses values.
		* Worst case O(1)
		*/
        Node<E> tailPrev = tail.prev;
        if (tailPrev == head) {
            throw new IllegalArgumentException();
        } else {
            size--;
            tail.prev = tail.prev.prev;
            return tailPrev.getElement();
        }
    }

    private static class Node<E> implements Position<E> {
        private final E element;
        private Node<E> prev;
        private Node<E> next;

        Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        /**
         * Returns the element stored at this position.
         *
         * @return the stored element
         * @throws IllegalStateException if position no longer valid
         */

        @TimeComplexity("O(1)")
        @Override
        public E getElement() throws IllegalStateException {
            /* TCJ
            *No loops, only setting a value, worst and best case are O(1);
			*/
            if ((next == null) || (prev == null)) {
                throw new IllegalStateException();
            } else {
                return element;
            }
        }

    }

}
