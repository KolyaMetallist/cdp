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

public class NonJava8Frequency implements NonJava8Approach {

	@Override
	public List<?> taskExecution(File file, boolean parallel) {
		List<Entry<String, Integer>> entryList = new ArrayList<>();
		try {
			Map<String, Integer> wordsFrequency = new HashMap<>();
			for(String word : this.readWordsFromText(file, parallel)) {
				if (wordsFrequency.containsKey(word)) {
					wordsFrequency.put(word, wordsFrequency.get(word).intValue() + 1);
				} else {
					wordsFrequency.put(word, 1);
				}
			}
						
			entryList.addAll(wordsFrequency.entrySet());
			
			Collections.sort(entryList, new Comparator<Entry<String, Integer>>(){

				@Override
				public int compare(Entry<String, Integer> o1,
						Entry<String, Integer> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}
				
			});
			
			int topIndex = entryList.size() > 1 ? 2 : entryList.size() > 0 ? 1 : 0;
			
			entryList = entryList.subList(0, topIndex);
			
			Collections.sort(entryList, new Comparator<Entry<String, Integer>>(){

				@Override
				public int compare(Entry<String, Integer> o1,
						Entry<String, Integer> o2) {
					return o2.getKey().compareTo(o1.getKey());
				}
				
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entryList;
	}
}
