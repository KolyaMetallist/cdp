package com.app.java8test.processor.approach.java8;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.groupingByConcurrent;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Java8Frequency implements Java8Approach {

	@Override
	public List<?> taskExecution(File file, boolean parallel) {
		List<?> list = new ArrayList<>();
		try {
			if (parallel) {
				list = Collections.synchronizedList(this.readWordsFromText(file, parallel))
					.parallelStream() // Stream<String>
					.collect(groupingByConcurrent(e -> e, counting())) // convert to Map of words and their frequency
					.entrySet() // extract entrySet from Map
					.parallelStream() // Stream<Entry<String, Long>>
					.sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // sorting by value descending
					.limit(2) // truncate 2 top entries
					.sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())) // sorting by words descending
					.collect(toList());
			} else {
				list = this.readWordsFromText(file, parallel)
					.stream() // Stream<String>
					.collect(groupingBy(e -> e, counting())) // convert to Map of words and their frequency
					.entrySet() // extract entrySet from Map
					.stream() // Stream<Entry<String, Long>>
					.sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // sorting by value descending
					.limit(2) // truncate 2 top entries
					.sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())) // sorting by words descending
					.collect(toList());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

}
