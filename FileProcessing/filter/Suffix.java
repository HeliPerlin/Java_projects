package filter;

import java.io.File;

/**
 * implements the suffix filter
 */
public class Suffix extends Filter {

    /*
     * the suffix the file must have to pass the filter
     */
    private final String suffix;

    /**
     * constructs a new suffix filter
     * @param suffix  the suffix the file must have to pass the filter
     * @param NotIsOn the negation indicator
     */
    public Suffix(String suffix, boolean NotIsOn){
        super(NotIsOn);
        this.suffix = suffix;
    }

    /**
     * decides whether pathname passes the filter or not
     * @param pathname the path name of the file to filter
     * @return true iff the file passes the filter
     */
    @Override
    public boolean accept(File pathname) {
        if (NotIsOn){
            return !(pathname.getName().endsWith(this.suffix)) && pathname.isFile();
        }
        return pathname.getName().endsWith(this.suffix) && pathname.isFile();
    }
}
