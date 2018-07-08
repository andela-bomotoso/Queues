import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int n;               // number of elements on dequeue

    public RandomizedQueue() {
        n = 0;
        items = (Item[]) new Object[1];

    }               // construct an empty randomized queue

    public boolean isEmpty() {
        return n == 0;
    }           // is the randomized queue empty?

    public int size() {
        return n;
    }            // return the number of items on the randomized queue

    public void enqueue(Item item) {
        checkNullity(item);
        if (n == items.length) resize(2 * items.length);
        items[n++] = item;
    }         // add the item

    public Item dequeue() {
        checkEmpty();
        int random = StdRandom.uniform(n);
        Item randomItem = items[random];
        items[random] = items[--n];
        items[n] = null;
        if (n > 0 && n == items.length / 4) resize(items.length / 2);
        return randomItem;
    }               // remove and return a random item

    public Item sample() {
        checkEmpty();
        int random = StdRandom.uniform(n);
        return items[random];
    }// return a random item (but do not remove it)

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = items[i];
        items = copy;
    }

    private void checkNullity(Item item) {
        if (item == null)
            throw new IllegalArgumentException("You can not add a null item");
    }

    private void checkEmpty() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
    }


    public Iterator<Item> iterator() {
        return new RandomisedIterator();
    }     // return an independent iterator over items in random order


    private class RandomisedIterator implements Iterator<Item> {
        Item[] temp;
        int currentSize = 0;

        public RandomisedIterator() {
            temp = (Item[]) new Object[n];
            for (int i = 0; i < items.length; i++) {
                temp[i] = items[i];
                currentSize++;
            }
        }

        public boolean hasNext() {
            return currentSize < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            int random = StdRandom.uniform(n);
            return items[random - 1];
        }
    }

    public static void main(String[] args) {

        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);

        System.out.println("The size: " + randomizedQueue.size());

        System.out.println("Dequeue:" + randomizedQueue.dequeue());
        System.out.println("Dequeue:" + randomizedQueue.dequeue());
        System.out.println("The size: " + randomizedQueue.size());
        System.out.println("Dequeue:" + randomizedQueue.dequeue());
        System.out.println("The size: " + randomizedQueue.size());
        randomizedQueue.enqueue(4);
        System.out.println("The sample: " + randomizedQueue.sample());


    } // unit testing (optional)
}