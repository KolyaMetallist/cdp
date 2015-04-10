package com.app.concurrency;

import java.util.Arrays;

/**
 * This class implements the merge sort algorithm using wait/notify for synchronization
 *
 */
public class ParallelMergeSort implements Runnable {
	
	private int[] values;
	private int threadCount;
	
	public ParallelMergeSort(int[] a, int threadCount) {
		this.values = a;
		this.threadCount = threadCount;
	}

	@Override
	public void run() {
		parallelMergeSort();
	}
	
	/**
	 *  The main sorting method, defines either to continue the array splitting in parallel,
	 *  or to split it within the current thread
	 */
	public synchronized void parallelMergeSort() {
		if (threadCount <= 1) {
			mergeSort(values);
			this.notify();
		} else if (values.length >= 2) {
			// split array in half
			int[] left  = Arrays.copyOfRange(values, 0, values.length / 2);
			int[] right = Arrays.copyOfRange(values, values.length / 2, values.length);
			
			// sort the halves
			// mergeSort(left);
			// mergeSort(right);
			Thread lThread = new Thread(new ParallelMergeSort(left,  threadCount / 2));
			Thread rThread = new Thread(new ParallelMergeSort(right, threadCount / 2));
			lThread.start();
			rThread.start();
			
			// Synchronizes by the thread objects and waits until threads finish their work
			// Refers to the Thread.join() implementation 
			try {
				synchronized (lThread) {
					while (lThread.isAlive()) {
						lThread.wait(0L);
					}
				}
				synchronized (rThread) {
					while (rThread.isAlive()) {
						rThread.wait(0L);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// merge them back together
			merge(left, right, values);
			this.notify();
		}
	}
	
	/**
	 * Provides the futher merge sorting within the current thread
	 * 
	 * @param a - the array to be sorted
	 */
	private void mergeSort(int[] a) {
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
	 * Merges two parts of array
	 * 
	 * @param left - the left part of the array
	 * @param right - the rigth part of the array
	 * @param a - the result array
	 */
	private void merge(int[] left, int[] right, int[] a) {
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
