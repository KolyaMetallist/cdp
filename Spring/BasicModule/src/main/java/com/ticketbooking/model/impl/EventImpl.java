/**
 * 
 */
package com.ticketbooking.model.impl;

import java.util.Date;

import com.ticketbooking.model.Event;

/**
 * Implementation for Event
 *
 */
public class EventImpl implements Event {
	
	private long id;
	private String title;
	private Date date;

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Event#getId()
	 */
	@Override
	public long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Event#setId(long)
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Event#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Event#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Event#getDate()
	 */
	@Override
	public Date getDate() {
		return date;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Event#setDate(java.util.Date)
	 */
	@Override
	public void setDate(Date date) {
		this.date = date;
	}

}
