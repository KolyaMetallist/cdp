package com.app.java8test.processor.approach;

import com.app.java8test.main.ApproachCommandEnum;
import com.app.java8test.processor.approach.java8.Java8TaskFactory;
import com.app.java8test.processor.approach.nonjava8.NonJava8TaskFactory;

/**
 * This factory returns the AbstractApproachTaskFactory 
 * according to the input approach
 *
 */
public abstract class AbstractApproachFactory {
	
	public static AbstractApproachTaskFactory getInstance(ApproachCommandEnum approach) {
		switch (approach) {
		case Java8:
			return new Java8TaskFactory();
		case NonJava8:
			return new NonJava8TaskFactory();
		default:
			return null;
		}
	}

}
