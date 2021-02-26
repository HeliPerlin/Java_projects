package filter;
import filesprocessing.TypeIException;

import java.io.File;

/**
 * implements the greater_than filter
 */
public class GreaterThan extends Filter{

    /*
    the upper boundary of the size of a file that passes the filter
     */
    private final double boundary;

    /**
     * constructs a new greater_then filter
     * @param boundary the upper boundary of the size of a file that passes the filter
     * @param NotIsOn the negation indicator
     * @throws TypeIException in case of illegal boundary
     */
    public GreaterThan(double boundary, boolean NotIsOn) throws TypeIException {
        super(NotIsOn);
        if (boundary < 0){
            throw new TypeIException();
        }
        this.boundary = boundary;
    }

    /**
     * decides whether pathname passes the filter or not
     * @param pathname the path name of the file to filter
     * @return true iff the file passes the filter
     */
    @Override
    public boolean accept(File pathname) {
        // TODO: Might need to convert the numbers
        if (this.NotIsOn){
            return ((double)pathname.length()/CONVERT_TO_K <= this.boundary) && pathname.isFile();
        }
        return ((double)pathname.length()/CONVERT_TO_K > (this.boundary)) && pathname.isFile();

    }
}
