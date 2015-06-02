/**
 * 
 */
package com.ticketbooking.model.impl;

import com.ticketbooking.model.Entity;

/**
 * General implementation of entity
 *
 */
public abstract class AbstractEntity implements Entity {
	
	private long id;

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Entity#getId()
	 */
	@Override
	public long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.Entity#setId(long)
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AbstractEntity)) {
			return false;
		}
		AbstractEntity other = (AbstractEntity) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
	

}
