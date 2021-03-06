package com.ticketbooking.facade;

import com.ticketbooking.model.Event;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.User;
import com.ticketbooking.model.UserAccount;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Groups together all operations related to tickets booking.
 * Created by maksym_govorischev.
 */
/**
 * @author Mykola_Bazhenov
 *
 */
public interface BookingFacade {

    /**
     * Gets event by its id.
     * @return Event.
     */
    Event getEventById(long id);

    /**
     * Get list of events by matching title. Title is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     * @param title Event title or it's part.
     * @param pageSize Pagination param. Number of events to return on a page.
     * @param pageNum Pagination param. Number of the page to return. Starts from 1.
     * @return List of events.
     */
    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

    /**
     * Get list of events for specified day.
     * In case nothing was found, empty list is returned.
     * @param day Date object from which day information is extracted.
     * @param pageSize Pagination param. Number of events to return on a page.
     * @param pageNum Pagination param. Number of the page to return. Starts from 1.
     * @return List of events.
     */
    List<Event> getEventsForDay(Date day, int pageSize, int pageNum);

    /**
     * Creates new event. Event id should be auto-generated.
     * @param event Event data.
     * @return Created Event object.
     */
    Event createEvent(Event event);

    /**
     * Updates event using given data.
     * @param event Event data for update. Should have id set.
     * @return Updated Event object.
     */
    Event updateEvent(Event event);

    /**
     * Deletes event by its id.
     * @param eventId Event id.
     * @return Flag that shows whether event has been deleted.
     */
    boolean deleteEvent(long eventId);

    /**
     * Gets user by its id.
     * @return User.
     */
    User getUserById(long id);

    /**
     * Gets user by its email. Email is strictly matched.
     * @return User.
     */
    User getUserByEmail(String email);

    /**
     * Get list of users by matching name. Name is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     * @param name Users name or it's part.
     * @param pageSize Pagination param. Number of users to return on a page.
     * @param pageNum Pagination param. Number of the page to return. Starts from 1.
     * @return List of users.
     */
    List<User> getUsersByName(String name, int pageSize, int pageNum);

    /**
     * Creates new user. User id should be auto-generated.
     * @param user User data.
     * @return Created User object.
     */
    User createUser(User user);
    
    
    /**
     * Create new user with account in one transaction
     * 
     * @param user - user data
     * @param amount - initial amount
     * @return created user
     */
    User createUserWithAccount(User user, double amount);

    /**
     * Updates user using given data.
     * @param user User data for update. Should have id set.
     * @return Updated User object.
     */
    User updateUser(User user);

    /**
     * Deletes user by its id.
     * @param userId User id.
     * @return Flag that shows whether user has been deleted.
     */
    boolean deleteUser(long userId);

    /**
     * Book ticket for a specified event on behalf of specified user.
     * @param userId User Id.
     * @param eventId Event Id.
     * @param place Place number.
     * @param category Service category.
     * @return Booked ticket object.
     * @throws java.lang.IllegalStateException if this place has already been booked.
     */
    Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);

    /**
     * Get all booked tickets for specified user. Tickets should be sorted by event date in descending order. 
     * @param user User
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
     */
    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

    /**
     * Get all booked tickets for specified event. Tickets should be sorted in by user email in ascending order.
     * @param event Event
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
     */
    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

    /**
     * Cancel ticket with a specified id.
     * @param ticketId Ticket id.
     * @return Flag whether anything has been canceled.
     */
    boolean cancelTicket(long ticketId);
    
    /**
	 * Gets the user account by the user id
	 * 
	 * @param id - the user id
	 * @return - the user account
	 */
	UserAccount getUserAccountById(long id);
	
	/**
	 * Creates the user account
	 * 
	 * @param user - the user
	 * @param amount - the user amount value
	 * @return the user account entity
	 */
	UserAccount createUserAccount(User user, double amount);
	
	/**
	 * Refills the user account with a new value
	 * 
	 * @param user - the user
	 * @param delta - new amount value to be added to the existing one
	 * @return
	 */
	boolean refillUserAccount(User user, double delta);
	
	/**
	 * Deletes the user account
	 * 
	 * @param userAccountId - the user account is, equals to the user id
	 * @return Flag that shows whether user account has been deleted.
	 */
	boolean deleteUserAccount(long userAccountId);
	
	/**
	 * Loads the tickets from the predefined xml file
	 * 
	 * @return Flag that shows whether the loading has been successful
	 */
	boolean loadTicketBase(InputStream inputStream);
	
	/**
	 * Set the default user to singleton bean
	 * 
	 * @param user - the user to be set as default
	 */
	void setDefaultUser(User user);

	/**
	 * Set the default event to singleton bean
	 * 
	 * @param event - the event to be set as default
	 */
	void setDefaultEvent(Event event);
	
	/**
	 * Get all booked tickets for specified user. Checking the default user.
	 * 
     * @param user User
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
	 */
	List<Ticket> getBookedTicketsDefault(User user, int pageSize, int pageNum);
	
	/**
     * Get all booked tickets for specified event. Checking the default event
     * @param event Event
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
     */
	List<Ticket> getBookedTicketsDefault(Event event, int pageSize, int pageNum);
	
	List<User> getAllUsers();
	
	List<Event> getAllEvents();
	
	List<Ticket> getAllTickets();
}
