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
import com.ticketbooking.storage.Functions;

/**
 * @author Mykola_Bazhenov
 *
 */
public class JdbcEventDao extends AbstractJdbcDao<Event> implements EventDao {
	
	private static final String EVENT = "EVENT";

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#create(com.ticketbooking.model.Entity)
	 */
	@Override
	public Event create(Event entity) {
		PreparedStatementCreator psc = connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_EVENT, new String[] {"id"});
			ps.setString(1, entity.getTitle());
			ps.setString(2, Functions.DATE_FORMAT_DB.format(entity.getDate()));
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
		return jdbcTemplate.queryForObject(String.format(SELECT_STATEMENT, EVENT), new Object[] {id}, eventMapper);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#update(com.ticketbooking.model.Entity)
	 */
	@Override
	public Event update(Event entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#delete(com.ticketbooking.model.Entity)
	 */
	@Override
	public Event delete(Event entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#getAll()
	 */
	@Override
	public List<Event> getAll() {
		return jdbcTemplate.query(String.format(SELECT_ALL_STATEMENT, EVENT), eventMapper);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.model.EventDao#getEventsByTitle(java.lang.String)
	 */
	@Override
	public List<Event> getEventsByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.model.EventDao#getEventsForDay(java.util.Date)
	 */
	@Override
	public List<Event> getEventsForDay(Date day) {
		// TODO Auto-generated method stub
		return null;
	}

}
