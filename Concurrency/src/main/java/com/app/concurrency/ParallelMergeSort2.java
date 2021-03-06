/**
 * 
 */
package com.app.concurrency;

import java.util.Arrays;

/**
 * This class implements the merge sort algorithm using fork/join concept for synchronization
 *
 */
public class ParallelMergeSort2 {

	/**
	 *  The main sorting method, defines either to continue the array splitting in parallel,
	 *  or to split it within the current thread
	 */
	public static void parallelMergeSort(int[] a, int threadCount) {
		if (threadCount <= 1) {
			mergeSort(a);
		} else if (a.length >= 2) {
			// split array in half
			int[] left  = Arrays.copyOfRange(a, 0, a.length / 2);
			int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);
			
			// sort the halves
			// mergeSort(left);
			// mergeSort(right);
			Thread lThread = new Thread(new Sorter(left,  threadCount / 2));
			Thread rThread = new Thread(new Sorter(right, threadCount / 2));
			lThread.start();
			rThread.start();
			
			try {
				lThread.join();
				rThread.join();
			} catch (InterruptedException ie) {}
			
			
			// merge them back together
			merge(left, right, a);
		}
	}
	
	/** 
	 * Arranges the elements of the given array into sorted order
	 * using the "merge sort" algorithm, which splits the array in half,
	 * recursively sorts the halves, then merges the sorted halves.
	 * It is O(N log N) for all inputs.
	 * 
	 * @param a - the array to be sorted
	 */
	public static void mergeSort(int[] a) {
		if (a.length >= 2) {
			// split array in half
			int[] left  = Arrays.copyOfRange(a, 0, a.length / 2);
			int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);
			
			// sort the halves
			mergeSort(left);
			mergeSort(right);
			
			// merge them back together
			merge(left, right, a);
		}
	}
	
	/** 
	 * Combines the contents of sorted left/right arrays into output array a.
	 * Assumes that left.length + right.length == a.length.
	 * 
	 * @param left - the left part of the array
	 * @param right - the rigth part of the array
	 * @param a - the result array
	 */
	public static void merge(int[] left, int[] right, int[] a) {
		int i1 = 0;
		int i2 = 0;
		for (int i = 0; i < a.length; i++) {
			if (i2 >= right.length || (i1 < left.length && left[i1] < right[i2])) {
				a[i] = left[i1];
				i1++;
			} else {
				a[i] = right[i2];
				i2++;
			}
		}
	}

}
