package order;

import java.io.File;
import java.util.Comparator;

/**
 * implements a comparator that compares by absolute path
 */
public class TypeComparator extends OrderComparator {

    /*
    the abs comparator, used in case of equal files
     */
    private AbsComparator absComparator;

    /**
     * constructs a new size comparator
     */
    public TypeComparator(){
        absComparator = new AbsComparator();
    }

    /**
     * compares to files
     * @param lhs the lhs file
     * @param rhs the rhs file
     * @return 0 if the files are equal, a negative integer if lhs is smaller than rhs, a positive integer
     * otherwise, type-name wise
     */
    @Override
    public int compare(File lhs, File rhs) {
        String lhsTypeName = getType(lhs);
        String rhsTypeName = getType(rhs);

        if (lhsTypeName.compareTo(rhsTypeName) < 0){
            return NEGATIVE_RETURN_VAL;
        }
        if (lhsTypeName.compareTo(rhsTypeName) > 0){
            return POSITIVE_RETURN_VAL;
        }
        return absComparator.compare(lhs, rhs);
    }

    /*
     * extracts the type of the file out of its name
     * @param file the file to find the type of
     * @return the type of the file
     */
    private String getType(File file){
        String name = file.toString();
        int lastDot = name.lastIndexOf('.');
        if (lastDot == -1){
            return "";
        }
        return name.substring(lastDot + 1);
    }
}
