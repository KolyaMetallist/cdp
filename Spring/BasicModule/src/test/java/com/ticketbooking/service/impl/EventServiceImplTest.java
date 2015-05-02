/**
 * 
 */
package com.ticketbooking.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ticketbooking.app.AbstractTest;
import com.ticketbooking.dao.model.EventDao;
import com.ticketbooking.dao.model.TicketDao;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.impl.TicketImpl;
import com.ticketbooking.storage.Functions;

/**
 * Test for EventServiceImpl
 *
 */
public class EventServiceImplTest extends AbstractTest {
	
	@InjectMocks
	private EventServiceImpl eventService;
	@Mock
	private EventDao eventDao;
	@Mock
	private TicketDao ticketDao;
	
	private Event event;
	private Event updatedEvent;
	private Ticket ticket1;
	private Ticket ticket2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		event = buildEvent();
		updatedEvent = buildCloneEvent(event);
		ticket1 = new TicketImpl();
		ticket2 = new TicketImpl();
		when(eventDao.read(EVENT_ID)).thenReturn(event);
		when(eventDao.getEventsByTitle(EVENT_NAME)).thenReturn(Arrays.asList(event));
		when(eventDao.getEventsForDay(Functions.DATE_FORMAT.parse(EVENT_DATE))).thenReturn(Arrays.asList(event));
		when(eventDao.create(event)).thenAnswer(invocation -> 
			{ Event event = (Event) invocation.getArguments()[0];
			  event.setId(EVENT_ID);
			  return null;});
		when(eventDao.update(event)).thenReturn(updatedEvent);
		when(eventDao.delete(event)).thenReturn(event);
		when(ticketDao.getTicketsByEvent(EVENT_ID)).thenReturn(Arrays.asList(ticket1, ticket2));
		when(ticketDao.delete(any(Ticket.class))).thenReturn(any(Ticket.class));
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.EventServiceImpl#getEventById(long)}.
	 */
	@Test
	public void testGetEventById() {
		assertThat(eventService.getEventById(EVENT_ID), equalTo(event));
		verify(eventDao, times(1)).read(EVENT_ID);
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.EventServiceImpl#getEventsByTitle(java.lang.String, int, int)}.
	 */
	@Test
	public void testGetEventsByTitle() {
		assertThat(eventService.getEventsByTitle(EVENT_NAME, 10, 1), hasItem(event));
		verify(eventDao, times(1)).getEventsByTitle(EVENT_NAME);
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.EventServiceImpl#getEventsForDay(java.util.Date, int, int)}.
	 * @throws ParseException 
	 */
	@Test
	public void testGetEventsForDay() throws ParseException {
		assertThat(eventService.getEventsForDay(Functions.DATE_FORMAT.parse(EVENT_DATE), 10, 1), hasItem(event));
		verify(eventDao, times(1)).getEventsForDay(Functions.DATE_FORMAT.parse(EVENT_DATE));
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.EventServiceImpl#createEvent(com.ticketbooking.model.Event)}.
	 */
	@Test
	public void testCreateEvent() {
		assertThat(event.getId(), not(EVENT_ID));
		assertNull(eventService.createEvent(event));
		assertThat(event.getId(), equalTo(EVENT_ID));
		verify(eventDao, times(1)).create(event);
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.EventServiceImpl#updateEvent(com.ticketbooking.model.Event)}.
	 */
	@Test
	public void testUpdateEvent() {
		assertThat(eventService.updateEvent(event), equalTo(updatedEvent));
		verify(eventDao, times(1)).update(event);
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.EventServiceImpl#deleteEvent(long)}.
	 */
	@Test
	public void testDeleteEvent() {
		assertTrue(eventService.deleteEvent(EVENT_ID));
		verify(eventDao, times(1)).delete(event);
		verify(ticketDao, times(1)).getTicketsByEvent(EVENT_ID);
		verify(ticketDao, times(2)).delete(any(Ticket.class));
	}

}
