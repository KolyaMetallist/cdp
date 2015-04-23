/**
 * 
 */
package com.ticketbooking.model.impl;

import java.util.Date;

import com.ticketbooking.model.Event;
import com.ticketbooking.storage.Functions;

/**
 * Implementation for Event
 *
 */
public class EventImpl extends AbstractEntity implements Event {
	
	private String title;
	private Date date;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventImpl [title=");
		builder.append(title);
		builder.append(", date=");
		builder.append(Functions.DATE_FORMAT.format(date));
		builder.append(", getId()=");
		builder.append(getId());
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
