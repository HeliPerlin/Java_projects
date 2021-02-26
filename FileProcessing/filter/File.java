package filter;

/**
 * implements the file filter
 */
public class File extends Filter {

    /*
     * the name that the file must have in order to pass the filter
     */
    private final String file;

    /**
     * constructs a new file filter
     * @param file the name that the file must have in order to pass the filter
     * @param NotIsOn the negation indicator
     */
    public File(String file, boolean NotIsOn){
        super(NotIsOn);
        this.file = file;
    }

    /**
     * decides whether pathname passes the filter or not
     * @param pathname the path name of the file to filter
     * @return true iff the file passes the filter
     */
    @Override
    public boolean accept(java.io.File pathname) {
        if (NotIsOn){
            return !(pathname.getName().equals(file)) && pathname.isFile();
        }
        return pathname.getName().equals(file) && pathname.isFile();
    }
}
