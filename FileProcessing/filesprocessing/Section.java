package filesprocessing;
import filter.Filter;
import filter.FilterFactory;
import order.Order;
import order.OrderFactory;

/**
 * implements a section of the commands file
 */
public class Section {

    /*
     * the filter description line as given in the commands file
     */
    private String filterLine;

    /*
     * the order description line as given in the commands file
     */
    private String orderLine;

    /*
     * the generated filter object, if valid
     */
    private Filter filter;

    /*
     * the generated order object, if valid
     */
    private Order order;

    /*
     * the line number of the filter line in the current section
     */
    private final int index;

    /*
    the gap between the two lines- used for error messages
     */
    private final static int LINE_GAP = 2;

    /*
    the title of a new order in a section
     */
    private final static String DEFAULT_ORDER = "abs";

    /*
    the title of a new order in a section
     */
    private final static String DEFAULT_FILTER = "all";

    /*
    basic warning message
     */
    private final static String WARNING_MSG = "Warning in line ";


    /**
     * constructs a new section
     * @param index the line number of the filter line in the current section
     */
    public Section(int index){
        this.index = index;
    }

    /**
     * sets the filter line of the section to the given argument
     * @param filterLine the line to set the filterLine to
     */
    public void setFilter(String filterLine) {
            this.filterLine = filterLine;
    }

    /**
     * sets the order line of the section to the given argument
     * @param orderLine the line to set the orderLine to
     */
    public void setOrder(String orderLine) {

        this.orderLine = orderLine;
    }

    /**
     * generates a filter out of a given line by sending it to the factory
     */
    public void generateFilter(){
        try {
            this.filter = FilterFactory.createFilter(filterLine);
        }
        catch (TypeIException e){
            System.err.println(WARNING_MSG + index);
            this.filter = FilterFactory.createFilter(DEFAULT_FILTER);
        }
    }

    /**
     * generates a filter out of a given line by sending it to the factory
     */
    public void generateOrder(){
        try {
            this.order = OrderFactory.createOrder(orderLine);
        }
        catch (TypeIException e){
            int errLine = index + LINE_GAP;
            System.err.println(WARNING_MSG + errLine);
            this.order = OrderFactory.createOrder(DEFAULT_ORDER);
        }
    }

    /**
     * @return the filter object
     */
    public Filter getFilter(){
        return filter;
    }

    /**
     * @return the order object
     */
    public Order getOrder(){
        return order;
    }


}