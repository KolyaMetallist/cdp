/**
 * 
 */
package com.ticketbooking.facade.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ticketbooking.facade.BookingFacade;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.Ticket.Category;
import com.ticketbooking.model.User;
import com.ticketbooking.model.UserAccount;
import com.ticketbooking.model.holder.DefaultHolder;
import com.ticketbooking.service.EventService;
import com.ticketbooking.service.TicketService;
import com.ticketbooking.service.UserAccountService;
import com.ticketbooking.service.UserService;

/**
 * BookingFacade implementation
 *
 */
public class BookingFacadeImpl implements BookingFacade {
	
	protected static final Logger logger = LogManager.getLogger();
	
	private UserService userService;
	private EventService eventService;
	private TicketService ticketService;
	private UserAccountService userAccountService;
	private DefaultHolder defaultHolder;
	
	public BookingFacadeImpl(UserService userService, EventService eventService, TicketService ticketService, UserAccountService userAccountService) {
		this.userService = userService;
		this.eventService = eventService;
		this.ticketService = ticketService;
		this.userAccountService = userAccountService;
	}
	
	public void setDefaultHolder(DefaultHolder defaultHolder) {
		this.defaultHolder = defaultHolder;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#getEventById(long)
	 */
	@Override
	public Event getEventById(long id) {
		return eventService.getEventById(id);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#getEventsByTitle(java.lang.String, int, int)
	 */
	@Override
	public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
		return eventService.getEventsByTitle(title, pageSize, pageNum);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#getEventsForDay(java.util.Date, int, int)
	 */
	@Override
	public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
		return eventService.getEventsForDay(day, pageSize, pageNum);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#createEvent(com.ticketbooking.model.Event)
	 */
	@Override
	public Event createEvent(Event event) {
		return eventService.createEvent(event);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#updateEvent(com.ticketbooking.model.Event)
	 */
	@Override
	public Event updateEvent(Event event) {
		return eventService.updateEvent(event);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#deleteEvent(long)
	 */
	@Override
	public boolean deleteEvent(long eventId) {
		return eventService.deleteEvent(eventId);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#getUserById(long)
	 */
	@Override
	public User getUserById(long id) {
		return userService.getUserById(id);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#getUserByEmail(java.lang.String)
	 */
	@Override
	public User getUserByEmail(String email) {
		return userService.getUserByEmail(email);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#getUsersByName(java.lang.String, int, int)
	 */
	@Override
	public List<User> getUsersByName(String name, int pageSize, int pageNum) {
		return userService.getUsersByName(name, pageSize, pageNum);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#createUser(com.ticketbooking.model.User)
	 */
	@Override
	public User createUser(User user) {
		return userService.createUser(user);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#updateUser(com.ticketbooking.model.User)
	 */
	@Override
	public User updateUser(User user) {
		return userService.updateUser(user);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#deleteUser(long)
	 */
	@Override
	public boolean deleteUser(long userId) {
		return userService.deleteUser(userId);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#bookTicket(long, long, int, com.ticketbooking.model.Ticket.Category)
	 */
	@Override
	public Ticket bookTicket(long userId, long eventId, int place,
			Category category) {
		Ticket ticket = null;
		try {
			ticket = ticketService.bookTicket(userId, eventId, place, category);
		} catch (Exception e) {
			logger.error(e);
		}
		return ticket;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#getBookedTickets(com.ticketbooking.model.User, int, int)
	 */
	@Override
	public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
		return ticketService.getBookedTickets(user, pageSize, pageNum);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#getBookedTickets(com.ticketbooking.model.Event, int, int)
	 */
	@Override
	public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
		return ticketService.getBookedTickets(event, pageSize, pageNum);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.facade.BookingFacade#cancelTicket(long)
	 */
	@Override
	public boolean cancelTicket(long ticketId) {
		return ticketService.cancelTicket(ticketId);
	}

	@Override
	public UserAccount getUserAccountById(long id) {
		return userAccountService.getUserAccountById(id);
	}

	@Override
	public UserAccount createUserAccount(User user, double amount) {
		return userAccountService.createUserAccount(user, amount);
	}

	@Override
	public boolean refillUserAccount(User user, double delta) {
		return userAccountService.refillUserAccount(user, delta);
	}

	@Override
	public boolean deleteUserAccount(long userAccountId) {
		return userAccountService.deleteUserAccount(userAccountId);
	}

	@Override
	public boolean loadTicketBase() {
		return ticketService.loadTicketBase();
	}

	@Override
	public void setDefaultUser(User user) {
		defaultHolder.setDefaultUser(user);
	}

	@Override
	public void setDefaultEvent(Event event) {
		defaultHolder.setDefaultEvent(event);
	}

	@Override
	public List<Ticket> getBookedTicketsDefault(User user, int pageSize,
			int pageNum) {
		return ticketService.getBookedTicketsDefault(user, pageSize, pageNum);
	}

	@Override
	public List<Ticket> getBookedTicketsDefault(Event event, int pageSize,
			int pageNum) {
		return ticketService.getBookedTicketsDefault(event, pageSize, pageNum);
	}

	@Override
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public User createUserWithAccount(User user, double amount) {
		userService.createUser(user);
		userAccountService.createUserAccount(user, amount);
		return user;
	}

	@Override
	public List<Event> getAllEvents() {
		return eventService.getAllEvents();
	}

	@Override
	public List<Ticket> getAllTickets() {
		return ticketService.getAllTickets();
	}

}
