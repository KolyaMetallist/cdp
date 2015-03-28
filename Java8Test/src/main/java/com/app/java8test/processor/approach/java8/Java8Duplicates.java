package com.app.java8test.processor.approach.java8;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Java8Duplicates implements Java8Approach {

	@Override
	public List<?> taskExecution(File file, boolean parallel) {
		List<?> list = new ArrayList<>();
		try {
			if (parallel) {
				list = Collections.synchronizedList(this.readWordsFromText(file, parallel))
					.parallelStream() // Stream<String>
					.collect(groupingBy(e -> e, LinkedHashMap::new, counting())) // convert to Map of words and their frequency
					.entrySet() // extract entrySet from Map
					.parallelStream() // Stream<Entry<String, Long>>
					.filter(e -> e.getValue() > 1) // filter all duplicates
					.limit(3) // truncate top 3 entries
					.map(Entry::getKey) // extract the list of duplicates
					.map(String::toUpperCase) // convert to upper case
					.map(e -> {return new StringBuffer(e).reverse().toString();}) // inverse
					.sorted(comparing(String::length)) // sort by length ascending
					.collect(toList());
			} else {
				list = this.readWordsFromText(file, parallel)
					.stream() // Stream<String>
					.collect(groupingBy(e -> e, LinkedHashMap::new, counting())) // convert to Map of words and their frequency
					.entrySet() // extract entrySet from Map
					.stream() // Stream<Entry<String, Long>>
					.filter(e -> e.getValue() > 1) // filter all duplicates
					.limit(3) // truncate top 3 entries
					.map(Entry::getKey) // extract the list of duplicates
					.map(String::toUpperCase) // convert to upper case
					.map(e -> {return new StringBuilder(e).reverse().toString();}) // inverse
					.sorted(comparing(String::length)) // sort by length ascending
					.collect(toList());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

}
