/**
 * 
 */
package com.ticketbooking.jdbc.dao;

import java.util.List;

import com.ticketbooking.dao.model.UserDao;
import com.ticketbooking.model.User;

/**
 * @author Mykola_Bazhenov
 *
 */
public class JdbcUserDao extends AbstractJdbcDao<User> implements UserDao {
	
	private static final String USER = "USER";

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#create(com.ticketbooking.model.Entity)
	 */
	@Override
	public User create(User entity) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#read(long)
	 */
	@Override
	public User read(long id) {
		return jdbcTemplate.queryForObject(String.format(SELECT_STATEMENT, USER), new Object[] {id}, userMapper);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#update(com.ticketbooking.model.Entity)
	 */
	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#delete(com.ticketbooking.model.Entity)
	 */
	@Override
	public User delete(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#getAll()
	 */
	@Override
	public List<User> getAll() {
		return jdbcTemplate.query(String.format(SELECT_ALL_STATEMENT, USER), userMapper);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.model.UserDao#getUserByEmail(java.lang.String)
	 */
	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.model.UserDao#getUsersByName(java.lang.String)
	 */
	@Override
	public List<User> getUsersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
