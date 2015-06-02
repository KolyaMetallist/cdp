package com.ticketbooking.dao.model;

import java.util.List;

import com.ticketbooking.dao.Dao;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.User;

/**
 * Dao object for the entity Ticket
 *
 */
public interface TicketDao extends Dao<Ticket> {
	
	/**
	 * Get tickets booked by the user id
	 * 
	 * @param userId - user id
	 * @return the list of tickets
	 */
	List<Ticket> getTicketsByUser(long userId);
	
	/**
	 * Get tickets booked by the event id
	 * 
	 * @param eventId - event id
	 * @return the list of tickets
	 */
	List<Ticket> getTicketsByEvent(long eventId);
	
	List<Ticket> getTicketsByUserDefault(User user);
	
	List<Ticket> getTicketsByEventDefault(Event event);

}
