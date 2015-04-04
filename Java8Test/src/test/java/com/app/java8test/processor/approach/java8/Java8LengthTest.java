/**
 * 
 */
package com.app.java8test.processor.approach.java8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.app.java8test.AbstractApproachTest;

/**
 * Test class for Java8Length
 *
 */
public class Java8LengthTest extends AbstractApproachTest {
	
	private Java8Length task;

	/* (non-Javadoc)
	 * @see com.app.java8test.AbstractApproachTest#setUp()
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		task = new Java8Length();
	}

	/**
	 * Test method for {@link com.app.java8test.processor.approach.java8.Java8Length#taskExecution(java.io.File, boolean)}.
	 */
	@Test
	public void testTaskExecution() {
		assertArrayEquals("should be same", task.taskExecution(getFile(), false).toArray(), getExpectedLengths().toArray());
	}
	
	/**
	 * Test method for {@link com.app.java8test.processor.approach.java8.Java8Length#taskExecution(java.io.File, boolean)}.
	 */
	@Test
	public void testTaskExecutionParallel() {
		assertArrayEquals("should be same", task.taskExecution(getFile(), true).toArray(), getExpectedLengths().toArray());
	}

}
