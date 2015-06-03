/**
 * 
 */
package com.ticketbooking.service.impl;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.ticketbooking.dao.model.TicketDao;
import com.ticketbooking.dao.model.UserAccountDao;
import com.ticketbooking.dao.model.UserDao;
import com.ticketbooking.model.User;
import com.ticketbooking.model.UserAccount;
import com.ticketbooking.service.UserService;

/**
 * UserService implementation
 *
 */
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LogManager.getLogger();
	
	private UserDao userDao;
	private TicketDao ticketDao;
	private UserAccountDao userAccountDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}
	
	public void setUserAccountDao(UserAccountDao userAccountDao) {
		this.userAccountDao = userAccountDao;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserService#getUserById(long)
	 */
	@Override
	public User getUserById(long id) {
		logger.info("Getting user by id: {}", id);
		return userDao.read(id);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserService#getUserByEmail(java.lang.String)
	 */
	@Override
	public User getUserByEmail(String email) {
		logger.info("Getting user by email: {}", email);
		return userDao.getUserByEmail(email);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserService#getUsersByName(java.lang.String, int, int)
	 */
	@Override
	public List<User> getUsersByName(String name, int pageSize, int pageNum) {
		logger.info("Getting users by name: {}", name);
		return getPageList(userDao.getUsersByName(name), pageNum, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserService#createUser(com.ticketbooking.model.User)
	 */
	@Override
	public User createUser(User user) {
		logger.info("Creating user: {}", user.getName());
		return userDao.create(user);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserService#updateUser(com.ticketbooking.model.User)
	 */
	@Override
	public User updateUser(User user) {
		logger.info("Updating user: {}", user.getName());
		return userDao.update(user);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserService#deleteUser(long)
	 */
	@Override
	public boolean deleteUser(long userId) {
		logger.info("Deleting user: {}", userId);
		User user = userDao.read(userId);
		if (user == null) {
			logger.error("User doesn't exists");
			return false;
		} else {
			ticketDao
				.getTicketsByUser(userId)
				.stream()
				.forEach(ticketDao::delete);
			UserAccount userAccount = userAccountDao.read(userId);
			if (userAccount != null) {
				userAccountDao.delete(userAccount);
			}
			return userDao.delete(user) == null ? false : true;
		}
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllSorted();
	}

}
