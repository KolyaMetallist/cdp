package com.app.concurrency;

/**
 * This class implements the merge sort algorithm in a single thread
 *
 */
public class SingleMergeSort {
	
	private int[] values;
	private int[] result;
	
	public SingleMergeSort(int[] values) {
		this.values = values;
		result = new int[values.length];
	}
	
	/**
	 * Sorts the input array
	 */
	public void sort() {
		mergeSort(0, values.length - 1);
	}
	
	/**
	 * Provides merge sorting algorithm by splitting the array, sorts the parts,
	 * and merges them into the final result
	 * 
	 * @param low - the begin index
	 * @param high - the end index
	 */
	private void mergeSort(int low, int high) {
	    // check if low is smaller then high, if not then the array is sorted
	    if (low < high) {
	      // Get the index of the element which is in the middle
	      int middle = low + (high - low) / 2;
	      // Sort the left side of the array
	      mergeSort(low, middle);
	      // Sort the right side of the array
	      mergeSort(middle + 1, high);
	      // Combine them both
	      merge(low, middle, high);
	    }
	}

	/**
	 * Merges the sorted parts into the array
	 * through a temporary array
	 * 
	 * @param low - the begin index
	 * @param middle - the middle index
	 * @param high - the end index
	 */
	private void merge(int low, int middle, int high) {
	    // Copy both parts into the helper array
	    for (int i = low; i <= high; i++) {
	    	result[i] = values[i];
	    }

	    int i = low;
	    int j = middle + 1;
	    int k = low;
	    // Copy the smallest values from either the left or the right side back
	    // to the original array
	    while (i <= middle && j <= high) {
	    	if (result[i] <= result[j]) {
	    		values[k] = result[i];
	    		i++;
	    	} else {
	    		values[k] = result[j];
	    		j++;
	    	}
	    	k++;
	    }
	    // Copy the rest of the left side of the array into the target array
	    while (i <= middle) {
	    	values[k] = result[i];
	    	k++;
	    	i++;
	    }
	}

}
