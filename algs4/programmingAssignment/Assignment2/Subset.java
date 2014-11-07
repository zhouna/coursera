import java.util.Iterator;

/**
 * a client program Subset.java that takes a command-line integer k;
 * read in a sequence of N strings from standard input using StdIn.readString();
 * and prints out exactly k of them, uniformly at random.
 */
public class Subset {
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("you lose the command-line integer k!");
            return;
        }
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            queue.enqueue(input);
        }
        
        int k = Integer.parseInt(args[0]);
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
        
        