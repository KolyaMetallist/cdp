package com.app.java8test.processor.approach.java8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import com.app.java8test.main.CommonConstants;
import com.app.java8test.processor.approach.Approach;

import static java.util.stream.Collectors.toList;

/**
 * This interface extends the general interface Approach
 * and implement the default file reading for Java 8 approach
 *
 */
public interface Java8Approach extends Approach {
	
	/**
	 * Returns the list of words from the text
	 * 
	 * @param file - the input file
	 * @param parallel - the flag for single/multi threading
	 * @return list of words
	 * 
	 * @see com.app.java8test.processor.approach.Approach#readWordsFromText(java.io.File, boolean)
	 */
	default List<String> readWordsFromText(File file, boolean parallel) throws IOException{
		return parallel ? 
			Files.lines(file.toPath())
				.parallel()
				.map(line -> line.split(CommonConstants.SPLIT_TEXT_REGEX)) // Stream<String[]>
				.flatMap(Arrays::stream) // Stream<String>
				.filter(s -> s.length() > 0) // avoid empty word
				.map(String::toLowerCase) // convert String to lower case
				.collect(toList()) :
			Files.lines(file.toPath())
			.map(line -> line.split(CommonConstants.SPLIT_TEXT_REGEX)) // Stream<String[]>
			.flatMap(Arrays::stream) // Stream<String>
			.filter(s -> s.length() > 0) // avoid empty word
			.map(String::toLowerCase)
			.collect(toList()); // convert String to lower case
	}

}
