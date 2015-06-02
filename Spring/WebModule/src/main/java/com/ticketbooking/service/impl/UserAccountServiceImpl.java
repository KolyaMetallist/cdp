/**
 * 
 */
package com.ticketbooking.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ticketbooking.dao.model.UserAccountDao;
import com.ticketbooking.model.User;
import com.ticketbooking.model.UserAccount;
import com.ticketbooking.model.impl.UserAccountImpl;
import com.ticketbooking.service.UserAccountService;

/**
 * UserAccountService implementation
 *
 */
public class UserAccountServiceImpl implements UserAccountService {
	
	private static final Logger logger = LogManager.getLogger();
	
	private UserAccountDao userAccountDao;
	
	public void setUserAccountDao(UserAccountDao userAccountDao){
		this.userAccountDao = userAccountDao;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserAccountService#getUserAccountById(long)
	 */
	@Override
	public UserAccount getUserAccountById(long id) {
		logger.info("Getting userAccount by id: {}", id);
		return userAccountDao.read(id);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserAccountService#createUserAccount(com.ticketbooking.model.User, double)
	 */
	@Override
	public UserAccount createUserAccount(User user, double amount) {
		logger.info("Creating userAccount for the user: {}", user.getName());
		UserAccount userAccount = new UserAccountImpl();
		userAccount.setId(user.getId());
		userAccount.setAmount(amount);
		userAccountDao.create(userAccount);
		return userAccount;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserAccountService#refillUserAccount(com.ticketbooking.model.User, double)
	 */
	@Override
	public boolean refillUserAccount(User user, double delta) {
		logger.info("Refilling the user account for the user {}", user.getName());
		UserAccount userAccount = userAccountDao.read(user.getId());
		if (userAccount == null) {
			logger.error("User Account for user {} doesn't exist", user.getName());
			return false;
		} else {
			userAccount.setAmount(userAccount.getAmount() + delta);
			return userAccountDao.update(userAccount) == null ? false : true;
		}
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserAccountService#deleteUserAccount(long)
	 */
	@Override
	public boolean deleteUserAccount(long userAccountId) {
		logger.info("Deleting the user account with the id {}", userAccountId);
		UserAccount userAccount = userAccountDao.read(userAccountId);
		if (userAccount == null) {
			logger.error("User Account doesn't exists");
			return false;
		} else {
			return userAccountDao.delete(userAccount) == null ? false : true;
		}
	}

}
