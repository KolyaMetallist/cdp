package com.app.java8test.processor.approach.java8;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Java8Duplicates implements Java8Approach {

	@Override
	public void taskExecution(File file, boolean parallel, PrintStream printer) {
		try {
			if (parallel) {
				Collections.synchronizedList(this.readWordsFromText(file, parallel))
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
					.forEachOrdered(printer::println);
			} else {
				this.readWordsFromText(file, parallel)
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
					.forEach(printer::println);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
