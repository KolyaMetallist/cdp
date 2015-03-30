package com.app.java8test.processor.approach.nonjava8;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * This class implements Non-Java 8 approach for the task "Length"
 * 
 * Find first three longest words and print this words along with the their length sorted them in a descend order by the total number of letters each word contains (task name: length) 
 * battle -> 6
 * map -> 3 
 * a – 1
 *
 */
public class NonJava8Length implements NonJava8Approach {

	private final Comparator<String> stringByLength = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return Integer.valueOf(o2.length())
					.compareTo(Integer.valueOf(o1.length()));
		}
	};

	/**
	 * Executes the text analyzer task
	 * 
	 * @param file - the input file
	 * @param flag - the flag for single/multi threading
	 * @return list of results
	 * 
	 * @see com.app.java8test.processor.approach.Approach#taskExecution(java.io.File, boolean)
	 */
	@Override
	public List<?> taskExecution(File file, boolean parallel) {
		List<Entry<String, Integer>> list = new ArrayList<>();
		
		try {
			List<String> words = this.readWordsFromText(file, parallel);
			
			// remove duplicates from the list of words
			Set<String> uniques = new HashSet<>();
			uniques.addAll(words);
			words.clear();
			words.addAll(uniques);

			// sort the list by the length of words descending
			if (parallel) {
				words = parallelMergeSort(words);
			} else {
				Collections.sort(words, stringByLength);
			}
			
			// identify the top index
			int topIndex = words.size() > 2 ? 3 : words.size() > 1 ? 2 : words.size() > 0 ? 1 : 0;
			
			// create the map of the words and their length from the list truncated list
			Map<String, Integer> map = new LinkedHashMap<>();
			for(String s : words.subList(0, topIndex)) {
				map.put(s, s.length());
			}
			
			// transform the map to the list of entries
			list.addAll(map.entrySet());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * Sorts words by their length descending using Fork/Join Framework
	 * 
	 * @param words - list of words
	 * @return - sorted list
	 * @throws InterruptedException
	 */
	private List<String> parallelMergeSort(List<String> words) throws InterruptedException {
		String[] values = words.toArray(new String[0]);
		ForkJoinPool pool = new ForkJoinPool();
		MergeSortStringByLength sort = new MergeSortStringByLength(values, 0, values.length);
		pool.invoke(sort);

		return Arrays.asList(sort.getResult());
	}
	
	/**
	 * Merge sort in parallel threads 
	 * based on the Fork/Join Framework
	 *
	 */
	class MergeSortStringByLength extends RecursiveAction {
	    
		private static final long serialVersionUID = 1L;
		private static final int SEQUENTIAL_THRESHOLD = 1000;
		private String[] numbers;
	    private int startPos, endPos;
	    private String[] result;
	    
	    public String[] getResult() {
			return result;
		}

		public MergeSortStringByLength(String[] numbers, int startPos, int endPos) {
			this.numbers = numbers;
			this.startPos = startPos;
			this.endPos = endPos;
			this.result = new String[numbers.length];
		}

		private void merge(MergeSortStringByLength left, MergeSortStringByLength right) {
	        int i=0, leftPos=0, rightPos=0, leftSize = left.size(), rightSize = right.size();
	        while (leftPos < leftSize && rightPos < rightSize)
	            result[i++] = (left.result[leftPos].length() >= right.result[rightPos].length())
	                    ? left.result[leftPos++]
	                    : right.result[rightPos++];
	        while (leftPos < leftSize)
	            result[i++] = left.result[leftPos++];
	        while (rightPos < rightSize)
	            result[i++] = right.result[rightPos++];
	    }

	    public int size() {
	        return endPos-startPos;
	    }

	    @Override
	    protected void compute() {
	        if (size() < SEQUENTIAL_THRESHOLD) {
	            System.arraycopy(numbers, startPos, result, 0, size());
	            Arrays.sort(result, 0, size(), stringByLength);
	        }
	        else {
	            int midpoint = size() / 2;
	            MergeSortStringByLength left = new MergeSortStringByLength(numbers, startPos, startPos+midpoint);
	            MergeSortStringByLength right = new MergeSortStringByLength(numbers, startPos+midpoint, endPos);
	            invokeAll(left, right);
	            merge(left, right);
	        }
	    }
	}
	

}
