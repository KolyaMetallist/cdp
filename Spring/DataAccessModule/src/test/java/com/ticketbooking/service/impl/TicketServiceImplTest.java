/**
 * 
 */
package com.ticketbooking.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ticketbooking.app.AbstractTest;
import com.ticketbooking.dao.model.EventDao;
import com.ticketbooking.dao.model.TicketDao;
import com.ticketbooking.dao.model.UserAccountDao;
import com.ticketbooking.dao.model.UserDao;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.User;
import com.ticketbooking.model.impl.TicketImpl;

/**
 * 
 * Test fir TicketServiceImpl
 *
 */
public class TicketServiceImplTest extends AbstractTest {
	
	@InjectMocks
	private TicketServiceImpl ticketService;
	@Mock
	private TicketDao ticketDao;
	@Mock
	private UserAccountDao userAccountDao;
	@Mock
	private UserDao userDao;
	@Mock
	private EventDao eventDao;

	private User user;
	private Event event;
	private Ticket ticket;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		ticket = new TicketImpl();
		user = buildUser();
		event = buildEvent();
		when(ticketDao.create(any(Ticket.class))).thenAnswer(invocation ->
				{Ticket ticket = (Ticket) invocation.getArguments()[0];
				 ticket.setId(TICKET_ID);
				 return null;});
		
		when(ticketDao.getTicketsByEvent(EVENT_ID)).thenReturn(Arrays.asList(ticket));
		when(ticketDao.getTicketsByUser(USER_ID)).thenReturn(Arrays.asList(ticket));
		when(user.getId()).thenReturn(USER_ID);
		when(event.getId()).thenReturn(EVENT_ID);
		when(ticketDao.read(TICKET_ID)).thenReturn(ticket);
		when(ticketDao.delete(ticket)).thenReturn(ticket);
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.TicketServiceImpl#bookTicket(long, long, int, com.ticketbooking.model.Ticket.Category)}.
	 */
	@Test
	public void testBookTicketservice() {
		Ticket ticket = ticketService.bookTicket(USER_ID, EVENT_ID, PLACE, Ticket.Category.PREMIUM);
		assertNotNull(ticket);
		assertThat(ticket.getId(), equalTo(TICKET_ID));
		assertThat(ticket.getUserId(), equalTo(USER_ID));
		assertThat(ticket.getEventId(), equalTo(EVENT_ID));
		assertThat(ticket.getPlace(), equalTo(PLACE));
		assertThat(ticket.getCategory(), equalTo(Ticket.Category.PREMIUM));
		verify(ticketDao, times(1)).create(any(Ticket.class));
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.TicketServiceImpl#getBookedTickets(com.ticketbooking.model.User, int, int)}.
	 */
	@Test
	public void testGetBookedTicketsUserIntInt() {
		assertThat(ticketService.getBookedTickets(user, 10, 1), hasItem(ticket));
		verify(ticketDao, times(1)).getTicketsByUser(USER_ID);
		verify(user, times(1)).getId();
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.TicketServiceImpl#getBookedTickets(com.ticketbooking.model.Event, int, int)}.
	 */
	@Test
	public void testGetBookedTicketsEventIntInt() {
		assertThat(ticketService.getBookedTickets(event, 10, 1), hasItem(ticket));
		verify(ticketDao, times(1)).getTicketsByEvent(EVENT_ID);
		verify(event, times(1)).getId();
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.TicketServiceImpl#cancelTicket(long)}.
	 */
	@Test
	public void testCancelTicket() {
		assertTrue(ticketService.cancelTicket(TICKET_ID));
		verify(ticketDao, times(1)).delete(ticket);
	}

}
