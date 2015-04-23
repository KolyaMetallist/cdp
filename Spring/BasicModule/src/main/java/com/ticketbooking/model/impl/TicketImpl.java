/**
 * 
 */
package com.ticketbooking.model.impl;

import com.ticketbooking.model.Ticket;

/**
 * @author Mykola_Bazhenov
 *
 */
public class TicketImpl extends AbstractEntity implements Ticket {
	
	private long eventId;
	private long userId;
	private Category category;
	private int place;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TicketImpl [eventId=");
		builder.append(eventId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", category=");
		builder.append(category);
		builder.append(", place=");
		builder.append(place);
		builder.append(", getId()=");
		builder.append(getId());
		builder.append("]");
		return builder.toString();
	}
	
	

}
