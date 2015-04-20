/**
 * 
 */
package com.ticketbooking.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ticketbooking.facade.BookingFacade;

/**
 * Test performing some real-life scenario
 *
 */
public class IntegrationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		BookingFacade facade = context.getBean("bookingFacade", BookingFacade.class);
		context.registerShutdownHook();
	}

}
