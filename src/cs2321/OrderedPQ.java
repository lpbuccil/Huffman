package cs2321;

import net.datastructures.Entry;
import net.datastructures.Position;
import net.datastructures.PositionalList;
import net.datastructures.PriorityQueue;

import java.util.Comparator;

/**
 * A PriorityQueue based on an ordered Doubly Linked List.
 * <p>
 * Course: CS2321 Section ALL
 * Assignment: #3
 *
 * @author Lucas Buccilli
 * @username lpbuccil
 */

public class OrderedPQ<K, V> implements PriorityQueue<K, V> {

    private PositionalList<Entry<K, V>> list;
    private Comparator<K> c = new DefaultComparator<>();


    public OrderedPQ() {
        list = new DoublyLinkedList<>();
    }

    public OrderedPQ(Comparator<K> c) {
        this.c = c;
    }

    /**
     * @return size of the list
     */
    @TimeComplexity("O(1)")
    @Override
    public int size() {
        return list.size();
    }

    /**
     * @return True is size == 0
     */
    @TimeComplexity("O(1)")
    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * Adds a new node in in the ordered list.
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return
     * @throws IllegalArgumentException if the key is null
     */
    @TimeComplexity("O(n)")
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        /* TCJ
         * Starts at size - 1 and looks the node to see if the key is bigger,
		 * If so, it goes and looks at n-2. If new node's key is small then all in
		 * the list, it will have traversed n elements
		 */

        //If key is null, throw error
        if (key == null) {
            throw new IllegalArgumentException();
        }

        //Create new entry node
        Entry<K, V> newNode = new Entry<K, V>() {

            private K newKey = key;
            private V newValue = value;

            @Override
            public K getKey() {
                return newKey;
            }

            @Override
            public V getValue() {
                return newValue;
            }
        };

        if (list.isEmpty()) {
            list.addFirst(newNode);
            return list.first().getElement();
        }

        Position<Entry<K, V>> currentNode = list.first();

        //Do while current node is not last node
        while (currentNode != null) {

            //If new node is greater than current node
            if (c.compare(newNode.getKey(), currentNode.getElement().getKey()) > 0) {
                currentNode = list.after(currentNode);
                // if new node is less than current node add before current node
            } else {
                list.addBefore(currentNode, newNode);
                return newNode;
            }
        }
        // If here, it means that new node is greater than all nodes in list
        list.addLast(newNode);
        return newNode;

    }


    /**
     * @return The node with the smallest Key
     */
    @TimeComplexity("O(1)")
    @Override
    public Entry<K, V> min() {
        /* TCJ
		 * No loops.
		 * Only case is O(1)
		 */
        if (list.isEmpty()) {
            return null;
        }
        return list.first().getElement();
    }

    /**
     * Removes and returns the node with the smallest key
     *
     * @return Node with the smallest key
     */
    @TimeComplexity("O(1)")
    @Override
    public Entry<K, V> removeMin() {
		/* TCJ
		 * Calls list.remove, this has a O(1) time
		 */
        if (list.isEmpty()) {
            return null;
        }
        Entry<K, V> temp = list.first().getElement();
        list.remove(list.first());
        return temp;

    }

}
