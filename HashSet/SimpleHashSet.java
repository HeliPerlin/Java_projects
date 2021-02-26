
/**
 * implements the SimpleHashSet class. This is the driver class of OpenHashSet and ClosedHashSet, which
 * both represent the data structure of a HashSet in its two versions.
 */
abstract public class SimpleHashSet implements SimpleSet {

    /**
     * the number of elements currently in the set
     */
    protected int size;

    /**
     * the capacity of the HashSet- its number of buckets
     */
    protected int capacity;

    // mine:

    /**
     * the upper load factor
     */
    protected float upperLoadFactor;

    /**
     * the lower load factor
     */
    protected float lowerLoadFactor;

    /**
     * the current load factor of the hash set
     */
    protected float curLoad;

    /**
     * indicates to increase the size of the set in the rehashing method
     */
    protected final static int INCREASE = 1;

    /**
     * indicates to decrease the size of the set in the rehashing method
     */
    protected final static int DECREASE = -1;

    /**
     * the resizing factor of the table
     */
    protected final static int RESIZE_FACTOR = 2;

    /**
     * the initial capacity of the hash set
     */
    protected final static int INITIAL_CAPACITY = 16;

    /**
     * the default lower load factor
     */
    protected final static float DEF_LOWER_LOAD_FACTOR = (float) 0.25;

    /**
     * the default upper load factor
     */
    protected final static float DEF_UPPER_LOAD_FACTOR = (float) 0.75;


    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    @Override
    abstract public boolean add(String newValue);

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    abstract public boolean contains(String searchVal);

    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    abstract public boolean delete(String toDelete);

    /**
     * @return The number of elements currently in the set
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @return The capacity of the data set
     */
    public int capacity() {
        return this.capacity;
    }


}
