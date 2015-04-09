package com.app.concurrency;

/**
 * The wrapper to launch the merge sorting in the separate thread
 *
 */
public class Sorter implements Runnable {
	
	private int[] a;
	private int threadCount;
	
	public Sorter(int[] a, int threadCount) {
		this.a = a;
		this.threadCount = threadCount;
	}

	@Override
	public void run() {
		ParallelMergeSort2.parallelMergeSort(a, threadCount);
	}

}
