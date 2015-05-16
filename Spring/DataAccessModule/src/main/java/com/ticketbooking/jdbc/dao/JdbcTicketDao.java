/**
 * 
 */
package com.ticketbooking.jdbc.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;

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
		PreparedStatementCreator psc = connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_TICKET, new String[] {"ID"});
			ps.setLong(1, entity.getEventId());
			ps.setLong(2, entity.getUserId());
			ps.setString(3, entity.getCategory().name());
			ps.setInt(4, entity.getPlace());
			return ps;
		};
		super.createEntityWithAutoIncrement(psc, entity);
		return entity;
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
		Ticket ticket = read(entity.getId());
		int rows = jdbcTemplate.update(UPDATE_TICKET, entity.getEventId(), entity.getUserId(), entity.getCategory(), entity.getPlace(), entity.getId());
		return rows > 0 ? ticket : null;
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
		return jdbcTemplate.query(SELECT_TICKET_BY_USER, new Object[] {userId}, ticketMapper);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.model.TicketDao#getTicketsByEvent(long)
	 */
	@Override
	public List<Ticket> getTicketsByEvent(long eventId) {
		return jdbcTemplate.query(SELECT_TICKET_BY_EVENT, new Object[] {eventId}, ticketMapper);
	}

}
