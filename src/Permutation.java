import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        // write your code here
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            randomizedQueue.enqueue(s);
        }
        int n = Integer.parseInt(args[0]);

        for (int i = 0; i < n; i++) {
            System.out.println(randomizedQueue.dequeue());
        }

    }
}
