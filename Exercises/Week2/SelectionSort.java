import edu.princeton.cs.algs4.StdOut;

public class SelectionSort {
    
    public static void sort(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            StdOut.println(String.format("Starting replacement search for %s...", array[i]));
            int minidx = i;
            for (int j = i+1; j < array.length; j++) {
                if (less(array[j],array[minidx])) {
                    StdOut.println(String.format("New candidate found: replacing candidate %s with %s", array[minidx], array[j]));
                    minidx = j;
                }
            }
            StdOut.println(String.format("Exchanging %s with %s", array[i], array[minidx]));
            exch(array, i, minidx);
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
