/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int size = 0;

    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        if (size == queue.length) resize(2 * queue.length);
        queue[size++] = item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException("queue is empty");
        int randomPosition = StdRandom.uniform(size);
        Item returnItem = queue[randomPosition];
        for (int i = 0; i < size; i++) {
            int index = i;
            if (i == randomPosition) {
                continue;
            }
            if (i > randomPosition) {
                index--;
            }
            queue[index] = queue[i];
        }
        queue[--size] = null;
        // size--;
        if (size > 0 && size == queue.length / 4) resize(queue.length/2);
        return returnItem;
    }

    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException("queue is empty");
        int randomPosition = StdRandom.uniform(size);
        return queue[randomPosition];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int count = 0;
        private boolean[] seen;
        public RandomizedQueueIterator() {
            seen = new boolean[size];
        }

        public boolean hasNext() {
            return count < size;
        }

        public Item next() {

            if (count > size - 1) throw new java.util.NoSuchElementException("no more items");
            int nextRandomPosition;

            do {
                nextRandomPosition = StdRandom.uniform(size);
            } while (seen[nextRandomPosition]);
            seen[nextRandomPosition] = true;
            count++;

            return queue[nextRandomPosition];
        }

        public void remove() {
            throw new UnsupportedOperationException("Operation not permitted!");
        }
    }
    public Iterator<Item> iterator() { return new RandomizedQueueIterator(); }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        if (queue.isEmpty()) {
            System.out.println("Queue is empty");
        }
        queue.enqueue(10);
        queue.enqueue(2);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.dequeue();
        queue.enqueue(11);
        queue.enqueue(15);
        queue.dequeue();
         for (Object s: queue) {
            System.out.printf("%d->", s);
        }
        System.out.printf("size: %d\n", queue.size());

    }
}
