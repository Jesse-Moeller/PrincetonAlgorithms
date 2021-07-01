import edu.princeton.cs.algs4.*;

public class ArrayStack {
    private String[] s;
    private int N = 0;

    public ArrayStack(int capacity)
    { s = new String[capacity]; }

    public boolean isEmpty()
    { return N == 0; }

    public void push(String item)
    { s[N++] = item; }

    public String pop()
    { return s[--N]; }

    public static void main(String[] args) {
        ArrayStack myStack = new ArrayStack(args.length);
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
