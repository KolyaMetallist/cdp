package com.app.java8test.processor.approach.nonjava8;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;

/**
 * This class implements Non-Java 8 approach for the task "Duplicates"
 * 
 * Find first three words which have duplicates and print them inversely (e.g. map -> pam) in the upper case sorted by length in ascending order. (task name: duplicates)
 * PAM
 * WOLLA
 * STNEMUGRA
 *
 */
public class NonJava8Duplicates implements NonJava8Approach {

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
		List<String> list = new ArrayList<>();	
		try {
			// put in the map the words and their occurrence 
			Map<String, Integer> wordsFrequency = null;
			
			if (parallel) {
				
			} else {
				wordsFrequency = new LinkedHashMap<>();
				for(String word : this.readWordsFromText(file, parallel)) {
					if (wordsFrequency.containsKey(word)) {
						wordsFrequency.put(word, wordsFrequency.get(word).intValue() + 1);
					} else {
						wordsFrequency.put(word, 1);
					}
				}
			}
			
			// remove unique words
			for(Iterator<Entry<String, Integer>> it = wordsFrequency.entrySet().iterator(); it.hasNext();) {
				Entry<String, Integer> entry = it.next();
				if (entry.getValue() < 2) {
					it.remove();
				}
			}
			
			// create the list of duplicates
			list.addAll(wordsFrequency.keySet());
			// identify the top index
			int topIndex = list.size() > 2 ? 3 : list.size() > 1 ? 2 : list.size() > 0 ? 1 : 0;
			// truncate the list
			list = list.subList(0, topIndex);
			
			// sort the list by the word length ascending
			Collections.sort(list, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return Integer.valueOf(o1.length())
							.compareTo(Integer.valueOf(o2.length()));
				}
				
			});
			
			// reverse the words and transform them to upper case
			for(String s : list) {
				list.set(list.indexOf(s),
							new StringBuilder(s)
								.reverse()
								.toString()
								.toUpperCase());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	class Producer implements Runnable {
		
		private BlockingQueue<String> queue;
		private List<String> words;

		@Override
		public void run() {	
			try {
				for(String word : words){
					queue.put(word);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
