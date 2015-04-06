package com.app.concurrency;

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
