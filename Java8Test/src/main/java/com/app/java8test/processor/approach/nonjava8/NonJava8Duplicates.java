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

public class NonJava8Duplicates implements NonJava8Approach {

	@Override
	public List<?> taskExecution(File file, boolean parallel) {
		List<String> list = new ArrayList<>();	
		try {
			Map<String, Integer> wordsFrequency = new LinkedHashMap<>();
			for(String word : this.readWordsFromText(file, parallel)) {
				if (wordsFrequency.containsKey(word)) {
					wordsFrequency.put(word, wordsFrequency.get(word).intValue() + 1);
				} else {
					wordsFrequency.put(word, 1);
				}
			}
			
			for(Iterator<Entry<String, Integer>> it = wordsFrequency.entrySet().iterator(); it.hasNext();) {
				Entry<String, Integer> entry = it.next();
				if (entry.getValue() < 2) {
					it.remove();
				}
			}
			
			list.addAll(wordsFrequency.keySet());
			int topIndex = list.size() > 2 ? 3 : list.size() > 1 ? 2 : list.size() > 0 ? 1 : 0;
			list = list.subList(0, topIndex);
			
			Collections.sort(list, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return Integer.valueOf(o1.length())
							.compareTo(Integer.valueOf(o2.length()));
				}
				
			});
			
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

}
