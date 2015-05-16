/**
 * 
 */
package com.ticketbooking.jdbc.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;

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
		PreparedStatementCreator psc = connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_USER, new String[] {"ID"});
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getEmail());
			return ps;
		};
		super.createEntityWithAutoIncrement(psc, entity);
		return entity;
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
		User user = read(entity.getId());
		int rows = jdbcTemplate.update(UPDATE_USER, entity.getName(), entity.getEmail(), entity.getId());
		return rows > 0 ? user : null;
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
		return jdbcTemplate.queryForObject(SELECT_USER_BY_EMAIL, new Object[] {email}, userMapper);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.model.UserDao#getUsersByName(java.lang.String)
	 */
	@Override
	public List<User> getUsersByName(String name) {
		return jdbcTemplate.query(SELECT_USERS_BY_NAME, new Object[] {'%' + name.trim() + '%'}, userMapper);
	}

}
