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
public class EventImpl extends AbstractEntity implements Event {
	
	private String title;
	private Date date;
	private double ticketPrice;

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
	public double getTicketPrice() {
		return ticketPrice;
	}

	@Override
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventImpl [title=");
		builder.append(title);
		builder.append(", date=");
		builder.append(date);
		builder.append(", ticketPrice=");
		builder.append(ticketPrice);
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
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ticketPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (!(obj instanceof EventImpl)) {
			return false;
		}
		EventImpl other = (EventImpl) obj;
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (Double.doubleToLongBits(ticketPrice) != Double
				.doubleToLongBits(other.ticketPrice)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}
	
}
