package order;

import java.io.File;
import java.util.Comparator;

/**
 * implements a comparator that compares by absolute path
 */
public class AbsComparator extends OrderComparator{

    /**
     * compares to files
     * @param lhs the lhs file
     * @param rhs the rhs file
     * @return 0 if the files are equal, a negative integer if lhs is smaller than rhs, a positive integer
     * otherwise, name-wise
     */
    @Override
    public int compare(File lhs, File rhs) {
        return lhs.getAbsolutePath().compareTo(rhs.getAbsolutePath());
    }
}
