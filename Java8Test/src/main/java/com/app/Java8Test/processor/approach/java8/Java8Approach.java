package com.app.java8test.processor.approach.java8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import com.app.java8test.processor.approach.Approach;

import static java.util.stream.Collectors.toList;

public interface Java8Approach extends Approach {
	
	default List<String> readWordsFromText(File file, boolean parallel) throws IOException{
		return parallel ? 
			Files.lines(file.toPath())
				.parallel()
				.map(line -> line.split("[ \n\t\r.,;:!?(){}]")) // Stream<String[]>
				.flatMap(Arrays::stream) // Stream<String>
				.filter(s -> s.length() > 0) // avoid empty word
				.map(String::toLowerCase) // convert String to lower case
				.collect(toList()) :
			Files.lines(file.toPath())
			.map(line -> line.split("[ \n\t\r.,;:!?(){}]")) // Stream<String[]>
			.flatMap(Arrays::stream) // Stream<String>
			.filter(s -> s.length() > 0) // avoid empty word
			.map(String::toLowerCase)
			.collect(toList()); // convert String to lower case
	}

}
