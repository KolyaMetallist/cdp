package com.ticketbooking.dao.model;

import java.util.List;

import com.ticketbooking.dao.Dao;
import com.ticketbooking.model.Ticket;

/**
 * Dao object for the entity Ticket
 *
 */
public interface TicketDao extends Dao<Ticket> {
	
	/**
	 * Get tickets booked be the user id
	 * 
	 * @return
	 */
	List<Ticket> getTicketsByUser(long userId);

}
