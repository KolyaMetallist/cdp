/**
 * 
 */
package com.ticketbooking.model.impl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.ticketbooking.model.Ticket;

/**
 * @author Mykola_Bazhenov
 *
 */
@XmlRootElement(name = "ticket")
public class TicketImpl extends AbstractEntity implements Ticket {
	
	private long eventId;
	private long userId;
	private Category category;
	private int place;

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Ticket#getEventId()
	 */
	@Override
	@XmlAttribute(name = "event")
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
	@XmlAttribute(name = "user")
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
	@XmlAttribute(name = "category")
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
	@XmlAttribute(name = "place")
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + (int) (eventId ^ (eventId >>> 32));
		result = prime * result + place;
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof TicketImpl)) {
			return false;
		}
		TicketImpl other = (TicketImpl) obj;
		if (category != other.category) {
			return false;
		}
		if (eventId != other.eventId) {
			return false;
		}
		if (place != other.place) {
			return false;
		}
		if (userId != other.userId) {
			return false;
		}
		return true;
	}
	
}
