/**
 * 
 */
package com.ticketbooking.app;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ticketbooking.facade.BookingFacade;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.User;
import com.ticketbooking.storage.Functions;

/**
 * Integration test
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IntegrationTest extends AbstractTest{
	
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
	public void testBookingProcess() throws ParseException {
		User user = buildUser();
		bookingFacade.createUser(user);
		assertNotNull(bookingFacade.getUserById(user.getId()));
		assertThat(user.getId(), not(0));
		
		Event event = buildEvent();
		bookingFacade.createEvent(event);
		assertNotNull(bookingFacade.getEventById(event.getId()));
		assertThat(event.getId(), not(0));
		
		Ticket ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 234, Ticket.Category.PREMIUM);
		assertThat(bookingFacade.getBookedTickets(user, 10, 1), hasItem(ticket));
		assertThat(bookingFacade.getBookedTickets(event, 10, 1), hasItem(ticket));
		assertThat(ticket.getId(), not(0));
		
		boolean isTicketCancelled = bookingFacade.cancelTicket(ticket.getId());
		assertTrue(isTicketCancelled);
		assertFalse(bookingFacade.getBookedTickets(event, 10, 1).contains(ticket));
		assertFalse(bookingFacade.getBookedTickets(user, 10, 1).contains(ticket));
	}
	
	@Test
	public void testUserServices() throws ParseException {
		User user = buildUser();
		bookingFacade.createUser(user);
		
		assertThat(bookingFacade.getUsersByName(AbstractTest.USER_NAME, 10, 1), hasItem(user));
		assertThat(bookingFacade.getUserByEmail(USER_EMAIL), equalTo(user));
		
		User userToUpdate = buildCloneUser(user);
		userToUpdate.setName(USER_NEW_NAME);
		User oldUser = bookingFacade.updateUser(userToUpdate);
		assertThat(oldUser.getName(), not(USER_NEW_NAME));
		assertThat(bookingFacade.getUserById(user.getId()).getName(), equalTo(USER_NEW_NAME));
		
		Event event = buildEvent();
		bookingFacade.createEvent(event);
		Ticket ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 234, Ticket.Category.PREMIUM);
		bookingFacade.deleteUser(user.getId());
		assertNull(bookingFacade.getUserById(user.getId()));
		assertFalse(bookingFacade.getBookedTickets(user, 10, 1).contains(ticket));
	}
	
	@Test
	public void testEventServices() throws ParseException {
		Event event = buildEvent();
		bookingFacade.createEvent(event);
		
		assertThat(bookingFacade.getEventsByTitle("Concert", 10, 1), hasItem(event));
		assertThat(bookingFacade.getEventsForDay(Functions.DATE_FORMAT.parse(EVENT_DATE), 10, 1), hasItem(event));
		
		Event eventToUpdate = buildCloneEvent(event);
		eventToUpdate.setTitle(EVENT_NEW_TITLE);
		Event oldEvent = bookingFacade.updateEvent(eventToUpdate);
		assertThat(oldEvent.getTitle(), not(EVENT_NEW_TITLE));
		assertThat(bookingFacade.getEventById(event.getId()).getTitle(), equalTo(EVENT_NEW_TITLE));
		
		User user = buildUser();
		bookingFacade.createUser(user);
		Ticket ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 234, Ticket.Category.PREMIUM);
		bookingFacade.deleteEvent(event.getId());
		assertNull(bookingFacade.getEventById(event.getId()));
		assertFalse(bookingFacade.getBookedTickets(event, 10, 1).contains(ticket));
	}
	
	@After
	public final void tearDown() {
		context.registerShutdownHook();
	}

}
