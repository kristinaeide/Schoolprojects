import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

public class CustomDeque {
    private Deque<Integer> deque;

    public CustomDeque() {
        this.deque = new LinkedList<>();
    }

    public void enqueueFront(int data) {
        deque.addFirst(data);
    }

    public void enqueueRear(int data) {
        deque.addLast(data);
    }

    public int dequeueFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        return deque.removeFirst();
    }

    public int dequeueRear() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        return deque.removeLast();
    }

    public Iterator<Integer> iterator() {
        return new DequeIterator();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public int size() {
        return deque.size();
    }

    public void displayDeque() {
        Iterator<Integer> current = iterator();
        while (current.hasNext()) {
            System.out.print(current.next() + " ");
        }
        System.out.println();
    }

    private class DequeIterator implements Iterator<Integer> {
        private Iterator<Integer> iterator = deque.iterator();

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the deque.");
            }
            return iterator.next();
        }
    }

    public static void main(String[] args) {
        CustomDeque customDeque = new CustomDeque();
        Random random = new Random();

        int[] testNumbers = new int[10];

        System.out.println("Ten random integers for testing:");
        for (int i = 0; i < testNumbers.length; i++) {
            testNumbers[i] = random.nextInt(100);
            System.out.print(testNumbers[i] + " ");
        }

        System.out.println("\n");

        // Add some numbers to the front and some to the rear
        for (int i = 0; i < testNumbers.length; i++) {
            if (i % 2 == 0) {
                customDeque.enqueueFront(testNumbers[i]);
            } else {
                customDeque.enqueueRear(testNumbers[i]);
            }
        }

        System.out.println("Deque after inserting elements:");
        customDeque.displayDeque();

        System.out.println("Removed from front: " + customDeque.dequeueFront());
        System.out.println("Removed from rear: " + customDeque.dequeueRear());

        System.out.println("\nDeque after removing one item from each end:");
        customDeque.displayDeque();

        System.out.println("Traversing the deque with the custom iterator:");
        Iterator<Integer> iterator = customDeque.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("\nCurrent deque size: " + customDeque.size());
    }
}