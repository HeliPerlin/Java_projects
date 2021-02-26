package filter;

import filesprocessing.TypeIException;

/**
 * implements the filter factory
 */
public class FilterFactory {

    /*
     * indicates a positive filter for the writable/executable/hidden filters
     */
    private final static String NEGATION= "NOT";

    /*
    one of the valid lengths of a line
    */
    private final static int LEGAL_LENGTH_2 = 2;

    /*
    one of the valid lengths of a line
    */
    private final static int LEGAL_LENGTH_3 = 3;

    /*
    one of the valid lengths of a line
    */
    private final static int LEGAL_LENGTH_4 = 4;

    /*
    one of the valid lengths of a line
    */
    private final static int LEGAL_LENGTH_1 = 1;

    /*
    the index of the filter parameter in the description line
    */
    private final static int FILTER_PARAMETER_INDEX = 0;

    /*
    the index of the value
    */
    private final static int VAL_INDEX = 1;

    /*
    the index of the negation indicator
    */
    private final static int NOT_INDEX_2 = 2;

    /*
    the index of the negation indicator
    */
    private final static int NOT_INDEX_3 = 3;

    /*
    the delimiter by which to split the string
     */
    private final static String DELIMITER = "#";


    /**
     * given a line describing a filter from the commands file, calls the matching constructor of the
     * filter object that is named
     * @param filterLine the filter description as given in the commands file
     * @return the filter constructed
     * @throws TypeIException in case the format of the description line or the given values are illegal
     */
    static public Filter createFilter(String filterLine) throws TypeIException{
        String[] values = filterLine.split(DELIMITER);

        if (values[0].equals("greater_than")){
            boolean NotIsOn = checkForNot(values);
            double num = Double.parseDouble(values[VAL_INDEX]);
            return new GreaterThan(num, NotIsOn);
        }

        if (values[0].equals("smaller_than")){
            boolean NotIsOn = checkForNot(values);
            double num = Double.parseDouble(values[VAL_INDEX]);
            return new SmallerThan(num, NotIsOn);
        }

        if (values[0].equals("prefix")){
            boolean NotIsOn = checkForNot(values);
            if (values.length == LEGAL_LENGTH_1){
                return new Contains("", NotIsOn);
            }
            return new Prefix(values[VAL_INDEX], NotIsOn);
        }

        if (values[0].equals("suffix")){
            boolean NotIsOn = checkForNot(values);
            if (values.length == LEGAL_LENGTH_1){
                return new Contains("", NotIsOn);
            }
            return new Suffix(values[VAL_INDEX], NotIsOn);
        }

        if (values[0].equals("contains")){
            boolean NotIsOn = checkForNot(values);
            if (values.length == LEGAL_LENGTH_1){
                return new Contains("", NotIsOn);
            }
            return new Contains(values[VAL_INDEX], NotIsOn);
        }
        if (values[0].equals("file")){
            boolean NotIsOn = checkForNot(values);
            if (values.length == LEGAL_LENGTH_1){
                return new Contains("", NotIsOn);
            }
            return new File(values[VAL_INDEX], NotIsOn);
        }

        if (values[0].equals("writable")){
            boolean NotIsOn = checkForNot(values);
            return new Writable(values[VAL_INDEX], NotIsOn);
        }

        if (values[0].equals("executable")){
            boolean NotIsOn = checkForNot(values);
            return new Executable(values[VAL_INDEX], NotIsOn);
        }

        if (values[0].equals("hidden")){
            boolean NotIsOn = checkForNot(values);
            return new Hidden(values[VAL_INDEX], NotIsOn);
        }


        if (values[0].equals("all")) {
            boolean NotIsOn = false;
            if (values.length > LEGAL_LENGTH_1) {
                if (values[VAL_INDEX].equals(NEGATION)) {
                    NotIsOn = true;
                }
                else {
                    throw new TypeIException();
                }
            }
            return new All(NotIsOn);
        }

        if (values[0].equals("between")){
            boolean NotIsOn = false;
            if (values.length == LEGAL_LENGTH_4){
                if (values[NOT_INDEX_3].equals(NEGATION)){
                    NotIsOn = true;
                }
                else {
                    throw new TypeIException();
                }
            }
            double num1 = Double.parseDouble(values[VAL_INDEX]);
            double num2 = Double.parseDouble(values[NOT_INDEX_2]);
            return new Between(num1, num2, NotIsOn);
        }
        else {
            throw new TypeIException();
        }
    }

    /*
     * helps the create method to check if a negation indicator is given in the line
     * @param values the elements of the given description line
     * @return true iff a negation indicator exists in the line
     * @throws TypeIException
     */
    private static boolean checkForNot(String[] values) throws TypeIException{
        if (values.length == LEGAL_LENGTH_2){
            return false;
        }
        if (values.length == LEGAL_LENGTH_3){
            if (values[2].equals(NEGATION)){
                return true;
            }
            else {
                throw new TypeIException();
            }
        }
        return false;
    }
}
