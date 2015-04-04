/**
 * 
 */
package com.app.java8test.processor.approach.java8;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.app.java8test.processor.approach.AbstractApproachTest;

/**
 * Test class for Java8Approach
 *
 */
public class Java8ApproachTest extends AbstractApproachTest{
	
	private Java8Approach approach;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		approach = new Java8Approach() {

			@Override
			public List<?> taskExecution(File file, boolean parallel) {
				// do not need this for testing
				return null;
			}
			
		};
	}
	
	/**
	 * Check if the test input file exists
	 */
	@Test
	public void testFile() {
		assertTrue(getFile().exists());
	}

	/**
	 * Test method for {@link com.app.java8test.processor.approach.java8.Java8Approach#readWordsFromText(java.io.File, boolean)}.
	 * @throws IOException 
	 */
	@Test
	public void testReadWordsFromText() throws IOException {
		assertArrayEquals("should be same", approach.readWordsFromText(getFile(), false).toArray(), getExpectedWords().toArray());
	}

}
