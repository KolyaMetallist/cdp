/**
 * 
 */
package com.ticketbooking.model.impl;

import com.ticketbooking.model.Ticket;

/**
 * @author Mykola_Bazhenov
 *
 */
public class TicketImpl implements Ticket {
	
	private long id;
	private long eventId;
	private long userId;
	private Category category;
	private int place;

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Ticket#getId()
	 */
	@Override
	public long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Ticket#setId(long)
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Ticket#getEventId()
	 */
	@Override
	public long getEventId() {
		return eventId;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Ticket#setEventId(long)
	 */
	@Override
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Ticket#getUserId()
	 */
	@Override
	public long getUserId() {
		return userId;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Ticket#setUserId(long)
	 */
	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Ticket#getCategory()
	 */
	@Override
	public Category getCategory() {
		return category;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Ticket#setCategory(com.ticketbooking.model.Ticket.Category)
	 */
	@Override
	public void setCategory(Category category) {
		this.category = category;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Ticket#getPlace()
	 */
	@Override
	public int getPlace() {
		return place;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Ticket#setPlace(int)
	 */
	@Override
	public void setPlace(int place) {
		this.place = place;
	}

}
