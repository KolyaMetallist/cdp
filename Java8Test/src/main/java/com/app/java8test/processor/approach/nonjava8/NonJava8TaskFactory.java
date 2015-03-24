package com.app.java8test.processor.approach.nonjava8;

import com.app.java8test.main.ProcessCommandEnum;
import com.app.java8test.processor.approach.AbstractApproachTaskFactory;
import com.app.java8test.processor.approach.Approach;
import com.app.java8test.processor.approach.java8.Java8Frequency;

public class NonJava8TaskFactory extends AbstractApproachTaskFactory {

	@Override
	public Approach getApproachTask(ProcessCommandEnum task) {
		switch (task) {
		case frequency:
			return new NonJava8Frequency();
		case length:
			return null;
		case duplicates:
			return null;
		default:
			return null;
		}
	}

}