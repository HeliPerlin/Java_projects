/**
 * implements a closed hash set
 */
public class ClosedHashSet extends SimpleHashSet {


    /*
    a string wrapper array that holds the data
     */
    private StringWrapper[] data;

    /**
     * default constructor
     */
    public ClosedHashSet() {
        this.capacity = INITIAL_CAPACITY;
        this.data = new StringWrapper[capacity];

        this.size = 0;
        this.lowerLoadFactor = DEF_LOWER_LOAD_FACTOR;
        this.upperLoadFactor = DEF_UPPER_LOAD_FACTOR;
        this.curLoad = (float) size / capacity;

    }

    /**
     * constructs an empty open hashSet by defining its lower and upper load factors
     *
     * @param upperLoadFactor the upper load factor
     * @param lowerLoadFactor the lower load factor
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        this.capacity = INITIAL_CAPACITY;
        this.data = new StringWrapper[capacity];
        this.size = 0;
        this.lowerLoadFactor = lowerLoadFactor;
        this.upperLoadFactor = upperLoadFactor;
        this.curLoad = (float) size / capacity;

    }


    /**
     * constructs a new open hashSet by adding items one-by-one
     *
     * @param data the items to add
     */
    public ClosedHashSet(String[] data) {
        this.capacity = INITIAL_CAPACITY;
        this.data = new StringWrapper[capacity];
        this.size = 0;
        this.lowerLoadFactor = DEF_LOWER_LOAD_FACTOR;
        this.upperLoadFactor = DEF_UPPER_LOAD_FACTOR;
        this.curLoad = (float) size / capacity;

        for (String datum : data) {
            add(datum);
        }
    }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    @Override
    public boolean add(String newValue) {

        if (contains(newValue) || newValue == null) {
            return false;
        }
        float loadAfterAddition = (float) (size + 1) / capacity;
        if (loadAfterAddition > upperLoadFactor) {
            rehash(INCREASE);
        }
        int index = searchForIndex(newValue);
        data[index] = new StringWrapper(newValue);
        size++;
        this.curLoad = (float) size / capacity;
        return true;
    }

    /*
     * looks for an empty cell in the data array that suits the value hashing
     * @param value the value to look for its index
     * @return
     */
    private int searchForIndex(String value) {
        int i = 0;
        while (i < capacity) {
            if (value == null) {
                return -1;
            }
            int index = (value.hashCode() + ((i + i * i) / 2)) & (capacity - 1);
            if (data[index] == null || data[index].getIsDeleted()) {
                return (index);
            }
            i++;
        }
        return -1;
    }

    /*
     * searches for the index of a value that needs to be deleted
     * @param value the value to look for
     * @return the index of the value if it exists in the set, -1 otherwise
     */
    private int searchForValToDelete(String value) {
        int i = 0;
        while (i < capacity) {
            int index = (value.hashCode() + ((i + i * i) / 2)) & (capacity - 1);
            if ((data[index].getString()).equals(value)) {
                return index;
            }
            if (data[index] == null) {
                return -1;
            }
            i++;
        }
        return -1;
    }

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        int i = 0;
        while (i <= capacity) {
            if (searchVal == null) {
                return false;
            }
            int index = (searchVal.hashCode() + ((i + i * i) / 2)) & (capacity - 1);
            if (data[index] == null) {
                return false;
            }
            if ((data[index].getString()).equals(searchVal) && !data[index].getIsDeleted()) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        if (!contains(toDelete)) {
            return false;
        }
        float loadAfterDeletion = (float) (size - 1) / capacity;
        if (loadAfterDeletion < lowerLoadFactor) {
            rehash(DECREASE);
        }
        int index = searchForValToDelete(toDelete);
        if (index == -1) {
            return false;
        } else {
            data[index].setIsDeleted();
            ;
        }
        size--;
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
        StringWrapper[] originalArr = data;
        this.data = new StringWrapper[capacity];
        size = 0;
        this.curLoad = (float) size / capacity;

        for (int i = 0; i < originalCapacity; i++) {
            if (originalArr[i] != null && !originalArr[i].getIsDeleted()) {
                int index = searchForIndex(originalArr[i].getString());
                data[index] = originalArr[i];
                size++;
                this.curLoad = (float) size / capacity;
            }

        }
    }
}
