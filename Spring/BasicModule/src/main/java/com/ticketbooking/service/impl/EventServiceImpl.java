/**
 * 
 */
package com.ticketbooking.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ticketbooking.dao.model.EventDao;
import com.ticketbooking.dao.model.TicketDao;
import com.ticketbooking.model.Event;
import com.ticketbooking.service.EventService;
import com.ticketbooking.storage.Functions;

/**
 * EventService implementation
 *
 */
public class EventServiceImpl implements EventService {
	
	private static final Logger logger = LogManager.getLogger();
	
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
		logger.info("Getting user by id: {}", id);
		return eventDao.read(id);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.EventService#getEventsByTitle(java.lang.String, int, int)
	 */
	@Override
	public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
		logger.info("Getting user by title: {}", title);
		return getPageList(eventDao.getEventsByTitle(title), pageNum, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.EventService#getEventsForDay(java.util.Date, int, int)
	 */
	@Override
	public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
		logger.info("Getting user by title: {}", Functions.DATE_FORMAT.format(day));
		return getPageList(eventDao.getEventsForDay(day), pageNum, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.EventService#createEvent(com.ticketbooking.model.Event)
	 */
	@Override
	public Event createEvent(Event event) {
		logger.info("Creating event: {}", event.getTitle());
		return eventDao.create(event);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.EventService#updateEvent(com.ticketbooking.model.Event)
	 */
	@Override
	public Event updateEvent(Event event) {
		logger.info("Updating event: {}", event.getTitle());
		return eventDao.update(event);
	}

	@Override
	public boolean deleteEvent(long eventId) {
		logger.info("Deleting user: {}", eventId);
		Event event = eventDao.read(eventId);
		if (event == null) {
			logger.error("Event doesn't exists");
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
