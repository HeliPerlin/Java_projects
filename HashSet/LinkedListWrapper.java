import java.util.LinkedList;

/**
 * implements a wrapper class for Java's LinkedList
 */
public class LinkedListWrapper {

    /*
    the likedList object that delegated to the wrapper
     */
    private final LinkedList<String> linkedList;


    /**
     * constructs a new LinkedListWrapper by initializing the instance's linked list
     */
    public LinkedListWrapper() {
        this.linkedList = new LinkedList<String>();
    }

    /**
     * delegated from the linked list add method
     *
     * @param value the value to add to the linked list
     * @return true iff the item was added successfully
     */
    public boolean add(String value) {
        return linkedList.add(value);
    }

    /**
     * delegated from the linked list remove method
     *
     * @param value the value to remove from the list
     */
    public void remove(String value) {
        linkedList.remove(value);
    }

    /**
     * delegated from the linked list contains method
     *
     * @param value the value to look for
     * @return true iff the value was found in the linked list
     */
    public boolean contains(String value) {
        return linkedList.contains(value);
    }

    /**
     * @return the linked list object
     */
    public LinkedList<String> getLinkedList() {
        return linkedList;
    }

}
