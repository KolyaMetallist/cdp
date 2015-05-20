/**
 * 
 */
package com.ticketbooking.jdbc.dao;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.ticketbooking.dao.model.EventDao;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.impl.EventImpl;

/**
 * @author Mykola_Bazhenov
 *
 */
public class JdbcEventDao extends AbstractJdbcDao<Event> implements EventDao {

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#create(com.ticketbooking.model.Entity)
	 */
	@Override
	public Event create(Event entity) {
		PreparedStatementCreator psc = connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_EVENT, new String[] {"ID"});
			ps.setString(1, entity.getTitle());
			ps.setDate(2, new java.sql.Date(entity.getDate().getTime()));
			ps.setDouble(3, entity.getTicketPrice());
			return ps;
		};
		super.createEntityWithAutoIncrement(psc, entity);
		return entity;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#read(long)
	 */
	@Override
	public Event read(long id) {
		return super.read(new EventImpl(), id, eventMapper);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#update(com.ticketbooking.model.Entity)
	 */
	@Override
	public Event update(Event entity) {
		Event event = read(entity.getId());
		int rows = jdbcTemplate.update(UPDATE_EVENT, entity.getTitle(),  new java.sql.Date(entity.getDate().getTime()), entity.getTicketPrice(), entity.getId());
		return rows > 0 ? event : null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#getAll()
	 */
	@Override
	public List<Event> getAll() {
		return super.getAll(new EventImpl(), eventMapper);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.model.EventDao#getEventsByTitle(java.lang.String)
	 */
	@Override
	public List<Event> getEventsByTitle(String title) {
		return jdbcTemplate.query(SELECT_EVENT_BY_TITLE, new Object[] {'%' + title.trim() + '%'}, eventMapper);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.model.EventDao#getEventsForDay(java.util.Date)
	 */
	@Override
	public List<Event> getEventsForDay(Date day) {
		return jdbcTemplate.query(SELECT_EVENT_BY_DATE, new Object[] {new java.sql.Date(day.getTime())}, eventMapper);
	}

	@Override
	public int[] batchInsert(List<Event> entities) {
		throw new UnsupportedOperationException("batchInsert");
	}

}
