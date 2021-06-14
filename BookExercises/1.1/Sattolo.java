import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//1. Sattolo's algorithm. Write a program Sattolo.java that generates a unifomly distributed cycle of length N using Sattolo's algorithm.

// The Python Implementation from Wikipedia
// from random import randrange

// def sattolo_cycle(items) -> None:
//     """Sattolo's algorithm."""
//     i = len(items)
//     while i > 1:
//         i = i - 1
//         j = randrange(i)  # 0 <= j <= i-1
//         items[j], items[i] = items[i], items[j]

public class Sattolo {

    public static void main(String[] args) {
        int N = StdIn.readInt();
        int[] cycle = new int[N];
        for(int i=0; i<N;i++){
            cycle[i] = i;
        }
        for(int i=1; i<=N;i++){
            int r = (int) (Math.random() * (i-1));
            int swap = cycle[r];
            cycle[r] = cycle[i-1];
            cycle[i-1] = swap;
        }
        StdOut.println(Arrays.toString(cycle));
    }
}