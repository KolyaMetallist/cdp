/**
 * 
 */
package com.ticketbooking.service.impl;

import java.util.List;

import com.ticketbooking.dao.model.TicketDao;
import com.ticketbooking.dao.model.UserDao;
import com.ticketbooking.model.User;
import com.ticketbooking.service.UserService;

/**
 * UserService implementation
 *
 */
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	private TicketDao ticketDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserService#getUserById(long)
	 */
	@Override
	public User getUserById(long id) {
		return userDao.read(id);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserService#getUserByEmail(java.lang.String)
	 */
	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserService#getUsersByName(java.lang.String, int, int)
	 */
	@Override
	public List<User> getUsersByName(String name, int pageSize, int pageNum) {
		return getPageList(userDao.getUsersByName(name), pageNum, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserService#createUser(com.ticketbooking.model.User)
	 */
	@Override
	public User createUser(User user) {
		return userDao.create(user);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserService#updateUser(com.ticketbooking.model.User)
	 */
	@Override
	public User updateUser(User user) {
		return userDao.update(user);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.UserService#deleteUser(long)
	 */
	@Override
	public boolean deleteUser(long userId) {
		User user = userDao.read(userId);
		if (user == null) {
			return false;
		} else {
			ticketDao
				.getTicketsByUser(userId)
				.stream()
				.forEach(ticketDao::delete);
			return userDao.delete(user) == null ? false : true;
		}
	}

}
