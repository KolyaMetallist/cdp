package com.app.java8test.processor.approach;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This common interface encapsulates the behavior of text analyzer tasks
 *
 */
public interface Approach {
	
	/**
	 * Executes the text analyzer task
	 * 
	 * @param file - the input file
	 * @param flag - the flag for single/multi threading
	 * @return list of words
	 */
	List<?> taskExecution(File file, boolean parallel);
	
	List<String> readWordsFromText(File file, boolean parallel) throws IOException;

}
