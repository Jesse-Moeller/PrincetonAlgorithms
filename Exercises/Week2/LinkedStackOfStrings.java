import edu.princeton.cs.algs4.*;

public class LinkedStackOfStrings {
    
    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(String item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }

    public static void main(String[] args) {
        LinkedStackOfStrings myStack = new LinkedStackOfStrings();
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