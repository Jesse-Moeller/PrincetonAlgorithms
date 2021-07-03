import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.*;

public class Deque<Item> implements Iterable<Item> {

    // a double-ended queue has a first and last node
    private Node first = null;
    private Node last = null;

    // keep track of size
    private int size = 0;

    // our implementation will use the node data structure
    private class Node {
        Item item;
        Node previous;
        Node next;
    }

    // construct an empty deque
    // public Deque()
    // don't need a constructor

    // is the deque empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node();
        newNode.item = item;

        if (size == 0) {
            first = newNode;
            last = newNode;
            size++;
        }
        else {
            first.next = newNode;
            newNode.previous = first;
            first = newNode;
            size++;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node();
        newNode.item = item;

        if (size == 0) {
            first = newNode;
            last = newNode;
            size++;
        }
        else {
            last.previous = newNode;
            newNode.next = last;
            last = newNode;
            size++;
        }

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        else {
            size--;
            Item item = first.item;
            first = first.previous;
            return item;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        else {
            size--;
            Item item = last.item;
            last = last.next;
            return item;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { return current != null; }
        public Item next()
        {
            Item item = current.item;
            current = current.previous;
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // test basic functionality
        StdOut.println("Creating Deque, checking isEmpty(), checking size() ...");
        Deque<Integer> myDeque = new Deque<Integer>();
        StdOut.println(myDeque.isEmpty());
        StdOut.println(myDeque.size());
        StdOut.println("testing addFirst() and addLast(), 2 calls to addFirst() and 2 calls to addLast() ...");
        myDeque.addFirst(2);
        myDeque.addFirst(1);
        myDeque.addLast(3);
        myDeque.addLast(4);
        StdOut.println("testing size of Deque ...");
        StdOut.println(myDeque.size());
        StdOut.println("Creating an Iterator and checking Iterator API; hasNext() and one call to next() ...");
        Iterator<Integer> myIterator = myDeque.iterator();
        StdOut.println(myIterator.hasNext());
        StdOut.println(myIterator.next());
        StdOut.println("three calls to next() ...");
        StdOut.println(myIterator.next());
        StdOut.println(myIterator.next());
        StdOut.println(myIterator.next());
        StdOut.println("one call to  hasNext() ...");
        StdOut.println(myIterator.hasNext());
        StdOut.println("Testing remove_() API; one call to removeFirst() three calls to removeLast() ...");
        StdOut.println(myDeque.removeFirst());
        StdOut.println(myDeque.removeLast());
        StdOut.println(myDeque.removeLast());
        StdOut.println(myDeque.removeLast());
        StdOut.println("testing size of Deque ...");
        StdOut.println(myDeque.size());

        // test exceptions
        try {
            myDeque.addFirst(null);
        }
        catch(IllegalArgumentException e) {
            StdOut.println("addFirst exception caught");
        }
        try {
            myDeque.addLast(null);
        }
        catch(IllegalArgumentException e) {
            StdOut.println("addFirst exception caught");
        }
        try {
            myDeque.removeFirst();
        }
        catch(NoSuchElementException e) {
            StdOut.println("removeFirst exception caught");
        }
        try {
            myDeque.removeLast();
        }
        catch(NoSuchElementException e) {
            StdOut.println("removeLast exception caught");
        }
    }

}