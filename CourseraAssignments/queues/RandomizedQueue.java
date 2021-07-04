import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] array;
    private int head = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object[1];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < head; i++) {
            copy[i] = array[i];
        }
        array = copy;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (head == array.length) resize(2 * array.length);
        array[head++] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (head <= (int) array.length/4) resize((int) array.length/2);
        int swapidx = StdRandom.uniform(head);
        Item toReturn = array[swapidx];
        array[swapidx] = array[--head];
        size--;
        return toReturn;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return array[StdRandom.uniform(head)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] iteratorArray;
        private int iteratorSize;

        public RandomizedQueueIterator() {
            iteratorArray = (Item[]) new Object[size];
            StdRandom.shuffle(array);
            for (int i = 0; i < head; i++) {
                iteratorArray[i] = array[i];
            }
            iteratorSize = size;
        }

        public boolean hasNext() {
            return iteratorSize != 0;
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return iteratorArray[--iteratorSize];
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // test basic functionality
        StdOut.println("Creating RandomizedQueue, checking isEmpty(), checking size() ...");
        RandomizedQueue<String> myRandomizedQueue = new RandomizedQueue<String>();
        StdOut.println(myRandomizedQueue.isEmpty());
        StdOut.println(myRandomizedQueue.size());
        StdOut.println("Four calls to enqueue() ...");
        myRandomizedQueue.enqueue("Aligator");
        myRandomizedQueue.enqueue("Bat");
        myRandomizedQueue.enqueue("Cuttlefish");
        myRandomizedQueue.enqueue("Dingo");
        StdOut.println("Checking size() ...");
        StdOut.println(myRandomizedQueue.size());
        StdOut.println("Creating an Iterator and checking Iterator API; hasNext() and four calls to next() ...");
        Iterator<String> myIterator = myRandomizedQueue.iterator();
        StdOut.println(myIterator.hasNext());
        StdOut.println(myIterator.next());
        StdOut.println(myIterator.next());
        StdOut.println(myIterator.next());
        StdOut.println(myIterator.next());
        StdOut.println("one call to  hasNext() ...");
        StdOut.println(myIterator.hasNext());
        StdOut.println("Recompute Iterator, four more calls to next() to check independence ...");
        myIterator = myRandomizedQueue.iterator();
        StdOut.println(myIterator.next());
        StdOut.println(myIterator.next());
        StdOut.println(myIterator.next());
        StdOut.println(myIterator.next());
        StdOut.println("one call to  hasNext() ...");
        StdOut.println(myIterator.hasNext());
        StdOut.println("Testing four calls of sample() ...");
        StdOut.println(myRandomizedQueue.sample());
        StdOut.println(myRandomizedQueue.sample());
        StdOut.println(myRandomizedQueue.sample());
        StdOut.println(myRandomizedQueue.sample());
        StdOut.println("Testing four calls of dequeue() ...");
        StdOut.println(myRandomizedQueue.dequeue());
        StdOut.println(myRandomizedQueue.dequeue());
        StdOut.println(myRandomizedQueue.dequeue());
        StdOut.println(myRandomizedQueue.dequeue());
        StdOut.println("Recompute Iterator, test hasNext() and next() ...");
        myIterator = myRandomizedQueue.iterator();
        StdOut.println(myIterator.hasNext());
        try {
            StdOut.println(myIterator.next());
        }
        catch (NoSuchElementException e) {
            StdOut.println("Caught exception for next().");
        }

        // test exceptions
        try {
            myRandomizedQueue.enqueue(null);
        }
        catch (IllegalArgumentException e) {
            StdOut.println("Caught exception for enqueue().");
        }
        try {
            myRandomizedQueue.dequeue();
        }
        catch (NoSuchElementException e) {
            StdOut.println("Caught exception for dequeue().");
        }
        try {
            myRandomizedQueue.sample();
        }
        catch (NoSuchElementException e) {
            StdOut.println("Caught exception for sample().");
        }

    }

}