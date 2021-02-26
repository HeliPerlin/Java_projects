import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * implements a performance analyzer which measures time that takes different methods to run on different
 * data structures
 */
public class SimpleSetPerformanceAnalyzer {

    /*
    the number of iterations to do as a warm up before the contains check
     */
    private final static int WARM_UP = 70000;

    /*
    the number of iterations to do as a warm up before the contains check
     */
    private final static int LINKED_LIST_ITERATIONS = 7000;


    /*
    the number to divide by in order to convert a result in nanoTime to milliseconds
     */
    private final static int MILLISECONDS_CONVERSION = 1000000;

    /*
    the number to data structures to tests
     */
    private final static int DATA_STRUCTURES_AMOUNT = 5;

    /*
    a string to test the contains method with
     */
    private final static String CONTAINS_TEST_STR1 = "hi";

    /*
    a string to test the contains method with
     */
    private final static String CONTAINS_TEST_STR2 = "-13170890158";

    /*
    a string to test the contains method with
     */
    private final static String CONTAINS_TEST_STR3 = "23";


    /*
    the data structures to test
     */
    private SimpleSet[] dataStructures;


    /*
     * constructs a new SimpleSet performance analyzer
     */
    private void resetDataStructures() {

        TreeSet<String> treeSet = new TreeSet<String>();
        LinkedList<String> linkedList = new LinkedList<String>();
        HashSet<String> hashSet = new HashSet<String>();

        dataStructures = new SimpleSet[DATA_STRUCTURES_AMOUNT];
        dataStructures[0] = new OpenHashSet();
        dataStructures[1] = new ClosedHashSet();
        dataStructures[2] = new CollectionFacadeSet(treeSet);
        dataStructures[3] = new CollectionFacadeSet(linkedList);
        dataStructures[4] = new CollectionFacadeSet(hashSet);
    }


    /**
     * runs the tests
     *
     * @param args there are no args
     */
    public static void main(String[] args) {
        SimpleSetPerformanceAnalyzer performanceAnalyzer1 = new SimpleSetPerformanceAnalyzer();
        SimpleSetPerformanceAnalyzer performanceAnalyzer2 = new SimpleSetPerformanceAnalyzer();

        String[] values1 = Ex4Utils.file2array("src/data1.txt");
        String[] values2 = Ex4Utils.file2array("src/data2.txt");

        performanceAnalyzer1.resetDataStructures(); // set data structs for analyzer TODO: constructor?
        performanceAnalyzer1.adder(values1); // send the analyzer with values1 to adder

        performanceAnalyzer2.resetDataStructures();
        performanceAnalyzer2.adder(values2);

        performanceAnalyzer1.containsRunner(CONTAINS_TEST_STR1);
        performanceAnalyzer1.containsRunner(CONTAINS_TEST_STR2);
        performanceAnalyzer2.containsRunner(CONTAINS_TEST_STR3);
        performanceAnalyzer2.containsRunner(CONTAINS_TEST_STR1);
    }

    /*
     * runs the 'add' method of each data structure of the given array, and measures the time this action
     * takes (time is measured by milliseconds)
     * @param values the data to test with
     */
    private void adder(String[] values) {
        for (SimpleSet dataStructure : dataStructures) {
            long timeBefore = System.nanoTime();
            for (String value : values) {
                dataStructure.add(value); // adds each value

            }
            // when finished adding all of the values in data1, measure the time
            long difference = System.nanoTime() - timeBefore;
            long timeInMilliseconds = difference / MILLISECONDS_CONVERSION;
            System.out.println("Time took to perform add: " + timeInMilliseconds);
        }
    }

    /*
     * runs the contain method on the given value
     * @param value
     */
    private void containsRunner(String value) {
        for (int i = 0; i < DATA_STRUCTURES_AMOUNT; i++) { // for each data structure:
            long timeBefore = 0;
            long difference = 0;
            long timePerAction = 0;
            // if the data structure is not the linked list
            if (i != 3) {
                // do 70000 warm up rounds
                for (int j = 0; j < WARM_UP; j++) {
                    dataStructures[i].contains(value);
                }
                // start counting
                timeBefore = System.nanoTime();
                for (int j = 0; j < WARM_UP; j++) {
                    dataStructures[i].contains(value);
                }
                difference = System.nanoTime() - timeBefore;
                timePerAction = (difference / WARM_UP);

            } else {
                timeBefore = System.nanoTime();
                for (int j = 0; j < LINKED_LIST_ITERATIONS; j++) {
                    dataStructures[i].contains(value);
                }
                difference = System.nanoTime() - timeBefore;
                timePerAction = (difference / LINKED_LIST_ITERATIONS);

            }
            System.out.println("Time took to perform contains on " + value + ":" + timePerAction);
        }
    }
}
