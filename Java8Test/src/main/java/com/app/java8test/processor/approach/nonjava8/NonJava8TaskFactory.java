package com.app.java8test.processor.approach.nonjava8;

import com.app.java8test.main.ProcessCommandEnum;
import com.app.java8test.processor.approach.AbstractApproachTaskFactory;
import com.app.java8test.processor.approach.Approach;

/**
 * This factory provides the instance of Approach implementation
 * related to Java 8 approach according to the input task option
 *
 */
public class NonJava8TaskFactory extends AbstractApproachTaskFactory {

	@Override
	public Approach getApproachTask(ProcessCommandEnum task) {
		switch (task) {
		case frequency:
			return new NonJava8Frequency();
		case length:
			return new NonJava8Length();
		case duplicates:
			return new NonJava8Duplicates();
		default:
			return null;
		}
	}

}
