/**
 * 
 */
package com.ticketbooking.storage.dao;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.ticketbooking.dao.model.TicketDao;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.impl.TicketImpl;

/**
 * @author kolya_metallist
 *
 */
public class StorageTicketDao extends AbstractStorageDao<Ticket> implements
		TicketDao {

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#read(long)
	 */
	@Override
	public Ticket read(long id) {
		return read(new TicketImpl(), id);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#getAll()
	 */
	@Override
	public List<Ticket> getAll() {
		return getStorage()
				.getValues()
				.stream()
				.filter(entity -> entity instanceof Ticket)
				.map(entity -> (Ticket) entity)
				.collect(toList());
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.model.TicketDao#getTicketsByUser(long)
	 */
	@Override
	public List<Ticket> getTicketsByUser(long userId) {
		return getAll()
				.stream()
				.filter(ticket -> ticket.getUserId() == userId)
				.collect(toList());
	}

}
