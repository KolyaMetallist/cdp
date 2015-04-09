/**
 * 
 */
package com.app.concurrency;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Test class for merge sorting implementation
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MergeSortTest {
	
	private int[] numbers;
	private final int arraySize = 10000000;
	private int threadCount;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		numbers = ConcurrencyMain.createRandomArray(arraySize);
	    threadCount = ConcurrencyMain.getSortThreads();
	}
	
	/**
	 * Checks if the current array is sorted, if not throws assert fail
	 * 
	 * @param array - the array to be checked
	 */
	private void testArraySorted(int[] array) {
		for (int i = 0; i < numbers.length - 1; i++) {
			if (numbers[i] > numbers[i + 1]) {
				fail("Should not happen");
			}
		}
		assertTrue(true);
	}

	/**
	 *  Tests the single thread implementation {@link SingleMergeSort}
	 */
	@Test
	public void test1SingleThreadSort() {
		long tStart = System.currentTimeMillis();
    	SingleMergeSort singleSorter = new SingleMergeSort(numbers);
    	singleSorter.sort();
    	long elapsedTime = System.currentTimeMillis() - tStart;
    	
    	testArraySorted(numbers);
    	
    	System.out.println("Single thread sorting test. Elapsed time is " + elapsedTime + " ms\n");
	}
	
	/**
	 *  Tests the single thread implementation {@link ParallelMergeSort}
	 */
	@Test
	public void test2MultiThreadWaitNotifySort() throws InterruptedException {
		long tStart = System.currentTimeMillis();
    	Thread parallelSorter = new Thread(new ParallelMergeSort(numbers, threadCount));
    	parallelSorter.start();
    	parallelSorter.join();
    	long elapsedTime = System.currentTimeMillis() - tStart;
    	
    	testArraySorted(numbers);
    	
    	System.out.println("Multi thread with wait/notify sorting test. " + threadCount + " Threads. Elapsed time is " + elapsedTime + " ms\n");
	}
	
	/**
	 *  Tests the single thread implementation {@link ParallelMergeSort2}
	 */
	@Test
	public void test3MultiThreadForkJoinSort() throws InterruptedException {
		long tStart = System.currentTimeMillis();
		ParallelMergeSort2.parallelMergeSort(numbers, threadCount);
    	long elapsedTime = System.currentTimeMillis() - tStart;
    	
    	testArraySorted(numbers);
    	
    	System.out.println("Multi thread with fork/join sorting test. " + threadCount + " Threads. Elapsed time is " + elapsedTime + " ms\n");
	}

}
