package filter;
import java.io.File;
import java.io.FileFilter;

/**
 * implements the driver class of all filters
 */
abstract public class Filter implements FileFilter {

    /**
    the co-efficient to convert by the size of the file
     */
    protected final static int CONVERT_TO_K = 1024;

    /**
     * indicates a positive filter for the writable/executable/hidden filters
     */
    protected final static String YES_ANSWER = "YES";

    /**
     * indicates a positive filter for the writable/executable/hidden filters
     */
    protected final static String NO_ANSWER = "NO";

    /**
    the negation indicator
     */
    protected final boolean NotIsOn;

    /**
     * constructs a new filter, called from extending classes only
     * @param isNot the negation indicator
     */
    public Filter(boolean isNot) {
        this.NotIsOn = isNot;
    }

    /**
     * decides whether pathname passes the filter or not
     * @param pathname the path name of the file to filter
     * @return true iff the file passes the filter
     */
    abstract public boolean accept(File pathname);
}
