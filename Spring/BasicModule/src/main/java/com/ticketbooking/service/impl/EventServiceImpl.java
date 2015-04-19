/**
 * 
 */
package com.ticketbooking.service.impl;

import java.util.Date;
import java.util.List;

import com.ticketbooking.dao.model.EventDao;
import com.ticketbooking.dao.model.TicketDao;
import com.ticketbooking.model.Event;
import com.ticketbooking.service.EventService;

/**
 * EventService implementation
 *
 */
public class EventServiceImpl implements EventService {
	
	private EventDao eventDao;
	private TicketDao ticketDao;
	
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.EventService#getEventById(long)
	 */
	@Override
	public Event getEventById(long id) {
		return eventDao.read(id);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.EventService#getEventsByTitle(java.lang.String, int, int)
	 */
	@Override
	public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
		return getPageList(eventDao.getEventsByTitle(title), pageSize, pageNum);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.EventService#getEventsForDay(java.util.Date, int, int)
	 */
	@Override
	public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
		return getPageList(eventDao.getEventsForDay(day), pageSize, pageNum);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.EventService#createEvent(com.ticketbooking.model.Event)
	 */
	@Override
	public Event createEvent(Event event) {
		return eventDao.create(event);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.EventService#updateEvent(com.ticketbooking.model.Event)
	 */
	@Override
	public Event updateEvent(Event event) {
		return eventDao.update(event);
	}

	@Override
	public boolean deleteEvent(long eventId) {
		Event event = eventDao.read(eventId);
		if (event == null) {
			return false;
		} else {
			ticketDao
				.getTicketsByEvent(eventId)
				.stream()
				.forEach(ticketDao::delete);
			return eventDao.delete(event) == null ? false : true;
		}
	}

}
