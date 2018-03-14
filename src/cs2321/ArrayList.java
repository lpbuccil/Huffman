/**
 * Name:  Lucas Buccilli
 * Assignment number:  1
 * Description: Add more functionality to an array. The array doesn't have a capacity restriction
 * and allows users to insert and remove elements and keep the integrity of the array.
 */


package cs2321;

import net.datastructures.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {

    private E[] array;
    private int size;
    private int capacity;

    /**
     * Default constructor for ArrayList.
     */
    public ArrayList() {
        array = (E[]) new Object[16];
        size = 0;
        capacity = 16;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return integer number of elements in the list
     */
    @TimeComplexity("O(1)")
    @Override
    public int size() {
        /* TCJ
         * Returns a variable, best and worse case is O(1)
		 */
        return size;
    }

    /**
     * Checks if the arrayList is empty.
     *
     * @return true if size equals empty, false otherwise
     */
    @TimeComplexity("O(1)")
    @Override
    public boolean isEmpty() {
		/* TCJ
		 * Compares a variable and a integer, best and worst is O(1)
		 */
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the element at a specified index.
     *
     * @param i index of element to return.
     * @return element as index i
     * @throws IndexOutOfBoundsException if the index is negative or greater than size
     */
    @TimeComplexity("O(1)")
    @Override
    public E get(int i) throws IndexOutOfBoundsException {
		/* TCJ
		 * Returns an array value of which has a O(1) 
		 * Worst case is O(1)
		 */

        if (size == 0) {
            return null;
        }

        if (i >= 0 && i < size) {
            return array[i];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns the element at index i. Then sets element at index i to e.
     *
     * @param i the index the element to set
     * @param e element to be stored
     * @return the element at index i before it is replaced
     * @throws IndexOutOfBoundsException if i is negative or is outside of the array
     */
    @TimeComplexity("O(1)")
    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
		/* TCJ
		 * Sets a variable from the array which has O(1). Sets a value in the array
		 * which has O(1), then returns a value.
		 * Worst case is O(1)
		 */
        if (i < 0 || i > size - 1) {
            throw new IndexOutOfBoundsException();
        }

        E TEMP = array[i];
        array[i] = e;
        return TEMP;
    }

    /**
     * Inserts element e at index i, shifting elements at index >= i to the right to
     * make room
     *
     * @param i the index where to insert element e
     * @param e the element that is to be inserted
     * @throws IndexOutOfBoundsException if i is negative or greater than size
     */
    @TimeComplexity("O(n)")
    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
		/* TCJ
		 * Shifts all elements over, i = 0 is worst case for shifting
		 * If array is full and one is added, a new array is made and 
		 * all values are copied over
		 * 
		 * Worst case is 2n or O(n)
		 * 
		 */
        // If specified index is outside of index size or negative
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException();

            // If Array is full
        }

        if (capacity == size) {

            // Copies array to an array twice the capacity
            E[] newArray = (E[]) new Object[capacity * 2];

            for (int j = 0; j < capacity; j++) {
                newArray[j] = array[j];
            }
            array = newArray;
            capacity = capacity * 2;
        }

        // shifts all elements greater than index i to the right 1
        for (int k = size - 1; k >= i; k--) {
            array[k + 1] = array[k];
        }

        array[i] = e;
        size++;

    }

    /**
     * Returns the element at i and removes it. Then it shifts all following
     * elements to the left 1
     *
     * @param i the index of the element to return and remove
     * @return the element at index i
     * @throws IndexOutOfBoundsException if the index is negative or greater than or equal to size
     */
    @TimeComplexity("O(n)")
    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
		/* TCJ
		 * All elements from index i + 1 are shifted. 
		 * i = 0, would be worst case. This has an O(n)
		 */

        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (i >= size) {
            throw new IndexOutOfBoundsException();
        }

        if ((i == 0) && size == 1) {
            array[0] = null;
        }

        E e = array[i];

        for (int j = i + 1; j <= size - 1; j++) {
            array[j - 1] = array[j];
        }

        array[i] = null;
        size--;
        return e;
    }

    /**
     * Returns an iterator of the elements in the arrayList
     *
     * @return iterator of arrayList's elements
     */
    @TimeComplexity("O(n)")
    @Override
    public Iterator<E> iterator() {
		/* TCJ
		 * Returns every element in the array
		 * Best and worst case is O(n)
		 */
        Iterator<E> myIterator = new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {

                // check if index is not out of bounds and the next item is not null
                if (index < size) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[index++];
            }
        };
        return myIterator;

    }

    /**
     * Shifts all elements in the array to the right by one, then adds element e at
     * index i
     *
     * @param e The element to be stored at index 0
     * @throws IndexOutOfBoundsException never throws, but I wasn't sure if I could
     *                                   remove throws statement
     */
    @TimeComplexity("O(n)")
    public void addFirst(E e) throws IndexOutOfBoundsException {
		/* TCJ
		 * Shifts all elements over, this results in an O(n)
		 * If array is full and one is added, a new array is made and 
		 * all values are copied over
		 * 
		 * Worst case is 2n or O(n)
		 * 
		 */
        if (capacity == size) {

            // Copies array to an array twice the capacity
            E[] newArray = (E[]) new Object[capacity * 2];

            for (int j = 0; j < capacity; j++) {
                newArray[j] = array[j];
            }
            array = newArray;
            capacity = capacity * 2;
        }

        // shifts all elements greater than index i to the right 1
        for (int k = size - 1; k >= 0; k--) {
            array[k + 1] = array[k];
        }

        array[0] = e;
        size++;

    }

    /**
     * Adds element e to index size (end of the array)
     *
     * @param e element to be stored at the end of the array
     * @throws IndexOutOfBoundsException never throws, but I wasn't sure if I could
     *                                   remove throws statement
     */
    @TimeComplexity("O(n)")
    public void addLast(E e) throws IndexOutOfBoundsException {
		/* TCJ
		 * 
		 * Adding a value to the end of the array is O(1)
		 * If array is full and one is added, a new array is made and 
		 * all values are copied over
		 * 
		 * Worst case is O(n)
		 * 
		 */
        if (capacity == size) {

            // Copies array to an array twice the capacity
            E[] newArray = (E[]) new Object[capacity * 2];

            for (int j = 0; j < capacity; j++) {
                newArray[j] = array[j];
            }
            array = newArray;
            capacity = capacity * 2;
        }

        array[size] = e;
        size++;
    }

    /**
     * Returns element at index 0, then shifts all elements to the left by one
     *
     * @return e element at index 0 that is being returned
     * @throws IndexOutOfBoundsException if there are no elements in the array
     */
    @TimeComplexity("O(n)")
    public E removeFirst() throws IndexOutOfBoundsException {
		/* TCJ
		 * Shifts all elements over after the first element is removed. 
		 * Worst case is O(n)
		 * 
		 */

        if (size == 0) {
            throw new IndexOutOfBoundsException();
        } else {

            E e = array[0];

            for (int j = 1; j <= size - 1; j++) {
                array[j - 1] = array[j];
            }

            size--;
            return e;
        }
    }

    /**
     * Returns the last element in the arrayList, then removes it.
     *
     * @return e the last element in the array
     * @throws IndexOutOfBoundsException if the array is empty.
     */
    @TimeComplexity("O(1)")
    public E removeLast() throws IndexOutOfBoundsException {
		/* TCJ
		 * Returns the value at the end of the array O(1)
		 * and sets the value to null O(1)
		 * 
		 * Worst case is O(1)
		 */
        if (size == 0) {

            throw new IndexOutOfBoundsException();

        } else {

            E TEMP = array[size - 1];
            array[size - 1] = null;

            size--;

            return TEMP;

        }
    }

}
