/**
 * implements the collection facade set: wraps java collections that implement SimpleSet
 */
public class CollectionFacadeSet implements SimpleSet {

    /*
     * the collection that is wrapped by this object
     */
    private final java.util.Collection<java.lang.String> collection;

    /**
     * constructs a new Collection facade set with the given collection argument
     *
     * @param collection
     */
    public CollectionFacadeSet(java.util.Collection<java.lang.String> collection) {

        this.collection = collection;
    }


    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    @Override
    public boolean add(String newValue) {
        if (!contains(newValue)) {
            return collection.add(newValue);
        }
        return false;
    }

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        return collection.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        return collection.remove(toDelete);
    }

    /**
     * @return The number of elements currently in the set
     */
    @Override
    public int size() {
        return collection.size();
    }
}
