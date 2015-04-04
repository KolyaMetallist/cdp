/**
 * 
 */
package com.app.java8test.processor.approach.java8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.app.java8test.AbstractApproachTest;

/**
 * Test class for Java8Frequency
 *
 */
public class Java8FrequencyTest extends AbstractApproachTest {
	
	private Java8Frequency task;

	/* (non-Javadoc)
	 * @see com.app.java8test.AbstractApproachTest#setUp()
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		task = new Java8Frequency();
	}

	/**
	 * Test method for {@link com.app.java8test.processor.approach.java8.Java8Frequency#taskExecution(java.io.File, boolean)}.
	 */
	@Test
	public void testTaskExecution() {
		assertArrayEquals("should be same", task.taskExecution(getFile(), false).toArray(), getExpectedFrequencies().toArray());
	}
	
	/**
	 * Test method for {@link com.app.java8test.processor.approach.java8.Java8Frequency#taskExecution(java.io.File, boolean)}.
	 */
	@Test
	public void testTaskExecutionParallel() {
		assertArrayEquals("should be same", task.taskExecution(getFile(), true).toArray(), getExpectedFrequencies().toArray());
	}

}
