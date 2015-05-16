/**
 * 
 */
package com.ticketbooking.app;

import java.text.ParseException;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

import com.ticketbooking.common.Functions;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.User;
import com.ticketbooking.model.UserAccount;
import com.ticketbooking.model.impl.EventImpl;
import com.ticketbooking.model.impl.UserAccountImpl;
import com.ticketbooking.model.impl.UserImpl;

/**
 * Abstract Test Class
 *
 */
public abstract class AbstractTest {

	public static final String USER_NAME = "Max";
	public static final String EVENT_NEW_TITLE = "New title";
	public static final String EVENT_FULL_NAME = "Concert Toto";
	public static final String EVENT_DATE = "23.04.2015";
	public static final String USER_FULL_NAME = "Max Sukachev";
	public static final String USER_EMAIL = "max_sukachev@metallist.ua";
	public static final String USER_NEW_NAME = "New Name";
	public static final long USER_ID = 123l;
	public static final long EVENT_ID = 45l;
	public static final long TICKET_ID = 23l;
	public static final int PLACE = 234;
	public static final String EVENT_NAME = "Concert";
	public static final double USER_ACCOUNT_AMOUNT = 100.56;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		 MockitoAnnotations.initMocks(this);
	}
	
	public User buildUser() {
		User user = new UserImpl();
		user.setName(USER_FULL_NAME);
		user.setEmail(USER_EMAIL);
		return user;
	}
	
	public Event buildEvent() throws ParseException {
		Event event = new EventImpl();
		event.setTitle(EVENT_FULL_NAME);
		event.setDate(Functions.DATE_FORMAT.parse(EVENT_DATE));
		return event;
	}
	
	public User buildCloneUser(User user) {
		User clone = new UserImpl();
		clone.setId(user.getId());
		clone.setName(user.getName());
		clone.setEmail(user.getEmail());
		return clone;
	}
	
	public Event buildCloneEvent(Event event) {
		Event clone = new EventImpl();
		clone.setId(event.getId());
		clone.setDate(event.getDate());
		clone.setTitle(clone.getTitle());
		return clone;
	}
	
	public UserAccount buildUserAccount(User user) {
		UserAccount userAccount = new UserAccountImpl();
		userAccount.setAmount(USER_ACCOUNT_AMOUNT);
		return userAccount;
	}

}
