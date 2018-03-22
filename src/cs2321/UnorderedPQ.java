package cs2321;

import net.datastructures.Entry;
import net.datastructures.Position;
import net.datastructures.PositionalList;
import net.datastructures.PriorityQueue;

import java.util.Comparator;

/**
 * A PriorityQueue based on an Unordered Doubly Linked List.
 * <p>
 * Course: CS2321 Section ALL
 * Assignment: #3
 *
 * @author Lucas Buccilli
 * @username lpbuccil
 */

public class UnorderedPQ<K, V> implements PriorityQueue<K, V> {

    private PositionalList<Entry<K, V>> list;
    private Comparator<K> c = new DefaultComparator<>();

    public UnorderedPQ() {
        list = new DoublyLinkedList<>();
    }

    public UnorderedPQ(Comparator<K> c) {
        this.c = c;
    }

    /**
     * @return Size of the list
     */
    @TimeComplexity("O(1)")
    @Override
    public int size() {
        return list.size();
    }

    /**
     * @return True if size == 0
     */
    @TimeComplexity("O(1)")
    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * Creates, adds, and returns a new node at the last position in the list.
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return The new node that was assed to the list
     * @throws IllegalArgumentException if key is null
     */
    @TimeComplexity("O(1)")
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        /* TCJ
         * No loops.
		 * Only case is O(1)
		 */
        //If key is null, throw error
        if (key == null) {
            throw new IllegalArgumentException();
        }

        //Create new entry node
        Entry<K, V> newNode = new Entry<K, V>() {

            private final K newKey = key;
            private final V newValue = value;

            @Override
            public K getKey() {
                return newKey;
            }

            @Override
            public V getValue() {
                return newValue;
            }
        };

        list.addLast(newNode);
        return newNode;
    }

    /**
     * Looks through entire list and gets the node with the smallest node
     *
     * @return node with smallest key
     */
    @TimeComplexity("O(n)")
    @Override
    public Entry<K, V> min() {
        /* TCJ
		 * Looks at each element in the list, does not call any methods
		 * that are greater than O(1)
		 */
        if (list.isEmpty()) {
            return null;
        }

        Position<Entry<K, V>> currentNode = list.first();
        Position<Entry<K, V>> min = currentNode;

        //For each node in list
        while (currentNode != null) {

            //if the key in current node is less than our min, set min to current node
            if (c.compare(currentNode.getElement().getKey(), min.getElement().getKey()) < 0) {
                min = currentNode;
            }

            //set current node to next nod, to traverse down list
            currentNode = list.after(currentNode);
        }

        return min.getElement();
    }

    /**
     * Finds the node with the smallest key and removes it
     *
     * @return Node with the smallest key
     */
    @TimeComplexity("O(n)")
    @Override
    public Entry<K, V> removeMin() {
		/* TCJ
		 * Compares each element to the current smallest element.
		 * Does not call any methods greater than O(n)
		 */
        if (list.isEmpty()) {
            return null;
        }

        Position<Entry<K, V>> currentNode = list.first();
        Position<Entry<K, V>> min = currentNode;

        //For each node in list
        while (currentNode != null) {

            //if the key in current node is less than our min, set min to current node
            if (c.compare(currentNode.getElement().getKey(), min.getElement().getKey()) < 0) {
                min = currentNode;
            }

            //set current node to next nod, to traverse down list
            currentNode = list.after(currentNode);
        }

        Entry<K, V> temp = min.getElement();
        list.remove(min);
        return temp;
    }


}
