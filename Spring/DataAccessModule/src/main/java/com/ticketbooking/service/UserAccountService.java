/**
 * 
 */
package com.ticketbooking.service;

import com.ticketbooking.model.User;
import com.ticketbooking.model.UserAccount;

/**
 * USer Account Service
 *
 */
public interface UserAccountService extends Service<UserAccount> {
	
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

}
