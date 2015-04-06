package com.app.concurrency;

import java.util.Arrays;
import java.util.Random;

/**
 * The main concurrency lab class
 *
 */
public class ConcurrencyMain 
{
    public static void main( String[] args ) {
    	
    	int values[] = createRandomArray(1000);
    	
    	Arrays.stream(values).forEach(a -> System.out.print(a + " "));
    	SingleMergeSort singleSorter = new SingleMergeSort(values);
    	singleSorter.sort();
    	System.out.println("\n After sorting:");
    	Arrays.stream(values).forEach(a -> System.out.print(a + " "));
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
