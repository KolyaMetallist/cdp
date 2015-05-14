/**
 * 
 */
package com.ticketbooking.jdbc.dao;

import java.util.List;

import com.ticketbooking.dao.model.TicketDao;
import com.ticketbooking.model.Ticket;

/**
 * @author Mykola_Bazhenov
 *
 */
public class JdbcTicketDao extends AbstractJdbcDao<Ticket> implements TicketDao {
	
	private static final String TICKET = "TICKET";

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#create(com.ticketbooking.model.Entity)
	 */
	@Override
	public Ticket create(Ticket entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#read(long)
	 */
	@Override
	public Ticket read(long id) {
		return jdbcTemplate.queryForObject(String.format(SELECT_STATEMENT, TICKET), new Object[] {id}, ticketMapper);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#update(com.ticketbooking.model.Entity)
	 */
	@Override
	public Ticket update(Ticket entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#delete(com.ticketbooking.model.Entity)
	 */
	@Override
	public Ticket delete(Ticket entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#getAll()
	 */
	@Override
	public List<Ticket> getAll() {
		return jdbcTemplate.query(String.format(SELECT_ALL_STATEMENT, TICKET), ticketMapper);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.model.TicketDao#getTicketsByUser(long)
	 */
	@Override
	public List<Ticket> getTicketsByUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.model.TicketDao#getTicketsByEvent(long)
	 */
	@Override
	public List<Ticket> getTicketsByEvent(long eventId) {
		// TODO Auto-generated method stub
		return null;
	}

}
