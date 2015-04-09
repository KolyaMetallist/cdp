package com.app.concurrency;

import java.util.Arrays;
import java.util.Random;

/**
 * The main concurrency lab class
 *
 */
public class ConcurrencyMain 
{
    public static void main( String[] args ) throws InterruptedException {
    	
    	final int arraySize = 8192000;
    	
    	int values[] = createRandomArray(arraySize);
    	int values2[] = values.clone();
    	int values3[] = values.clone();
    	
    	System.out.println("Array size = " + values.length);
    	
    	//*************************************************
    	// Single thread sorting
    	//Arrays.stream(values).forEach(a -> System.out.print(a + " "));
    	long tStart = System.currentTimeMillis();
    	SingleMergeSort singleSorter = new SingleMergeSort(values);
    	singleSorter.sort();
    	long elapsedTime = System.currentTimeMillis() - tStart;
		
    	System.out.println("\nAfter sorting:");
    	//Arrays.stream(values).forEach(a -> System.out.print(a + " "));
    	System.out.println("\nElapsed time: " + elapsedTime + " ms\n");
    	
    	//*************************************************
    	// Multi thread sorting with wait/notify
    	//Arrays.stream(values2).forEach(a -> System.out.print(a + " "));
    	int cores = getSortThreads();
    	tStart = System.currentTimeMillis();
    	Thread parallelSorter = new Thread(new ParallelMergeSort(values2, cores));
    	parallelSorter.start();
    	parallelSorter.join();
    	elapsedTime = System.currentTimeMillis() - tStart;
    	
    	System.out.println("\nAfter parallel sorting with wait/notify synchronization:");
    	//Arrays.stream(values2).forEach(a -> System.out.print(a + " "));
    	System.out.println("\n" + cores + " threads. Elapsed time: " + elapsedTime + " ms\n");
    	
    	//*************************************************
    	// Multi thread sorting with fork/join
    	//Arrays.stream(values3).forEach(a -> System.out.print(a + " "));
    	tStart = System.currentTimeMillis();
    	ParallelMergeSort2.parallelMergeSort(values3, cores);
    	elapsedTime = System.currentTimeMillis() - tStart;
    	
    	System.out.println("\nAfter parallel static sorting with a fork/join concept:");
    	//Arrays.stream(values3).forEach(a -> System.out.print(a + " "));
    	System.out.println("\n" + cores + " threads. Elapsed time: " + elapsedTime + " ms");
    }
    
    /**
     * Creates array with the random values
     * 
     * @param length - the length of the array
     * @return the array
     */
    public static int[] createRandomArray(int length) {
		int[] a = new int[length];
		Random r = new Random();
		for (int i = 0; i < a.length; i++) {
			a[i] = r.nextInt(1000);
		}
		return a;
	}
    
    /**
     * Returns the count of threads used in the parallel sorting
     * 
     * @return the thread count
     */
    public static int getSortThreads() {
    	int cores = Runtime.getRuntime().availableProcessors(); 
    	//cores = 8;
    	return cores;
    }
}
