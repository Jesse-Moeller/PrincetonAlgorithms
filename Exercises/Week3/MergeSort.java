import edu.princeton.cs.algs4.StdOut;

public class MergeSort {

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static boolean isSorted(Comparable[] array, int low, int high) {
        assert (low < array.length);
        assert (high < array.length);
        assert (low <= high);
        for (int i = low; i < high-1; i++) {
            if (less(array[i+1], array[i])) {
                return false;
            }
        }
        return true;
    }

    public static void sort(Comparable[] array, Comparable[] auxiliary, int low, int high) {
        if (low == high) {
            return;
        }
        int mid = (int) (high + low)/2;
        sort(array, auxiliary, low, mid);
        sort(array, auxiliary, mid+1, high);
        if (less(array[mid], array[mid+1])) {
            return;
        }
        merge(array, auxiliary, low, mid, high);
    }

    private static void merge(Comparable[] array, Comparable[] auxiliary, int low, int mid, int high) {
        assert isSorted(array, low, mid);
        assert isSorted(array, mid+1, high);

        // Copy array into auxiliary
        for (int k = low; k <= high; k++) {
            auxiliary[k] = array[k];
        }
        int i = low;
        int j = mid+1;
        for (int k = low; k <= high; k++) {
            if (i > mid) array[k] = auxiliary[j++];
            else if (j > high) array[k] = auxiliary[i++];
            else if (less(auxiliary[j], auxiliary[i])) array[k] = auxiliary[j++];
            else array[k] = auxiliary[i++];
        }
    }
    public static void main(String[] args) {
        Integer[] exampleArray = {9, 5, 6, 4, 7, 8, 3, 0, 1, 2};
        Integer[] auxiliary = new Integer[10];
        MergeSort.sort(exampleArray, auxiliary, 0, 9);
        for (int i = 0; i < exampleArray.length; i++) {
            StdOut.print(exampleArray[i]);
        }
    }
}
