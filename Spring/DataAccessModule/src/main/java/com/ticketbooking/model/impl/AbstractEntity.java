/**
 * 
 */
package com.ticketbooking.model.impl;

import com.ticketbooking.model.Entity;

/**
 * General implementation of entity
 *
 */
public class AbstractEntity implements Entity {
	
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

}
