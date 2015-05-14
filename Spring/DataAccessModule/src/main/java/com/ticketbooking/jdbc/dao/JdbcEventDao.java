/**
 * 
 */
package com.ticketbooking.jdbc.dao;

import java.util.Date;
import java.util.List;

import com.ticketbooking.dao.model.EventDao;
import com.ticketbooking.model.Event;

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
		// TODO Auto-generated method stub
		return null;
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
