package com.app.java8test.processor.approach.nonjava8;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
			Collections.sort(words, new Comparator<String>(){

				@Override
				public int compare(String o1, String o2) {
					return Integer.valueOf(o2.length())
							.compareTo(Integer.valueOf(o1.length()));
				}
				
			});
			
			// identify the top index
			int topIndex = words.size() > 2 ? 3 : words.size() > 1 ? 2 : words.size() > 0 ? 1 : 0;
			
			// create the map of the words and their length from the list truncated list
			Map<String, Integer> map = new LinkedHashMap<>();
			for(String s : words.subList(0, topIndex)) {
				map.put(s, s.length());
			}
			
			// transform the map to the list of entries
			list.addAll(map.entrySet());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
