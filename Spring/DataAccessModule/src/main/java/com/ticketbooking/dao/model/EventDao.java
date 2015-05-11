/**
 * 
 */
package com.ticketbooking.dao.model;

import java.util.Date;
import java.util.List;

import com.ticketbooking.dao.Dao;
import com.ticketbooking.model.Event;

/**
 * Dao object for the entity Event
 *
 */
public interface EventDao extends Dao<Event> {
	
	/**
	 * Get the list of events by matching title
	 * 
	 * @param title - title of events 
	 * @return list of events
	 */
	List<Event> getEventsByTitle(String title);
	
	/**
	 * Get the list of events by the event day
	 * 
	 * @param day - event day
	 * @return the list of events
	 */
	List<Event> getEventsForDay(Date day);

}
