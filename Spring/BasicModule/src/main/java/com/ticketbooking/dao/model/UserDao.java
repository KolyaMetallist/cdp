/**
 * 
 */
package com.ticketbooking.dao.model;

import java.util.List;

import com.ticketbooking.dao.Dao;
import com.ticketbooking.model.User;

/**
 * Dao object for the entity User
 *
 */
public interface UserDao extends Dao<User> {
	
	/**
	 * Get user by its email address
	 * 
	 * @param email address
	 * @return the user
	 */
	User getUserByEmail(String email);
	
	/**
	 * Get the list of users by matching name fragment
	 * 
	 * @param name - user name
	 * @return the list of users
	 */
	List<User> getUsersByName(String name);

}
