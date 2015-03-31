/**
 * 
 */
package com.app.java8test.processor.approach.java8;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.app.java8test.AbstractApproachTest;

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

	@Test
	public void testReadWordsFromText() throws IOException {
		assertArrayEquals("should be same", approach.readWordsFromText(getFile(), false).toArray(), getExpectedWords().toArray());
	}

}
