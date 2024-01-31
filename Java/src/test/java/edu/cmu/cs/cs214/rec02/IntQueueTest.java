package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: 
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

    private static final Integer NULL = null;
    private IntQueue mQueue;
    private List<Integer> testList;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        // mQueue = new LinkedIntQueue();
       mQueue = new ArrayIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
    }

    @Test
    public void testIsEmpty() {
        // This is an example unit test
        assertTrue(mQueue.isEmpty());
        assertTrue(mQueue.size() == 0);

        mQueue.enqueue(2);
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        // TODO: write your own unit test
        mQueue.enqueue(2);
        assertFalse(mQueue.isEmpty());
        assertTrue(mQueue.size() == 1);
    }

    @Test
    public void testPeekEmptyQueue() {
        // TODO: write your own unit test
        assertEquals(mQueue.peek(),NULL);
        assertTrue(mQueue.size() == 0);
    }

    @Test
    public void testPeekNoEmptyQueue() {
        // TODO: write your own unit test
        mQueue.enqueue(2);
        assertTrue(mQueue.peek() == 2);
        assertTrue(mQueue.size() == 1);

        mQueue.clear();
        assertEquals(mQueue.peek(), NULL);
    }

    @Test
    public void testEnqueue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
        mQueue.clear();
        mQueue.enqueue(2);
        assertTrue(mQueue.peek() == 2);
        assertTrue(mQueue.size() == 1);
        mQueue.enqueue(3);
        assertTrue(mQueue.peek() == 2);
        assertTrue(mQueue.size() == 2);
        mQueue.dequeue();
        assertTrue(mQueue.peek() == 3);
        assertTrue(mQueue.size() == 1);
        mQueue.dequeue();
        assertTrue(mQueue.peek() == NULL);
        assertTrue(mQueue.isEmpty());
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.enqueue(3);
        mQueue.enqueue(4);
        mQueue.enqueue(5);
        mQueue.enqueue(6);
        mQueue.enqueue(7);
        mQueue.enqueue(8);
        mQueue.enqueue(9);
        mQueue.enqueue(10);
        assertTrue(mQueue.size() == 10);
        assertTrue(mQueue.peek() == 1);
        mQueue.enqueue(11);
        assertTrue(mQueue.size() == 11);
        assertTrue(mQueue.peek() == 1);
        mQueue.dequeue();
        assertTrue(mQueue.size() == 10);
        assertTrue(mQueue.peek() == 2);
        mQueue.dequeue();
        assertTrue(mQueue.size() == 9);
        assertTrue(mQueue.peek() == 3);

    }

    @Test
    public void testDequeue() {
        //Edge case: Empty queue -> Dequeue
        mQueue.enqueue(3);
        mQueue.enqueue(2);
        assertTrue(mQueue.dequeue() == 3);
        assertTrue(mQueue.peek() == 2);
        assertTrue(mQueue.dequeue() == 2);
        assertEquals(mQueue.peek(), NULL);
        assert(mQueue.isEmpty());

        mQueue.dequeue();
        assert(mQueue.isEmpty());
    }

    @Test
    public void testContent() throws IOException {
        // This is an example unit test
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }


}
