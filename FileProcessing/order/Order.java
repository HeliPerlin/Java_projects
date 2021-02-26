package order;

import java.io.File;

/**
 * implements the order class
 */
public class Order{

    /*
    the parameter to sort by
     */
    private final String sortingParameter;

    /*
    reverse indicator
     */
    private final boolean isReverse;

    /*
    the kind of the comparator
     */
    private OrderComparator orderComparator;

    /*
    the index that helps defining the separating of the array into two arrays
     */
    private final static int ARRAY_SEPARATOR_IND = 1;

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

    /**
     * constructs a new order object with a comparator according to the given parameter
     * @param sortingParameter the parameter to sort by
     * @param isReverse the reverser indicator
     */
    public Order(String sortingParameter, boolean isReverse){
        this.sortingParameter = sortingParameter;
        this.isReverse = isReverse;
        if (sortingParameter.equals(ABS_ORDER)){
            orderComparator = new AbsComparator();
        }
        if (sortingParameter.equals(SIZE_ORDER)){
            orderComparator = new SizeComparator();

        }
        if (sortingParameter.equals(TYPE_ORDER)){
            orderComparator = new TypeComparator();
        }
    }

    /**
     * the main sorting function
     * @param files an array of files to sort
     * @return a sorted array of the given files
     */
    public File[] sortFiles(File[] files){
        File[] sortedFiles = null;
        sortedFiles = quickSort(files, 0, files.length - ARRAY_SEPARATOR_IND);
        return sortedFiles;
    }

    /*
     * applies the quick sort algorithm on the given files array
     * @param files the array to sort
     * @param start the beginning of the array
     * @param end the end of the array
     * @return a sorted files array
     */
    private File[] quickSort(File[] files, int start, int end){
        if (start < end){
            int index = partition(files, start,end);
            quickSort(files, start, index - ARRAY_SEPARATOR_IND);
            quickSort(files, index + ARRAY_SEPARATOR_IND, end);
        }
        return files;
    }

    /*
     * applies partition around a pivot on the files
     * @param files the files to sort
     * @param start the beginning of the array
     * @param end the end of the array
     * @return a partitioned (around a pivot) files array
     */
    private int partition(File[] files, int start, int end){

        File pivotFile = files[end];
        int compInd = start ;
        for (int j = start; j < end; j ++){
            int comparison_result = orderComparator.compare(files[j],pivotFile);
            if (isReverse){
                comparison_result = - comparison_result;
            }
            if (comparison_result <= 0){
                File temp = files[compInd];
                files[compInd] = files[j];
                files[j] = temp;
                compInd ++;
            }
        }
        File temp = files[compInd];
        files[compInd] = files[end];
        files[end] = temp;
        return compInd;
    }
}
