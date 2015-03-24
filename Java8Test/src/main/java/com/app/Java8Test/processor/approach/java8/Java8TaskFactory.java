package com.app.java8test.processor.approach.java8;

import com.app.java8test.main.ProcessCommandEnum;
import com.app.java8test.processor.approach.AbstractApproachTaskFactory;
import com.app.java8test.processor.approach.Approach;

public class Java8TaskFactory extends AbstractApproachTaskFactory {

	@Override
	public Approach getApproachTask(ProcessCommandEnum task) {
		switch (task) {
		case frequency:
			return new Java8Frequency();
		case length:
			return null;
		case duplicates:
			return null;
		default:
			return null;
		}
	}

}
