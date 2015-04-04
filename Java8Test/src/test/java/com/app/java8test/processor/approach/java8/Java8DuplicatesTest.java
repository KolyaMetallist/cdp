/**
 * 
 */
package com.app.java8test.processor.approach.java8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.app.java8test.processor.approach.AbstractApproachTest;

/**
 * Test class for Java8Duplicates
 *
 */
public class Java8DuplicatesTest extends AbstractApproachTest{
	
	private Java8Duplicates task;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		task = new Java8Duplicates();
	}

	/**
	 * Test method for {@link com.app.java8test.processor.approach.java8.Java8Duplicates#taskExecution(java.io.File, boolean)}.
	 */
	@Test
	public void testTaskExecution() {
		assertArrayEquals("should be same", task.taskExecution(getFile(), false).toArray(), getExpectedDuplicates().toArray());
	}
	
	/**
	 * Test method for {@link com.app.java8test.processor.approach.java8.Java8Duplicates#taskExecution(java.io.File, boolean)}.
	 */
	@Test
	public void testTaskExecutionParallel() {
		assertArrayEquals("should be same", task.taskExecution(getFile(), true).toArray(), getExpectedDuplicates().toArray());
	}


}
