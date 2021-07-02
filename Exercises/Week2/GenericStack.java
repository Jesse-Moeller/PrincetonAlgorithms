import java.util.Iterator;

import edu.princeton.cs.algs4.*;

public class GenericStack<Item> {
    
    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public Iterator<Item> iterator()
    {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext() { return current != null; }
        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        GenericStack<String> myStack = new GenericStack<String>();
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