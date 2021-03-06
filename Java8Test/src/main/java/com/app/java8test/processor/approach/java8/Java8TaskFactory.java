package com.app.java8test.processor.approach.java8;

import com.app.java8test.main.ProcessCommandEnum;
import com.app.java8test.processor.approach.AbstractApproachTaskFactory;
import com.app.java8test.processor.approach.Approach;

/**
 * This factory provides the instance of Approach implementation
 * related to Java 8 approach according to the input task option
 *
 */
public class Java8TaskFactory extends AbstractApproachTaskFactory {

	@Override
	public Approach getApproachTask(ProcessCommandEnum task) {
		switch (task) {
		case frequency:
			return new Java8Frequency();
		case length:
			return new Java8Length();
		case duplicates:
			return new Java8Duplicates();
		default:
			return null;
		}
	}

}
