/**
 * 
 */
package com.ticketbooking.model.impl;

import com.ticketbooking.model.User;

/**
 * @author Mykola_Bazhenov
 *
 */
public class UserImpl extends AbstractEntity implements User {
	
	private String name;
	private String email;

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.User#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.User#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.User#getEmail()
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.User#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		this.email = email;
	}

}
