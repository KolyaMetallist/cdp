/**
 * 
 */
package com.app.java8test.processor.approach;

import java.io.File;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Before;

/**
 * Abstract test class with common methods and variables
 *
 */
public abstract class AbstractApproachTest {
	
	private static final String TEST_INPUT_TXT = "testInput.txt";
	private File testFile;
	
	private final List<String> expectedWords = Arrays.asList("one", "two", "three", "one", "two", "three",
			"five", "hundred", "one", "one", "one", "two", "five", "six");
	
	private final List<String> expectedDuplicates = Arrays.asList("ENO", "OWT", "EERHT");
	
	private final List<Entry<String, Long>> expectedFrequencies = Arrays.asList(
			new AbstractMap.SimpleEntry<String, Long>("two", (long) 3),
			new AbstractMap.SimpleEntry<String, Long>("one", (long) 5));
	
	private final List<Entry<String, Integer>> expectedLengths = Arrays.asList(
			new AbstractMap.SimpleEntry<String, Integer>("hundred", 7),
			new AbstractMap.SimpleEntry<String, Integer>("three", 5),
			new AbstractMap.SimpleEntry<String, Integer>("five", 4));

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testFile = new File(TEST_INPUT_TXT);
	}
	
	public File getFile() {
		return testFile;
	}

	public List<String> getExpectedWords() {
		return expectedWords;
	}
	
	public List<String> getExpectedDuplicates() {
		return expectedDuplicates;
	}

	public List<Entry<String, Long>> getExpectedFrequencies() {
		return expectedFrequencies;
	}

	public List<Entry<String, Integer>> getExpectedLengths() {
		return expectedLengths;
	}

}
