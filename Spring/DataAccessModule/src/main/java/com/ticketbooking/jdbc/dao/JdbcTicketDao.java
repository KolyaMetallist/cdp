/**
 * 
 */
package com.ticketbooking.jdbc.dao;

import static java.util.stream.Collectors.toList;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.ticketbooking.dao.model.TicketDao;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.impl.TicketImpl;

/**
 * @author Mykola_Bazhenov
 *
 */
public class JdbcTicketDao extends AbstractJdbcDao<Ticket> implements TicketDao {

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
		return super.read(new TicketImpl(), id, ticketMapper);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#update(com.ticketbooking.model.Entity)
	 */
	@Override
	public Ticket update(Ticket entity) {
		Ticket ticket = read(entity.getId());
		int rows = jdbcTemplate.update(UPDATE_TICKET, entity.getEventId(), entity.getUserId(), entity.getCategory().name(), entity.getPlace(), entity.getId());
		return rows > 0 ? ticket : null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#getAll()
	 */
	@Override
	public List<Ticket> getAll() {
		return super.getAll(new TicketImpl(), ticketMapper);
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

	@Override
	public int[] batchInsert(List<Ticket> entities) {
		List<Object[]> batch = entities
			 				.stream()
			 				.map(ticket -> new Object[] {ticket.getEventId(), 
			 											ticket.getUserId(), 
			 											ticket.getCategory().name(), 
			 											ticket.getPlace()})
			 				.collect(toList());
		return jdbcTemplate.batchUpdate(INSERT_TICKET, batch);
	}

}