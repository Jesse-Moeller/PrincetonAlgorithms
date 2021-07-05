import edu.princeton.cs.algs4.StdOut;

public class InsertionSort {
    
    public static void sort(Comparable[] array) {
        int head = 0;
        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i-1])) {
                int j = i;
                StdOut.println(String.format("Item %s is out of order...", array[j]));
                while (j >= 1 && less(array[j], array[j-1])) {
                    StdOut.println("moving it to the left...");
                    exch(array, j, j-1);
                    j--;
                }
                StdOut.println("Settled.");
            }
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] array, int i, int j) {
        Comparable swap = array[i];
        array[i] = array[j];
        array[j] = swap;
    }

    public static void main(String[] args) {
        sort(args);
        for (String string : args) {
            StdOut.print(string);
        }
    }
}