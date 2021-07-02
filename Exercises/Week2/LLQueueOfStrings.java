public class LLQueueOfStrings {

    private Node first, last;

    private class Node {
        String item;
        Node next;
    }

    boolean isEmpty() {
        return first == null;
    }

    void enqueue(String item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
        }

    }

    String dequeue() {
        String item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    
}
