package filter;

import java.io.File;

/**
 * implements the contains filter
 */
public class Contains extends Filter {

    /*
     * the substring that the name of the file must contain to pass the filter
     */
    private final String substring;

    /**
     * constructs a new contains filter with the given arguments
     * @param substring the substring that the name of the file must contain to pass the filter
     * @param NotIsOn the negation indicator
     */
    public Contains(String substring, boolean NotIsOn){
        super(NotIsOn);
        this.substring = substring;
    }

    /**
     * decides whether pathname passes the filter or not
     * @param pathname the path name of the file to filter
     * @return true iff the file passes the filter
     */
    @Override
    public boolean accept(File pathname) {
        if (NotIsOn){
            return !(pathname.getName().contains(substring)) && pathname.isFile();
        }
        return pathname.getName().contains(substring) && pathname.isFile();
    }
}
