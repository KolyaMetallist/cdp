/**
 * 
 */
package com.ticketbooking.service.impl;

import java.util.List;

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
		Ticket ticket = new TicketImpl();
		ticket.setUserId(userId);
		ticket.setEventId(eventId);
		ticket.setPlace(place);
		ticket.setCategory(category);
		return ticketDao.create(ticket);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.TicketService#getBookedTickets(com.ticketbooking.model.User, int, int)
	 */
	@Override
	public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
		return getPageList(ticketDao.getTicketsByUser(user.getId()), pageSize, pageNum);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.TicketService#getBookedTickets(com.ticketbooking.model.Event, int, int)
	 */
	@Override
	public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
		return getPageList(ticketDao.getTicketsByEvent(event.getId()), pageSize, pageNum);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.TicketService#cancelTicket(long)
	 */
	@Override
	public boolean cancelTicket(long ticketId) {
		Ticket ticket = ticketDao.read(ticketId);
		if (ticket == null) {
			return false;
		} else {
			return ticketDao.delete(ticket) == null ? false : true;
		}
	}

}
