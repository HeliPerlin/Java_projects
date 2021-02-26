/**
 * implements an open hash set that uses the chaining principle
 */
public class OpenHashSet extends SimpleHashSet {

    /*
     * an array of linkedListWrapper object the holds the data
     */
    private LinkedListWrapper[] data;


    /**
     * default constructor
     */
    public OpenHashSet() {
        this.capacity = INITIAL_CAPACITY; // TODO: CONVERT TO MAGIC NUMBERS
        this.data = new LinkedListWrapper[capacity];
        this.size = 0;
        this.lowerLoadFactor = DEF_LOWER_LOAD_FACTOR;
        this.upperLoadFactor = DEF_UPPER_LOAD_FACTOR;
        this.curLoad = (float) size / capacity;
        for (int i = 0; i < capacity; i++) {
            data[i] = new LinkedListWrapper();
        }
    }

    /**
     * constructs an empty open hashSet by defining its lower and upper load factors
     *
     * @param upperLoadFactor the upper load factor
     * @param lowerLoadFactor the lower load factor
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        this.capacity = INITIAL_CAPACITY; // TODO: MAGIC NUMBER
        this.data = new LinkedListWrapper[capacity];
        this.size = 0;
        this.lowerLoadFactor = lowerLoadFactor;
        this.upperLoadFactor = upperLoadFactor;
        this.curLoad = (float) size / capacity;
        for (int i = 0; i < capacity; i++) {
            data[i] = new LinkedListWrapper();
        }
    }


    /**
     * constructs a new open hashSet by adding items one-by-one
     *
     * @param data the items to add
     */
    public OpenHashSet(String[] data) {
        this.capacity = INITIAL_CAPACITY;
        this.data = new LinkedListWrapper[capacity];
        this.size = 0;
        this.lowerLoadFactor = DEF_LOWER_LOAD_FACTOR;
        this.upperLoadFactor = DEF_UPPER_LOAD_FACTOR;
        this.curLoad = (float) size / capacity;
        for (int j = 0; j < capacity; j++) {
            this.data[j] = new LinkedListWrapper();
        }
        int i = 0;
        for (; i < data.length; i++) {
            add(data[i]);
        }
    }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    // TODO: Check for rehashing: inclusive or exclusive of the load factors
    @Override
    public boolean add(String newValue) {
        // TODO: Consider changing the order of the actions: first increase size, then check curLoad <= lower
        // TODO: upper factor 1? handle
        if (contains(newValue)) {
            return false;
        }
        float loadAfterAddition = (float) (size + 1) / capacity;
        if (loadAfterAddition > upperLoadFactor) {
            rehash(INCREASE);
        }
        int index = hash(newValue);
        data[index].add(newValue);
        this.size++;
        this.curLoad = (float) size / capacity;
        return true;
    }

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        int index = hash(searchVal);
        return data[index].contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        // TODO: Consider changing the order of the actions: first increase size, then check curLoad <= lower
        // TODO: upper factor 1? handle
        if (!contains(toDelete)) {
            return false;
        }
        float loadAfterDeletion = (float) (size - 1) / capacity;
        if (loadAfterDeletion < lowerLoadFactor) {
            rehash(DECREASE);
        }
        int index = hash(toDelete);
        data[index].remove(toDelete);
        this.size--;
        this.curLoad = (float) size / capacity;
        return true;
    }

    /*
     * rearranges the data in case of
     * @param indicator - indicates whether to increase or decrease the size
     */
    private void rehash(int indicator) {
        int originalCapacity = capacity;
        if (indicator == INCREASE) { // increase
            capacity *= RESIZE_FACTOR;
        } else {
            if ((capacity / RESIZE_FACTOR) < 1) {
                return;
            }
            capacity /= RESIZE_FACTOR;
        }
        LinkedListWrapper[] originalData = this.data;
        this.data = new LinkedListWrapper[capacity];
        this.size = 0;
        this.curLoad = (float) size / capacity;
        for (int i = 0; i < capacity; i++) {
            data[i] = new LinkedListWrapper();
        }
        for (int i = 0; i < (originalCapacity); i++) {
            for (String val : originalData[i].getLinkedList()) {
                int index = hash(val);
                data[index].add(val);
                this.size++;
                this.curLoad = (float) size / capacity;
            }
        }
    }

    /*
     * returns the hash value of the given string
     * @param value the value to hash
     * @return the hash value of 'value'
     */
    private int hash(String value) {
        int hashedValue = value.hashCode();
        return (hashedValue) & (capacity - 1);
    }

}
