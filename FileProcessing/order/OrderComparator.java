package order;

import java.io.File;
import java.util.Comparator;

/**
 * implements the driver class of all comparators
 */
abstract public class OrderComparator implements Comparator<File> {

    /**
     * a negative return value for the comparators
     */
    protected final static int NEGATIVE_RETURN_VAL = -1;

    /**
     * a positive return value for the comparators
     */
    protected final static int POSITIVE_RETURN_VAL = 1;

    /**
     * compares to files
     * @param lhs the lhs file
     * @param rhs the rhs file
     * @return 0 if the files are equal, a negative integer if lhs is smaller than rhs, a positive integer
     * otherwise
     */
    abstract public int compare(File lhs, File rhs);
}