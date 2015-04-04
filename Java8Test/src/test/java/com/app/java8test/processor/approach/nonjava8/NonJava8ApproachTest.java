/**
 * 
 */
package com.app.java8test.processor.approach.nonjava8;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.ArrayComparisonFailure;

import com.app.java8test.processor.approach.AbstractApproachTest;

/**
 * Test class for NonJava8Approach
 *
 */
public class NonJava8ApproachTest extends AbstractApproachTest{
	
	private NonJava8Approach approach;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		approach = new NonJava8Approach() {

			@Override
			public List<?> taskExecution(File file, boolean parallel) {
				// do not need this for testing
				return null;
			}
		};
	}

	/**
	 * Test method for {@link com.app.java8test.processor.approach.nonjava8.NonJava8Approach#readWordsFromText(java.io.File, boolean)}.
	 * @throws IOException 
	 * @throws ArrayComparisonFailure 
	 */
	@Test
	public void testReadWordsFromText() throws ArrayComparisonFailure, IOException {
		assertArrayEquals("should be same", approach.readWordsFromText(getFile(), false).toArray(), getExpectedWords().toArray());
	}

}
