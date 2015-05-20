/**
 * 
 */
package com.ticketbooking.app;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
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

import com.ticketbooking.common.Functions;
import com.ticketbooking.facade.BookingFacade;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.User;
import com.ticketbooking.model.UserAccount;

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
		
		UserAccount userAccount = bookingFacade.createUserAccount(user, USER_ACCOUNT_AMOUNT);
		assertNotNull(bookingFacade.getUserAccountById(userAccount.getId()));
		
		Event event = buildEvent();
		bookingFacade.createEvent(event);
		assertNotNull(bookingFacade.getEventById(event.getId()));
		assertThat(event.getId(), not(0));
		
		Ticket ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 234, Ticket.Category.PREMIUM);
		assertThat(bookingFacade.getBookedTickets(user, 10, 1), hasItem(ticket));
		assertThat(bookingFacade.getBookedTickets(event, 10, 1), hasItem(ticket));
		assertThat(ticket.getId(), not(0));
		assertTrue(bookingFacade.getUserAccountById(user.getId()).getAmount() < USER_ACCOUNT_AMOUNT );
		
		boolean isTicketCancelled = bookingFacade.cancelTicket(ticket.getId());
		assertTrue(isTicketCancelled);
		assertFalse(bookingFacade.getBookedTickets(event, 10, 1).contains(ticket));
		assertFalse(bookingFacade.getBookedTickets(user, 10, 1).contains(ticket));
		assertThat(bookingFacade.getUserAccountById(user.getId()).getAmount(), equalTo(USER_ACCOUNT_AMOUNT));
		
		// book the 1st ticket
		ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 1, Ticket.Category.PREMIUM);
		//book the 2nd
		ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 2, Ticket.Category.PREMIUM);
		assertNull(ticket);
	}
	
	@Test
	public void testUserServices() throws ParseException {
		User user = buildUser();
		bookingFacade.createUser(user);
		bookingFacade.createUserAccount(user, USER_ACCOUNT_AMOUNT);
		
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
		assertTrue(bookingFacade.deleteUser(user.getId()));
		assertNull(bookingFacade.getUserById(user.getId()));
		assertFalse(bookingFacade.getBookedTickets(user, 10, 1).contains(ticket));
		assertNull(bookingFacade.getUserAccountById(user.getId()));
	}
	
	@Test
	public void testEventServices() throws ParseException {
		Event event = buildEvent();
		bookingFacade.createEvent(event);
		
		assertThat(bookingFacade.getEventsByTitle(AbstractTest.EVENT_NAME, 10, 1), hasItem(event));
		assertThat(bookingFacade.getEventsForDay(Functions.DATE_FORMAT.parse(EVENT_DATE), 10, 1), hasItem(event));
		
		Event eventToUpdate = buildCloneEvent(event);
		eventToUpdate.setTitle(EVENT_NEW_TITLE);
		Event oldEvent = bookingFacade.updateEvent(eventToUpdate);
		assertThat(oldEvent.getTitle(), not(EVENT_NEW_TITLE));
		assertThat(bookingFacade.getEventById(event.getId()).getTitle(), equalTo(EVENT_NEW_TITLE));
		
		User user = buildUser();
		bookingFacade.createUser(user);
		bookingFacade.createUserAccount(user, USER_ACCOUNT_AMOUNT);
		Ticket ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 234, Ticket.Category.PREMIUM);
		assertTrue(bookingFacade.deleteEvent(event.getId()));
		assertNull(bookingFacade.getEventById(event.getId()));
		assertFalse(bookingFacade.getBookedTickets(event, 10, 1).contains(ticket));
	}
	
	@Test
	public void testUserAccountServices() {
		User user = buildUser();
		bookingFacade.createUser(user);
		
		UserAccount userAccount = bookingFacade.createUserAccount(user, USER_ACCOUNT_AMOUNT);
		assertEquals(user.getId(), userAccount.getId());
		
		assertTrue(bookingFacade.refillUserAccount(user, DELTA));
		userAccount = bookingFacade.getUserAccountById(user.getId());
		assertThat(userAccount.getAmount(), equalTo(USER_ACCOUNT_AMOUNT + DELTA));
		
		assertTrue(bookingFacade.deleteUserAccount(userAccount.getId()));
		assertNull(bookingFacade.getUserAccountById(user.getId()));
	}
	
	@Test
	public void testOXMProcess() {
		bookingFacade.loadTicketBase();

		User user = bookingFacade.getUserById(2);
		Event event = bookingFacade.getEventById(2);
		
		assertThat(bookingFacade.getBookedTickets(user, 10, 1).size(), equalTo(7));
		assertThat(bookingFacade.getBookedTickets(event, 10, 1).size(), equalTo(7));
	}
	
	@After
	public final void tearDown() {
		context.registerShutdownHook();
	}

}
