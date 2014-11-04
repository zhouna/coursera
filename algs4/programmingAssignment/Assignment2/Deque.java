/**
 * A doulbe-ended queue or deque is a generalization of a stack and 
 * a queue that supports inserting and removing items from either
 * the front or the back of the data structure.
 */
public class Deque<Item> implements Iterable<Item> {
    /**
     * the node of the linked list
     */
    private class Node {
        Item item;
        Node next;
        Node prev;
    }
    Node head, tail;
    int size;
    /**
     * construct an empty deque
     */
    public Deque() {
        head = tail = null;
        size = 0;
    }
    /**
     * is the deque empty?
     */
    public boolean isEmpty() {
        return (size == 0);
    }
    /**
     * insert the item at the front
     * 
     * @throws a NullPointerException if item is null
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node();
        node.item = item;
        node.next = head;
        node.prev = null;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        if (tail == null) {
            tail = head;
        }
        size++;
    }
    /**
     * insert the item at the end
     * 
     * @throws NullPointerException if item is null
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node();
        node.item = item;
        node.next = null;
        if (tail != null) {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;
        if (head == null) {
            head = node;
        }
        size++;
    }
    /**
     * delete and return the item at the front
     * 
     * @throws java.util.NoSuchElementException if deque is empty
     */
    public Item removeFirst() {
        if (head == null) {
            throw new java.util.NoSuchElementException();
        }
        Node first = head;
        head = head.next;
        size--;
        return first.item;
    }
    /**
     * delete and return the item at the end
     * 
     * @throws java.util.NoSuchElementException if deque if empty
     */
    public Item removeLast() {
        if (tail == null) {
            throw new java.util.NoSuchElementException();
        }
        Node node = tail;
        if (tail.prev != null) {
            tail.prev.next = null;
            tail = tail.prev;
        } else {
            tail = head = null;
        }
        size--;
        return node.item;
    }
    /**
     *
     */
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>();
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node node = head;
        
        public boolean hasNext() {
            return (!(node == null));
        }
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Node cur = node;
            node = node.next;
            return cur.item;
        }
        public void remove() {
            throw new UnsupportOperationException();
        }
    }
}