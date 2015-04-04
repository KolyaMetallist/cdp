/**
 * 
 */
package com.app.java8test.processor.approach.nonjava8;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.app.java8test.AbstractApproachTest;

/**
 * Test class for NonJava8Frequency
 *
 */
public class NonJava8FrequencyTest extends AbstractApproachTest {
	
	private NonJava8Frequency task;

	/* (non-Javadoc)
	 * @see com.app.java8test.AbstractApproachTest#setUp()
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		task = new NonJava8Frequency();
	}
	
	@SuppressWarnings("unchecked")
	private void testResultMapEntries(boolean parallel) {
		List<Entry<String, Integer>> actualFrequencies = (List<Entry<String, Integer>>) task.taskExecution(getFile(), parallel);
		assertEquals(actualFrequencies.size(), getExpectedFrequencies().size());
		for(Entry<String, Long> value : getExpectedFrequencies()) {
			Entry<String, Integer> actualFrequency = actualFrequencies.get(getExpectedFrequencies().indexOf(value));
			//Integer frequency = value.getValue().intValue();
			assertNotNull(actualFrequency);
			assertNotNull(actualFrequency.getKey());
			assertNotNull(actualFrequency.getValue());
			assertThat(actualFrequency.getKey(), equalTo(value.getKey()));
			assertThat(actualFrequency.getValue(), equalTo(value.getValue().intValue()));
		}
	}

	/**
	 * Test method for {@link com.app.java8test.processor.approach.nonjava8.NonJava8Frequency#taskExecution(java.io.File, boolean)}.
	 */
	@Test
	public void testTaskExecution() {
		testResultMapEntries(false);
	}
	
	/**
	 * Test method for {@link com.app.java8test.processor.approach.nonjava8.NonJava8Frequency#taskExecution(java.io.File, boolean)}.
	 */
	@Test
	public void testTaskExecutionParallel() {
		testResultMapEntries(true);
	}


}
