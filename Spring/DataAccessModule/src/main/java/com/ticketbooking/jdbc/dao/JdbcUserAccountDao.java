/**
 * 
 */
package com.ticketbooking.jdbc.dao;

import java.util.List;

import com.ticketbooking.dao.model.UserAccountDao;
import com.ticketbooking.model.UserAccount;

/**
 * @author Mykola_Bazhenov
 *
 */
public class JdbcUserAccountDao extends AbstractJdbcDao<UserAccount> implements
		UserAccountDao {
	
	private static final String USER_ACCOUNT = "USER_ACCOUNT";

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#create(com.ticketbooking.model.Entity)
	 */
	@Override
	public UserAccount create(UserAccount entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#read(long)
	 */
	@Override
	public UserAccount read(long id) {
		return jdbcTemplate.queryForObject(String.format(SELECT_STATEMENT, USER_ACCOUNT), new Object[] {id}, userAccountMapper);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#update(com.ticketbooking.model.Entity)
	 */
	@Override
	public UserAccount update(UserAccount entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#delete(com.ticketbooking.model.Entity)
	 */
	@Override
	public UserAccount delete(UserAccount entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#getAll()
	 */
	@Override
	public List<UserAccount> getAll() {
		return jdbcTemplate.query(String.format(SELECT_ALL_STATEMENT, USER_ACCOUNT), userAccountMapper);
	}

}
