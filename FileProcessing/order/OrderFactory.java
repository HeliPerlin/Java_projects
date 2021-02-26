package order;

import filesprocessing.TypeIException;

/**
 * implements the order factory
 */
public class OrderFactory {

    /*
    the absolute order name
     */
    private final static String ABS_ORDER = "abs";

    /*
    the absolute order name
     */
    private final static String TYPE_ORDER = "type";

    /*
    the absolute order name
     */
    private final static String SIZE_ORDER = "size";

    /*
    indicated that reversing the order is needed
     */
    private final static String REVERSE = "REVERSE";

    /*
    the delimiter by which to split the string
     */
    private final static String DELIMITER = "#";

    /*
    one of the valid lengths of a line
    */
    private final static int LEGAL_LENGTH = 2;





    /**
     * given a line describing an order from the commands file, calls the matching constructor of the
     * order object that is named
     * @param orderLine the filter description as given in the commands file
     * @return the order constructed
     * @throws TypeIException in case the format of the description line is illegal
     */
    static public Order createOrder(String orderLine) throws TypeIException {
        String[] values = orderLine.split(DELIMITER);
        if (values.length > LEGAL_LENGTH){
            throw new TypeIException();
        }
        boolean isReverse = false;
        if (values.length == LEGAL_LENGTH){
            if (values[1].equals(REVERSE)){
                isReverse = true;
            }
            else {
                throw new TypeIException();
            }
        }
        switch (values[0]){
            case (ABS_ORDER):
                return new Order(ABS_ORDER, isReverse);
            case (TYPE_ORDER):
                return new Order(TYPE_ORDER, isReverse);
            case (SIZE_ORDER):
                return new Order(SIZE_ORDER, isReverse);
        }
        throw new TypeIException();
    }
}
