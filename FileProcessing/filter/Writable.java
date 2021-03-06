package filter;

import filesprocessing.TypeIException;

import java.io.File;

/**
 * implements the writable filter
 */
public class Writable extends Filter {

    /*
    indicates whether the filter should pass files that are executable or not
     */
    private final boolean answer;

    /**
     * constructs a new writable filter with the given arguments
     * @param answer indicates whether the filter should pass files that are writable or not
     * @param NotIsOn the negation indicator
     */
    public Writable(String answer, boolean NotIsOn){
        super(NotIsOn);
        if (answer.equals(YES_ANSWER)){
            this.answer = true;
        }
        else if (answer.equals(NO_ANSWER)){
            this.answer = false;
        }
        else {
            throw new TypeIException();
        }
    }

    /**
     * decides whether pathname passes the filter or not
     * @param pathname the path name of the file to filter
     * @return true iff the file passes the filter
     */
    @Override
    public boolean accept(File pathname) {
        if (NotIsOn) {
            return !(pathname.canWrite() & answer) && pathname.isFile();
        }
        return (pathname.canWrite() & answer) && pathname.isFile();
    }
}
