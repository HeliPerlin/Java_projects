package filter;

import java.io.File;

/**
 * implements the extends filter
 */
public class Prefix extends Filter {

    /*
     * the prefix the file must have to pass the filter
     */
    private final String prefix;

    /**
     * constructs a new suffix filter
     * @param prefix the suffix the file must have to pass the filter
     * @param NotIsOn the negation indicator
     */
    public Prefix(String prefix, boolean NotIsOn){
        super(NotIsOn);
        this.prefix = prefix;
    }

    /**
     * decides whether pathname passes the filter or not
     * @param pathname the path name of the file to filter
     * @return true iff the file passes the filter
     */
    @Override
    public boolean accept(File pathname) {
        if (NotIsOn){
            return !(pathname.getName().endsWith(this.prefix)) && pathname.isFile();
        }
        return pathname.getName().startsWith(this.prefix) && pathname.isFile();
    }
}
