/**
 * 
 */
package com.app.java8test.processor.approach.nonjava8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.app.java8test.processor.approach.AbstractApproachTest;

/**
 * Test class for NonJava8Duplicates
 *
 */
public class NonJava8DuplicatesTest extends AbstractApproachTest {
	
	private NonJava8Duplicates task;

	/* (non-Javadoc)
	 * @see com.app.java8test.AbstractApproachTest#setUp()
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		task = new NonJava8Duplicates();
	}

	/**
	 * Test method for {@link com.app.java8test.processor.approach.nonjava8.NonJava8Duplicates#taskExecution(java.io.File, boolean)}.
	 */
	@Test
	public void testTaskExecution() {
		assertArrayEquals("should be same", task.taskExecution(getFile(), false).toArray(), getExpectedDuplicates().toArray());
	}
	
	/**
	 * Test method for {@link com.app.java8test.processor.approach.nonjava8.NonJava8Duplicates#taskExecution(java.io.File, boolean)}.
	 */
	@Test
	public void testTaskExecutionParallel() {
		assertArrayEquals("should be same", task.taskExecution(getFile(), true).toArray(), getExpectedDuplicates().toArray());
	}

}
