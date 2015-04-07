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
    	
    	int values[] = createRandomArray(1024000);
    	int values2[] = values.clone();
    	int values3[] = values.clone();
    	
    	System.out.println("Array size = " + values.length);
    	
    	//Arrays.stream(values).forEach(a -> System.out.print(a + " "));
    	
    	long tStart = System.currentTimeMillis();
    	SingleMergeSort singleSorter = new SingleMergeSort(values);
    	singleSorter.sort();
    	long elapsedTime = System.currentTimeMillis() - tStart;
		
    	System.out.println("\nAfter sorting:");
    	//Arrays.stream(values).forEach(a -> System.out.print(a + " "));
    	System.out.println("\nElapsed time: " + elapsedTime + " ms\n");
    	
    	//Arrays.stream(values2).forEach(a -> System.out.print(a + " "));
    	
    	int cores = Runtime.getRuntime().availableProcessors(); 
    	//cores = 8;
    	tStart = System.currentTimeMillis();
    	Thread parallelSorter = new Thread(new ParallelMergeSort(values2, cores));
    	parallelSorter.start();
    	parallelSorter.join();
    	elapsedTime = System.currentTimeMillis() - tStart;
    	
    	System.out.println("\nAfter parallel sorting:");
    	//Arrays.stream(values2).forEach(a -> System.out.print(a + " "));
    	System.out.println("\n" + cores + " threads. Elapsed time: " + elapsedTime + " ms\n");
    	
    	//Arrays.stream(values3).forEach(a -> System.out.print(a + " "));
    	
    	tStart = System.currentTimeMillis();
    	ParallelMergeSort2.parallelMergeSort(values3, cores);
    	elapsedTime = System.currentTimeMillis() - tStart;
    	
    	System.out.println("\nAfter parallel static sorting:");
    	//Arrays.stream(values3).forEach(a -> System.out.print(a + " "));
    	System.out.println("\n" + cores + " threads. Elapsed time: " + elapsedTime + " ms");
    }
    
    public static int[] createRandomArray(int length) {
		int[] a = new int[length];
		Random r = new Random();
		for (int i = 0; i < a.length; i++) {
			a[i] = r.nextInt(1000);
		}
		return a;
	}
}
