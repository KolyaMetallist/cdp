package com.app.java8test.processor.approach.nonjava8;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.app.java8test.main.CommonConstants;
import com.app.java8test.processor.approach.Approach;

/**
 * This interface extends the general interface Approach
 * and implement the default file reading for Non-Java 8 approach
 *
 */
public interface NonJava8Approach extends Approach {
	
	/**
	 * Returns the list of words from the text
	 * 
	 * This task is performed in a single thread, because the I/O is not a CPU-bound task. See details
	 * <a>http://stackoverflow.com/questions/18971951/multithreading-to-read-a-file-in-java</a>.
	 * Thus, the parameter <i>parallel</i> doesn't involve the method behaviour.
	 * 
	 * @param file - the input file
	 * @param parallel - the flag for single/multi threading
	 * @return list of words
	 * 
	 * @see com.app.java8test.processor.approach.Approach#readWordsFromText(java.io.File, boolean)
	 */
	default List<String> readWordsFromText(File file, boolean parallel) throws IOException{
		List<String> words = new ArrayList<>();
		try(BufferedReader bufferReader = Files.newBufferedReader(file.toPath())) {
			String line = null;
			while ((line = bufferReader.readLine()) != null){
				String[] lineWords = line.split(CommonConstants.SPLIT_TEXT_REGEX); 
				for(String word : lineWords) {
					if (word.length() > 0) {
						words.add(word.toLowerCase());
					}
				}
			}
		}
		return words;
	}
}
