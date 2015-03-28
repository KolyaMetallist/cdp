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

public class NonJava8Length implements NonJava8Approach {

	@Override
	public List<?> taskExecution(File file, boolean parallel) {
		List<Entry<String, Integer>> list = new ArrayList<>();
		
		try {
			List<String> words = this.readWordsFromText(file, parallel);
			
			Set<String> uniques = new HashSet<>();
			uniques.addAll(words);
			words.clear();
			words.addAll(uniques);
			
			Collections.sort(words, new Comparator<String>(){

				@Override
				public int compare(String o1, String o2) {
					return Integer.valueOf(o2.length())
							.compareTo(Integer.valueOf(o1.length()));
				}
				
			});
			
			int topIndex = words.size() > 2 ? 3 : words.size() > 1 ? 2 : words.size() > 0 ? 1 : 0;
			
			Map<String, Integer> map = new LinkedHashMap<>();
			for(String s : words.subList(0, topIndex)) {
				map.put(s, s.length());
			}
			
			list.addAll(map.entrySet());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
