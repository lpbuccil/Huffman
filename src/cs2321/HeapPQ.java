package cs2321;

import net.datastructures.AdaptablePriorityQueue;
import net.datastructures.Entry;

import java.util.Comparator;

/**
 * A Adaptable PriorityQueue based on an heap.
 * <p>
 * Course: CS2321 Section ALL
 * Assignment: #3
 *
 * @author Lucas Buccilli
 * @username lpbuccil
 */

public class HeapPQ<K, V> implements AdaptablePriorityQueue<K, V> {

    private ArrayList<AdaptableEntry<K, V>> list;
    private Comparator<K> c = new DefaultComparator<>();


    public HeapPQ() {
        list = new ArrayList<>();
    }


    public HeapPQ(Comparator<K> c) {
        this.c = c;
        list = new ArrayList<>();
    }

    /**
     * Checks if the current index has a parent
     *
     * @param i index of node to check
     * @return true if has a parent
     */
    @TimeComplexity("O(1)")
    public boolean hasParent(int i) {
        /* TCJ
         * No loops.
		 * Only case is O(1)
		 */
        if (((i - 1) / 2) < 0) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the current index has a left child
     *
     * @param i index to check
     * @return true if has a left child
     */
    @TimeComplexity("O(1)")
    public boolean hasLeft(int i) {
         /* TCJ
         * No loops.
		 * Only case is O(1)
		 */
        if (((2 * i) + 1) > list.size() - 1) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the node has a right child
     *
     * @param i index of the node to check
     * @return true if has right child
     */
    @TimeComplexity("O(1)")
    public boolean hasRight(int i) {
         /* TCJ
         * No loops.
		 * Only case is O(1)
		 */
        if (((2 * i) + 2) > list.size() - 1) {
            return false;
        }
        return true;
    }

    /**
     * Returns the index of the parent node
     *
     * @param i index of the node who's parent to find
     * @return index of the parent node
     */
    @TimeComplexity("O(1)")
    public int parent(int i) {
         /* TCJ
         * No loops.
		 * Only case is O(1)
		 */
        if (i == 0 || i >= list.size()) {
            return -1;
        }
        return (i - 1) / 2;
    }

    /**
     * Returns the index of the left child's node
     *
     * @param i index of the parent node
     * @return index of the left child
     */
    @TimeComplexity("O(1)")
    public int left(int i) {
         /* TCJ
         * No loops.
		 * Only case is O(1)
		 */
        if (hasLeft(i)) {
            return (2 * i) + 1;
        }
        return -1;
    }

    /**
     * Returns the index of the right child
     *
     * @param i index of the parent node
     * @return index of the right child
     */
    @TimeComplexity("O(1)")
    public int right(int i) {
         /* TCJ
         * No loops.
		 * Only case is O(1)
		 */
        if (hasRight(i)) {
            return (2 * i) + 2;
        }
        return -1;
    }

    /**
     * Checks if the parent's key is greater than the node at index j. If so swap and
     * upheap at parents node
     *
     * @param j index of node to start upheap
     */
    @TimeComplexity("O(lg n)")
    public void upheap(int j) {
         /* TCJ
		 * If inserted in index (size-1) then there is a
		 * possibility of it needing to get to index = 0;
		 * Each parent has two children, but the new node only need to
		 * traverse up to the parents index
		 * this results in the amount of times it could be swapped being
		 * O(log(n)
		 */
        //base case
        if (j == 0) {
            return;
        }
        int p = parent(j);
        if (c.compare(list.get(j).getKey(), list.get(p).getKey()) < 0) {
            swap(list, j, p);
            upheap(p);
        }
    }

    /**
     * Swaps the two nodes in the array, also swaps the index of nodes
     *
     * @param A array that contains the nodes to swap
     * @param i index of node 1
     * @param j index of node 2
     */
    @TimeComplexity("O(1)")
    public void swap(ArrayList<AdaptableEntry<K, V>> A, int i, int j) {
         /* TCJ
		 * No loops.
		 * Only case is O(1)
		 */

        //Set tempI to old I entry
        AdaptableEntry<K, V> tempI = A.get(i);
        //Set tempJ to old J entry
        AdaptableEntry<K, V> tempJ = A.get(j);
        //Original index of I
        int origIndexI = tempI.getIndex();
        //Original index of J
        int origIndexJ = A.get(j).getIndex();
        //Set Index of I to J's original
        tempI.setIndex(origIndexJ);
        //Set index of J to I's Original
        tempJ.setIndex(origIndexI);

        A.set(origIndexI, tempJ);
        A.set(origIndexJ, tempI);


    }

    /**
     * Compares node at index j with its largest child, if parent in bigger, then it
     * swaps them. It will then down heap the index of the new position of the parent
     * that got swapped down
     *
     * @param j index of parent node
     */
    @TimeComplexity("O(lg n)")
    public void downheap(int j) {
        /* TCJ
		 * For each downheap, it has to check that both children are greater
		 * than its parent, if downheaping the index 0, it will then have to check
		 * both child and swap the the smaller of the two
		 * then down heap with the parents new position, this could result in a
		 * time complexity  no greater that n-1 so O(lg n)
		 */

        if (!hasLeft(j)) {
            return;
        }
        int s = left(j);
        if (hasRight(j) && c.compare(list.get(s).getKey(), list.get(right(j)).getKey()) > 0) {
            s = right(j);
        }

        if (c.compare(list.get(s).getKey(), list.get(j).getKey()) < 0) {
            swap(list, s, j);
            downheap(s);
        }

    }

    /**
     * @return size of the array list
     */
    @TimeComplexity("O(1)")
    @Override
    public int size() {
        return list.size();
    }

    /**
     * @return True if size is 0
     */
    @TimeComplexity("O(1)")
    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * Inserts a new value at the last spot of the array, or the
     * next spot in the heap that keeps it complete. Then runs upheap at the new nodes
     * index to maintain proper heap property
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return New node that was inserted
     * @throws IllegalArgumentException if key is null
     */
    @TimeComplexity("O(lg n)")
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        /* TCJ
		 * This method itself is onlyt O(1)
		 * but it calls upheap which is O(lg n)
		 */
        if (key == null) {
            return null;
        }

        //Creates new node with index
        AdaptableEntry<K, V> newNode = new AdaptableEntry<>(key, value);
        newNode.setIndex(list.size());
        list.addLast(newNode);
        upheap(list.size() - 1);
        return newNode;

    }

    /**
     * @return node with smalled key
     */
    @TimeComplexity("O(1)")
    @Override
    public Entry<K, V> min() {
        return list.get(0);
    }

    /**
     * Returns node with smallest key, then removes it from the heap
     *
     * @return node with smallest key
     */
    @TimeComplexity("O(lg n)")
    @Override
    public Entry<K, V> removeMin() {
        /* TCJ
		 * This method itself is only O(1)
		 * and calls downHeap which is O(lg n)
		 */

        if (isEmpty()) {
            return null;
        }
        Entry<K, V> temp = min();
        swap(list, 0, list.size() - 1);
        list.remove(list.size() - 1);
        downheap(0);
        return temp;
    }


    /**
     * Removes the node
     *
     * @param entry an entry of this priority queue
     * @throws IllegalArgumentException if node is not of type AdaptableEntry
     */
    @TimeComplexity("O(lg n)")
    @Override
    public void remove(Entry<K, V> entry) throws IllegalArgumentException {
        /* TCJ
		 * This method itself is only O(1)
		 * but it calls downheap which is O(lg n)
		 */
        if (isEmpty()) {
            return;
        }

        AdaptableEntry<K, V> newEntry = (AdaptableEntry<K, V>) entry;
        try {

            int index = newEntry.getIndex();
            swap(list, index, list.size() - 1);
            list.remove(list.size() - 1);
            downheap(0);


        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

    }


    /**
     * Replaces the key of the specified node
     *
     * @param entry an entry of this priority queue
     * @param key   the new key
     * @throws IllegalArgumentException if node is not of type AdaptableEntry or if new
     *                                  key is null
     */
    @TimeComplexity("O(lg n)")
    @Override
    public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
        /* TCJ
		 * This method itself is only O(1)
		 * but it may call downheap which is O(lg n)
		 */

        //Cast entry to AdaptableEntry
        AdaptableEntry<K, V> newNode = (AdaptableEntry<K, V>) entry;

        //If get index fails, then it is not of type Adaptable Entry
        try {
            newNode.getIndex();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        //If new key is null
        if (key == null) {
            throw new IllegalArgumentException();
        }

        //if old key is less than new key, downheap.
        if (c.compare(newNode.getKey(), key) < 0) {
            newNode.setKey(key);
            downheap(newNode.getIndex());

            //If old key is greater than new key, upheap.
        } else if (c.compare(newNode.getKey(), key) > 0) {
            newNode.setKey(key);
            upheap(newNode.getIndex());
        }

        //Keys are the same
        return;


    }


    /**
     * Changes the value of the node
     *
     * @param entry an entry of this priority queue
     * @param value the new value
     * @throws IllegalArgumentException if entry is not of type AdaptableEntry
     */
    @TimeComplexity("O(1)")
    @Override
    public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
        /* TCJ
		 * No loops.
		 */

        AdaptableEntry<K, V> newNode = (AdaptableEntry<K, V>) entry;
        try {
            newNode.setValue(value);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

    }

    /**
     * New object for Adaptable Entry, holds index and has new methods to change
     * key and value
     *
     * @param <K>
     * @param <V>
     */
    @TimeComplexity("O(1)")
    private static class AdaptableEntry<K, V> implements Entry<K, V> {
        private int index;
        private K Key;
        private V Value;

        public AdaptableEntry(K key, V value) {
            this.Key = key;
            this.Value = value;
        }

        @Override
        public K getKey() {
            return Key;
        }

        protected void setKey(K key) {
            Key = key;
        }

        @Override
        public V getValue() {
            return Value;
        }

        protected void setValue(V value) {
            Value = value;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

    }


}
