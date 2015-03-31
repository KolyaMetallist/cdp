package com.app.java8test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.app.java8test.processor.approach.java8.Java8ApproachTest;

/**
 * Test suite for all test classes
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	Java8ApproachTest.class
})
public class UnitTestsSuite {

}
