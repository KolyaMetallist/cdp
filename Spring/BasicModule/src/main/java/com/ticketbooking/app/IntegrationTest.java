/**
 * 
 */
package com.ticketbooking.app;

import java.text.ParseException;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ticketbooking.facade.BookingFacade;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.User;
import com.ticketbooking.model.impl.EventImpl;
import com.ticketbooking.model.impl.UserImpl;
import com.ticketbooking.storage.Functions;

/**
 * Test performing some real-life scenario
 *
 */
public class IntegrationTest {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		BookingFacade facade = context.getBean("bookingFacade", BookingFacade.class);
		
		User user = new UserImpl();
		user.setName("Max Sukachev");
		user.setEmail("max_sukachev@metallist.ua");
		
		facade.createUser(user);
		System.out.println("Created user: " + user);
		
		Event event = new EventImpl();
		event.setTitle("Concert Toto");
		event.setDate(Functions.DATE_FORMAT.parse("23.04.2015"));
		
		facade.createEvent(event);
		System.out.println("Created event: " + event);
		
		Ticket ticket = facade.bookTicket(user.getId(), event.getId(), 234, Ticket.Category.PREMIUM);
		System.out.println("Booked ticket: " + ticket);
		
		boolean isTicketCancelled = facade.cancelTicket(ticket.getId());
		System.out.println("Ticket with id " + ticket.getId() + " is cancelled: " + isTicketCancelled);
		
		List<User> listByName = facade.getUsersByName("Max", 10, 1);
		listByName.stream().forEach(System.out::println);
		
		User userByEmail = facade.getUserByEmail("max007@mail.ru");
		System.out.println("User by email: " + userByEmail);
		
		userByEmail.setEmail("newemail@gh.ty");
		facade.updateUser(userByEmail);
		User updatedUser = facade.getUserById(userByEmail.getId());
		System.out.println("Updated user: " + updatedUser);
		
		context.registerShutdownHook();
	}

}
