/**
 * 
 */
package com.ticketbooking.facade;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Integration test
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config.xml"})
public class BookingFacadeTest {
	
	@Autowired
	protected AbstractApplicationContext context;
	private BookingFacade bookingFacade;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		 bookingFacade = context.getBean("bookingFacade", BookingFacade.class);
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	@After
	public final void tearDown() {
		context.registerShutdownHook();
	}

}
