package com.app.concurrency;

import java.util.Arrays;

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
	
	public void parallelMergeSort() {
		synchronized (values) {
			if (threadCount <= 1) {
				mergeSort(values);
				values.notify();
			} else if (values.length >= 2) {
				// split array in half
				int[] left  = Arrays.copyOfRange(values, 0, values.length / 2);
				int[] right = Arrays.copyOfRange(values, values.length / 2, values.length);
				
				synchronized(left) {
					synchronized (right) {
						// sort the halves
						// mergeSort(left);
						// mergeSort(right);
						Thread lThread = new Thread(new ParallelMergeSort(left,  threadCount / 2));
						Thread rThread = new Thread(new ParallelMergeSort(right, threadCount / 2));
						lThread.start();
						rThread.start();
						
						/*try {
							lThread.join();
							rThread.join();
						} catch (InterruptedException ie) {}*/
						try {
							left.wait();
							right.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// merge them back together
						merge(left, right, values);
					}
				}
				values.notify();
			}
		}
	}
	
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
