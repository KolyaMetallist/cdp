package com.ticketbooking.model.holder;

import com.ticketbooking.model.Event;
import com.ticketbooking.model.User;

public class DefaultHolder {
	
	private User defaultUser;
	
	private Event defaultEvent;

	public User getDefaultUser() {
		return defaultUser;
	}

	public void setDefaultUser(User defaultUser) {
		this.defaultUser = defaultUser;
	}

	public Event getDefaultEvent() {
		return defaultEvent;
	}

	public void setDefaultEvent(Event defaultEvent) {
		this.defaultEvent = defaultEvent;
	}

}
