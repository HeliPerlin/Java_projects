package filesprocessing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * runs over the commands file. Checks its validity, and then creates a list of the filters and a list of
 * the orders to apply on the
 */
public class Parser {

    /*
    the path of the commands file
     */
    private final String commandsFilePath;

    /*
    the title of a new filter in a section
     */
    private final static String FILTER_TITLE = "FILTER";

    /*
    the title of a new order in a section
     */
    private final static String ORDER_TITLE = "ORDER";

    /*
    the title of a new order in a section
     */
    private final static String DEFAULT_ORDER = "abs";

    /*
    the title of a new order in a section
     */
    private final static String ERR_MSG_SECTION_ORDER = "ERROR: Invalid ORDER section\n";

    /*
    the title of a new order in a section
     */
    private final static String ERR_MSG_SECTION_FILTER = "ERROR: Invalid FILTER section\n";

    /*
    the title of a new order in a section
     */
    private final static String ERR_MSG_NO_SECTION_ORDER = "ERROR: No order description\n";

    /*
    the title of a new order in a section
     */
    private final static String ERR_MSG_NO_SECTION_FILTER = "ERROR: No filter description\n";

    /*
    an array list of all of the sections for the current file
     */
    private static final ArrayList<Section> sections = new ArrayList<Section>();

    /**
     * constructs a new parser
     * @param commandsFilePath the path to the file to parse in the current parser object
     */
    public Parser(String commandsFilePath) {
        // TODO: Somehow open the file
        this.commandsFilePath = commandsFilePath;

    }

    /**
     * parses the commands file. Looks for type II errors (such as illegal structure of the input file),
     * saves each section as is in a new section object, and saves them in the sections arrayList to later
     * be printed (if valid)
     * @throws FileNotFoundException in case the given file was not found
     * @throws TypeIException in case of a type I exception as described in the instructions
     * @throws TypeIIException in case of a type I exception as described in the instructions
     */
    public void parse() throws FileNotFoundException, TypeIException, TypeIIException {
        int i = 1;
        FileReader fileReader = new FileReader(commandsFilePath);
        Scanner scanner = new Scanner(fileReader);
        if (!(scanner.hasNextLine())){ // If scanner is empty, return
            return;
        }
        String line = scanner.nextLine();

        // TODO: Check if hasNextLine()
        while (scanner.hasNextLine()){ // while scanner has more lines, go over them and check structure
            Section section = new Section(i + 1);
            if (!line.equals(FILTER_TITLE)){
                throw new TypeIIException(ERR_MSG_SECTION_FILTER);
            }
            if (!(scanner.hasNextLine())){
                throw new TypeIIException(ERR_MSG_NO_SECTION_FILTER);
            }
            String filterLine = scanner.nextLine();
            i++;

            section.setFilter(filterLine);

            if (!(scanner.hasNextLine())){
                throw new TypeIIException(ERR_MSG_NO_SECTION_ORDER);
            }

            if (!(scanner.nextLine().equals(ORDER_TITLE))){
                throw new TypeIIException(ERR_MSG_SECTION_ORDER);
            }
            i++;

            if (!scanner.hasNextLine()){
                section.setOrder(DEFAULT_ORDER);
                sections.add(section);
                return;
            }

            String orderLine = scanner.nextLine();
            i++;

            if (orderLine.equals(FILTER_TITLE)){
                section.setOrder(DEFAULT_ORDER);
                line = orderLine;
                if (!(scanner.hasNextLine())){
                    sections.add(section);
                    return;
                }
            }
            else {
                section.setOrder(orderLine);
                if (!(scanner.hasNextLine())){
                    sections.add(section);
                    return;
                }
                line = scanner.nextLine();
                i++;
                if (!line.equals(FILTER_TITLE)){
                    throw new TypeIIException(ERR_MSG_SECTION_FILTER);

                }
            }
            sections.add(section);
        }

    }

    /**
     * @return the sections array list
     */
    public ArrayList<Section> getSections(){
        return sections;
    }

    /**
     * empties the sections array list
     */
    public void resetSections(){
        sections.clear();
    }

}

