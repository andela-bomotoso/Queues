import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;    // beginning of dequeue
    private Node last;     // end of dequeue
    private int n;               // number of elements on dequeue

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node previous;

        public Node(Item item) {
            this.item = item;
        }
    }

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }                        // construct an empty deque

    public boolean isEmpty() {
        return n == 0;
    }                // is the deque empty?

    public int size() {
        return n;
    }                 // return the number of items on the deque

    public void addFirst(Item item) {
        checkNullity(item);
        Node newFirst = new Node(item);

        if (isEmpty()) {
            first = last = newFirst;
        } else {
            Node oldfirst = first;
            first = newFirst;
            first.next = oldfirst;
        }
        n++;

    }      // add the item to the front

    public void addLast(Item item) {
        checkNullity(item);
        Node newLast = new Node(item);
        if (isEmpty()) {
            first = last = newLast;
        } else {
            Node oldlast = last;
            last = newLast;
            last.previous = oldlast;
        }
        n++;
    }         // add the item to the end

    public Item removeFirst() {
        checkEmpty();
        Node oldFirst = first;
        first = first.next;
        n--;
        return oldFirst.item;
    }            // remove and return the item from the front

    public Item removeLast() {
        checkEmpty();
        Node oldLast = last;
        last = oldLast.previous;
        n--;
        return oldLast.item;
    }          // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new DequeueIterator();
    }      // return an iterator over items in order from front to end

    private void checkNullity(Item item) {
        if (item == null)
            throw new IllegalArgumentException("You can not add a null item");
    }

    private void checkEmpty() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
    }


    private class DequeueIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();

        //Add 3 items to the deque from from
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        //Remove 2 items from the front
        System.out.println(deque.removeFirst()); //should return 3
        System.out.println(deque.removeFirst()); //should return 2

        //Add 2 items to the last
        deque.addLast(4);
        deque.addLast(5);

        //Remove 1 item from last and one from front
        System.out.println(deque.removeLast()); //should return 5
        System.out.println(deque.removeFirst()); // should return 1

        System.out.println(deque.removeLast()); // should return 4

    } // unit testing (optional)


}
