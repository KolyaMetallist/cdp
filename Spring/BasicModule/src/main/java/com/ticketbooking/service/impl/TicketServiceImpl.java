/**
 * 
 */
package com.ticketbooking.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ticketbooking.dao.model.TicketDao;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.Ticket.Category;
import com.ticketbooking.model.User;
import com.ticketbooking.model.impl.TicketImpl;
import com.ticketbooking.service.TicketService;

/**
 * TicketService implementation
 *
 */
public class TicketServiceImpl implements TicketService {
	
	private static final Logger logger = LogManager.getLogger();
	
	private TicketDao ticketDao;
	
	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.TicketService#bookTicket(long, long, int, com.ticketbooking.model.Ticket.Category)
	 */
	@Override
	public Ticket bookTicket(long userId, long eventId, int place,
			Category category) {
		logger.info("Booking ticket: userId {}, eventId {}, place {}, cat {}",
				userId, eventId, place, category);	
		Ticket ticket = new TicketImpl();
		ticket.setUserId(userId);
		ticket.setEventId(eventId);
		ticket.setPlace(place);
		ticket.setCategory(category);
		ticketDao.create(ticket);
		return ticket;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.TicketService#getBookedTickets(com.ticketbooking.model.User, int, int)
	 */
	@Override
	public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
		logger.info("Getting tickets by user {}", user.getName());
		return getPageList(ticketDao.getTicketsByUser(user.getId()), pageNum, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.TicketService#getBookedTickets(com.ticketbooking.model.Event, int, int)
	 */
	@Override
	public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
		logger.info("Getting tickets by user {}", event.getTitle());
		return getPageList(ticketDao.getTicketsByEvent(event.getId()), pageNum, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.TicketService#cancelTicket(long)
	 */
	@Override
	public boolean cancelTicket(long ticketId) {
		logger.info("Deleting ticket {}", ticketId);
		Ticket ticket = ticketDao.read(ticketId);
		if (ticket == null) {
			logger.error("Ticket {} doesn't exist");
			return false;
		} else {
			return ticketDao.delete(ticket) == null ? false : true;
		}
	}

}
