package com.app.java8test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.app.java8test.processor.approach.java8.Java8ApproachTest;
import com.app.java8test.processor.approach.java8.Java8DuplicatesTest;
import com.app.java8test.processor.approach.java8.Java8FrequencyTest;
import com.app.java8test.processor.approach.java8.Java8LengthTest;
import com.app.java8test.processor.approach.nonjava8.NonJava8ApproachTest;
import com.app.java8test.processor.approach.nonjava8.NonJava8DuplicatesTest;
import com.app.java8test.processor.approach.nonjava8.NonJava8FrequencyTest;
import com.app.java8test.processor.approach.nonjava8.NonJava8LengthTest;

/**
 * Test suite for all test classes
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	Java8ApproachTest.class,
	NonJava8ApproachTest.class,
	Java8DuplicatesTest.class,
	NonJava8DuplicatesTest.class,
	Java8FrequencyTest.class,
	NonJava8FrequencyTest.class,
	Java8LengthTest.class,
	NonJava8LengthTest.class
})
public class UnitTestsSuite {

}
