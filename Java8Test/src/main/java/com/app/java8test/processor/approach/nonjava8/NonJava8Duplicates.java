package com.app.java8test.processor.approach.nonjava8;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import com.app.java8test.main.CommonConstants;

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
	
	private static final String STOP_THREAD = "STOP_THREAD";

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
				wordsFrequency = frequencyCounterInParallel(file);
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
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Fill the frequency map using Producer-Consumer Pattern
	 * 
	 * @param file - the input file
	 * @return the map of words and their occurrences 
	 * @throws IOException
	 */
	private Map<String, Integer> frequencyCounterInParallel(File file) throws InterruptedException {
		Map<String, Integer> wordsFrequency = Collections.synchronizedMap(new LinkedHashMap<>());
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024);
		Thread producer = new Thread(new Producer(queue, file));
		Thread consumer = new Thread(new Consumer(queue, wordsFrequency));
		producer.start();
		consumer.start();
		producer.join();
		consumer.join();
		return wordsFrequency;
	}
	
	class Producer implements Runnable {
		
		private BlockingQueue<String> queue;
		private File file;
		
		public Producer(BlockingQueue<String> queue, File file) {
			this.file = file;
			this.queue = queue;
		}

		@Override
		public void run() {	
			try(BufferedReader bufferReader = Files.newBufferedReader(file.toPath())) {
				String line = null;
				while ((line = bufferReader.readLine()) != null){
					String[] lineWords = line.split(CommonConstants.SPLIT_TEXT_REGEX); 
					for(String word : lineWords) {
						if (word.length() > 0) {
							queue.put(word.toLowerCase());
						}
					}
				}
				queue.put(STOP_THREAD);
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	class Consumer implements Runnable {

		
		private BlockingQueue<String> queue;
		private Map<String, Integer> wordsFrequency;
		
		public Consumer(BlockingQueue<String> queue, Map<String, Integer> wordsFrequency) {
			this.queue = queue;
			this.wordsFrequency = wordsFrequency;
		}
		
		@Override
		public void run() {
			try {
				String word = null;
				while(!((word = queue.take()).equals(STOP_THREAD))) {
					if (wordsFrequency.containsKey(word)) {
						wordsFrequency.put(word, wordsFrequency.get(word).intValue() + 1);
					} else {
						wordsFrequency.put(word, 1);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		
	}

}
