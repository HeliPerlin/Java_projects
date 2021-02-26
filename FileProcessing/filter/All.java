package filter;

import java.io.File;

/**
 * implements the all filter
 */
public class All extends Filter {

    /**
     * constructs a new all filter with the negation indicator only
     * @param isNot the negation indicator
     */
    public All(boolean isNot) {
        super(isNot);
    }

    /**
     * decides whether pathname passes the filter or not
     * @param pathname the path name of the file to filter
     * @return true iff the file passes the filter
     */
    @Override
    public boolean accept(File pathname) {
        return pathname.isFile() & !NotIsOn;
    }
}
