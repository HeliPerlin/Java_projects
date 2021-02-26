package order;

import java.io.File;
import java.util.Comparator;

/**
 * implements a comparator that compares by absolute path
 */
public class SizeComparator extends OrderComparator{

    /*
    the abs comparator, used in case of equal files
     */
    private AbsComparator absComparator;

    /**
     * constructs a new size comparator
     */
    public SizeComparator(){
        absComparator = new AbsComparator();
    }

    /**
     * compares to files
     * @param lhs the lhs file
     * @param rhs the rhs file
     * @return 0 if the files are equal, a negative integer if lhs is smaller than rhs, a positive integer
     * otherwise, size-wise
     */
    @Override
    public int compare(File lhs, File rhs) {
        if(lhs.length() < rhs.length()){
            return NEGATIVE_RETURN_VAL;
        }
        if (lhs.length() > rhs.length()){
            return POSITIVE_RETURN_VAL;
        }
        return absComparator.compare(lhs, rhs);
    }
}
