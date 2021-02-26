package filesprocessing;

/**
 * implements a typeI exception
 */
public class TypeIIException extends Throwable {

    private String msg;

    /**
     * constructs a TypeI exception that is thrown in cases of bad commands line structure or a missing sub-
     * section, as described in the instructions
     * @param msg the message to print in case the error is thrown
     */
    TypeIIException(String msg){
        super();
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }


}
