/**
 * 
 */
package com.ticketbooking.storage.dao;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;

import com.ticketbooking.dao.model.EventDao;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.impl.EventImpl;

/**
 * @author kolya_metallist
 *
 */
public class StorageEventDao extends AbstractStorageDao<Event> implements
		EventDao {

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#read(long)
	 */
	@Override
	public Event read(long id) {
		return read(new EventImpl(), id);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#getAll()
	 */
	@Override
	public List<Event> getAll() {
		return getStorage()
				.getValues()
				.stream()
				.filter(entity -> entity instanceof Event)
				.map(entity -> (Event) entity)
				.collect(toList());
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.model.EventDao#getEventsByTitle(java.lang.String)
	 */
	@Override
	public List<Event> getEventsByTitle(String title) {
		return getAll()
				.stream()
				.filter(event -> event.getTitle().contains(title))
				.collect(toList());
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.model.EventDao#getEventsForDay(java.util.Date)
	 */
	@Override
	public List<Event> getEventsForDay(Date day) {
		return getAll()
				.stream()
				.filter(event -> event.getDate().compareTo(day) == 0)
				.collect(toList());
	}

}
