import java.util.Iterator;

/**
 * RandomizedQueue is similar to a stack or queue, except that the item 
 * removed is chosen uniformly at random from items in the data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] pointer; // the array list of the item pointers
    private int N = 0; // the count of the items in the randomizedQueue
    /**
     * construct
     */
    public RandomizedQueue() {
        pointer = (Item[]) new Object[1];
    }
    /**
     * is the queue empty?
     */
    public boolean isEmpty() {
        return N == 0;
    }
    /**
     * return the number of items on the queue
     */
    public int size() {
        return N;
    }
    /**
     * add the item
     * 
     * @throws a NullPointerException if item is null
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == pointer.length) {
            resize(2*pointer.length);
        }
        pointer[N++] = item;
    }
    /**
     * resize the capacity of the array list
     * 
     * @param capacity new size of the array list
     */
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = pointer[i];
        }
        pointer = copy;
    }
    /**
     * delete and return a random item
     * 
     * @throws a java.util.NoSuchElementException if the queue is empty
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int index = StdRandom.uniform(N); // a random num in the range [0, N)
        Item item = pointer[index];
        pointer[index] = pointer[--N];
        pointer[N] = null; // avoid loitering
        if (N > 0 && N == pointer.length / 4) {
            resize(pointer.length/2);
        }
        return item;
    }
    /**
     * return (but not delete) a random item
     * 
     * @throws a java.util.NoSuchElementException if the queue is empty
     */
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int index = StdRandom.uniform(N); // a random num in the range [0, N)
        return pointer[index];
    }
    /**
     * return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] randomIndex = null;
        private int currentIndex = 0;
        
        public RandomizedQueueIterator() {
            randomIndex = new int[N];
            for (int i = 0; i < N; i++) {
                randomIndex[i] = i;
            }
            StdRandom.shuffle(randomIndex);
        }
        
        public boolean hasNext() {
            return currentIndex < N;
        }
        
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return pointer[randomIndex[currentIndex++]];
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    public static void main(String[] args) {
        
    }
}
    