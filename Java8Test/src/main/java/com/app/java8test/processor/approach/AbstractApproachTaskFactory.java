package com.app.java8test.processor.approach;

import com.app.java8test.main.ProcessCommandEnum;

abstract public class AbstractApproachTaskFactory {
	
	public abstract Approach getApproachTask(ProcessCommandEnum task);
	
}
