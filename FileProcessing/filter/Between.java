package filter;

import filesprocessing.TypeIException;

import java.io.File;

/**
 * implements the between filter
 */
public class Between extends Filter {

    /*
    the lower boundary of the files' size
     */
    private final double lowerBoundary;

    /*
    the upper boundary of the files' size
     */
    private final double upperBoundary;

    /**
     * constructs a new between filter with the given parameters
     * @param lowerBoundary the lower boundary of the files size
     * @param upperBoundary the upper boundary of the files size
     * @param isNot the negation indicator
     * @throws TypeIException thrown in case of illegal boundary
     */
    public Between(double lowerBoundary, double upperBoundary, boolean isNot) throws TypeIException {
        super(isNot);
        if (lowerBoundary < 0 || upperBoundary < 0 || upperBoundary < lowerBoundary){
            throw new TypeIException();
        }
        this.lowerBoundary = lowerBoundary;
        this.upperBoundary = upperBoundary ;
    }

    /**
     * decides whether pathname passes the filter or not
     * @param pathname the path name of the file to filter
     * @return true iff the file passes the filter
     */
    @Override
    public boolean accept(File pathname) {
        if (this.NotIsOn) {
            return ((double)pathname.length()/CONVERT_TO_K < this.lowerBoundary &&
                    (double)pathname.length()/CONVERT_TO_K > this.upperBoundary) && pathname.isFile();
        }
        return (((double)pathname.length()/CONVERT_TO_K >= this.lowerBoundary) &&
                ((double)pathname.length()/CONVERT_TO_K <=  this.upperBoundary)) && pathname.isFile();
    }

}