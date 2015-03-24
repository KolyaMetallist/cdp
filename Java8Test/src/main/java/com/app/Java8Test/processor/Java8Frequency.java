package com.app.Java8Test.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Java8Approach implements Approach {

	@Override
	public void frequenceTask(File file, boolean parallel) {
		try {
			Files.lines(file.toPath())
				.map(line -> line.split("[ \n\t\r.,;:!?(){}]")) // Stream<String[]>
				.flatMap(Arrays::stream) // Stream<String>
				.filter(s -> s.length() > 0) // avoid empty word
				.map(String::toLowerCase) // convert String to lower case
				.collect(groupingBy(e -> e, counting())) // convert to Map of words and their frequency
				.entrySet() // extract entrySet from Map
				.stream() // Stream<Entry<String, Long>>
				.sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // sorting by value descending
				.limit(2) // truncate 2 top entries
				.sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())) // sorting by words descending
				.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
