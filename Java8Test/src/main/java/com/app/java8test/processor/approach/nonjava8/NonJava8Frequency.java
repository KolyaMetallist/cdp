package com.app.java8test.processor.approach.nonjava8;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * This class implements Non-Java 8 approach for the task "Frequency"
 * 
 * Find the most two frequent words and print them out sorted alphabetically in a reversed order. (Task name: frequency). 
 *	good -> 23
 *	allow -> 2
 *
 */
public class NonJava8Frequency implements NonJava8Approach {

	/**
	 * Executes the text analyzer task
	 * 
	 * @param file - the input file
	 * @param flag - the flag for single/multi threading
	 * @return list of words
	 * 
	 * @see com.app.java8test.processor.approach.Approach#taskExecution(java.io.File, boolean)
	 */
	@Override
	public List<?> taskExecution(File file, boolean parallel) {
		List<Entry<String, Integer>> entryList = new ArrayList<>();
		try {
			if (parallel) {
				entryList = taskExecutionInParallel(file);
			} else {
				// put in the map the words and their occurrence 
				Map<String, Integer> wordsFrequency = new HashMap<>();
				for(String word : this.readWordsFromText(file, parallel)) {
					if (wordsFrequency.containsKey(word)) {
						wordsFrequency.put(word, wordsFrequency.get(word).intValue() + 1);
					} else {
						wordsFrequency.put(word, 1);
					}
				}
				
				// create the list of Map.Entry objects
				entryList.addAll(wordsFrequency.entrySet());
				
				// sort the entries by the value descending
				Collections.sort(entryList, new Comparator<Entry<String, Integer>>(){
	
					@Override
					public int compare(Entry<String, Integer> o1,
							Entry<String, Integer> o2) {
						return o2.getValue().compareTo(o1.getValue());
					}
					
				});
				
				// identify the top index
				int topIndex = entryList.size() > 1 ? 2 : entryList.size() > 0 ? 1 : 0;
				
				// truncate the list
				entryList = entryList.subList(0, topIndex);
				
				// sort the result list by the words descending
				Collections.sort(entryList, new Comparator<Entry<String, Integer>>(){
	
					@Override
					public int compare(Entry<String, Integer> o1,
							Entry<String, Integer> o2) {
						return o2.getKey().compareTo(o1.getKey());
					}
					
				});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entryList;
	}

	private List<Entry<String, Integer>> taskExecutionInParallel(File file) throws IOException {
		List<Entry<String, Integer>> entryList = new CopyOnWriteArrayList<>();
		
		ForkJoinPool pool = new ForkJoinPool();
		Map<String, Integer> wordsFrequency = new ConcurrentHashMap<>();
		pool.invoke(new ForkJoinFrequencyReader(Collections.synchronizedList(this.readWordsFromText(file, true)), wordsFrequency));
		
		// create the list of Map.Entry objects
		entryList.addAll(wordsFrequency.entrySet());
		
		// sort the entries by the value descending
		Collections.sort(entryList, new Comparator<Entry<String, Integer>>(){

			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
			
		});
		
		// identify the top index
		int topIndex = entryList.size() > 1 ? 2 : entryList.size() > 0 ? 1 : 0;
		
		// truncate the list
		entryList = entryList.subList(0, topIndex);
		
		// sort the result list by the words descending
		Collections.sort(entryList, new Comparator<Entry<String, Integer>>(){

			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o2.getKey().compareTo(o1.getKey());
			}
			
		});
		
		return entryList;
	}
	
	class ForkJoinFrequencyReader extends RecursiveAction {
		
		static final int SEQUENTIAL_THRESHOLD = 1000;
		
		private static final long serialVersionUID = -7784403215745552735L;
		private Map<String, Integer> wordsFrequency;
		private final int start;
		private final int end;
		private final List<String> words;
		
		public ForkJoinFrequencyReader(List<String> words, Map<String, Integer> wordsFrequency) {
			this(words, 0, words.size(), wordsFrequency);
		}
		
		private ForkJoinFrequencyReader(List<String> words, int start, int end, Map<String, Integer> wordsFrequency) {
			this.words = words;
			this.start = start;
			this.end = end;
			this.wordsFrequency = wordsFrequency;
		}
		
		private synchronized void putInMap() {
			for(int i = start; i < end; i++) {
				String word = words.get(i);
				if (wordsFrequency.containsKey(word)) {
					wordsFrequency.put(word, wordsFrequency.get(word).intValue() + 1);
				} else {
					wordsFrequency.put(word, 1);
				}
			}
		}

		@Override
		protected void compute() {
			if (end - start < SEQUENTIAL_THRESHOLD) {
				putInMap();
			} else {
				int mid = (start + end) >>> 1;
				ForkJoinFrequencyReader left = new ForkJoinFrequencyReader(words, start, mid, wordsFrequency);
				ForkJoinFrequencyReader right =	new ForkJoinFrequencyReader(words, mid, end, wordsFrequency);
				left.fork();
				right.fork();
				left.join();
				right.join();
			}
		}
		
	}
}
