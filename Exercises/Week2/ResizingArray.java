import edu.princeton.cs.algs4.*;

public class ResizingArray {
    private String[] s;
    private int N = 0;

    public ResizingArray()
    { s = new String[1]; }

    public boolean isEmpty()
    { return N == 0; }

    public void push(String item) {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    } 

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    public String pop() {
        if (N <= (int) s.length/4) resize((int) s.length/2);
        return s[--N];
    }

    public static void main(String[] args) {
        ResizingArray myStack = new ResizingArray();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-")) {
                myStack.pop();
            }
            else {
                myStack.push(args[i]);
            }
        }
        while (!myStack.isEmpty()) {
            StdOut.println(myStack.pop());
        }
    }
}