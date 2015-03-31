/**
 * 
 */
package com.app.java8test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Mykola_Bazhenov
 *
 */
public abstract class AbstractApproachTest {
	
	private static final String TEST_INPUT_TXT = "testInput.txt";
	private File testFile;
	
	private final List<String> expectedWords = Arrays.asList("one", "two", "three", "one", "two", "three",
			"five", "hundred", "one", "one", "one", "two", "five", "six");

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
	
	@Test
	public void testFile() {
		assertTrue(getFile().exists());
	}

	public List<String> getExpectedWords() {
		return expectedWords;
	}
}
