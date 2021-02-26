package filesprocessing;

import java.io.File;
import java.io.FileNotFoundException;


/**
 * implements the Directory Processor who runs the main function to filter and sort the files
 */
public class DirectoryProcessor {

    /*
    an error message in case of the number of the given arguments is not two
     */
    private static final String ERR_MSG_ARGS = "ERROR: Number of arguments is illegal\n";

    /*
    an error message in case of the number of the given arguments is not two
     */
    private static final String ERR_MSG_FILE_NOT_FOUND = "ERROR: File not found\n";

    /*
    the requested number of arguments
     */
    private final static int NUM_OF_ARGS = 2;

    /*
    the index of the source directory in the input arguments
     */
    private final static int DIR_INDEX = 0;

    /*
    the index of the commands file in the input arguments
     */
    private final static int COMMANDS_FILE_INDEX = 1;

    /**
     * runs the program.
     * @param args receives an input directory and a commands file containing sections of filter and order,
     * and for each section prints the files from the input directory that pass the filter in the specified
     * order
     */
    public static void main(String[] args) {
        try {
            if (args.length != NUM_OF_ARGS) {
                throw new TypeIIException(ERR_MSG_ARGS);
                // TODO: Check that 0 is a directory and 1 is commandsFile
            }
            File[] ordered;
            File sourceDirectory = new File(args[DIR_INDEX]); // creates a file object with the given src directory
            Parser parser = new Parser(args[COMMANDS_FILE_INDEX]);
            parser.parse();// creates a Parser object with the commandsFile path
            for (Section section: parser.getSections()){
                section.generateFilter();
                section.generateOrder();
                File[] filtered = sourceDirectory.listFiles(section.getFilter());
                if (filtered == null){
                    continue;
                }
                ordered = section.getOrder().sortFiles(filtered);
                for (File file: ordered){
                    System.out.println(file.getName());
                }
            }
            parser.resetSections();

        } catch (TypeIIException e) {
            System.err.println(e.getMsg());
        }
        catch (FileNotFoundException e){
            System.err.println(ERR_MSG_FILE_NOT_FOUND);
        }
    }

}