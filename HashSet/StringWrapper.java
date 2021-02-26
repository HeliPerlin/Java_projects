/**
 * implements a string wrapper that holds a string and indicates if it was deleted from a data structure
 */
public class StringWrapper {

    /*
    the string that is represented by the wrapper
     */
    private final String string;

    /*
    indicated whether the string was deleted from a ds or not
     */
    private boolean isDeleted;

    /**
     * constructs a new string wrapper object
     *
     * @param string the string to wrap
     */
    public StringWrapper(String string) {
        this.string = string;
        isDeleted = false;
    }

    /**
     * @return the string
     */
    public String getString() {
        return this.string;
    }

    /**
     * @return true if the string is deleted, false otherwise
     */
    public boolean getIsDeleted() {
        return this.isDeleted;
    }

    /**
     * sets the string deletion to its opposite
     */
    public void setIsDeleted() {
        isDeleted = !isDeleted;
    }


}
