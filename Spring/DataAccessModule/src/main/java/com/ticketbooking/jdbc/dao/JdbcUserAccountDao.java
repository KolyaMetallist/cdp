/**
 * 
 */
package com.ticketbooking.jdbc.dao;

import java.util.List;

import com.ticketbooking.dao.model.UserAccountDao;
import com.ticketbooking.model.UserAccount;
import com.ticketbooking.model.impl.UserAccountImpl;

/**
 * @author Mykola_Bazhenov
 *
 */
public class JdbcUserAccountDao extends AbstractJdbcDao<UserAccount> implements
		UserAccountDao {

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#create(com.ticketbooking.model.Entity)
	 */
	@Override
	public UserAccount create(UserAccount entity) {
		jdbcTemplate.update(INSERT_USER_ACCOUNT, entity.getId(), entity.getAmount());
		return entity;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#read(long)
	 */
	@Override
	public UserAccount read(long id) {
		return super.read(new UserAccountImpl(), id, userAccountMapper);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#update(com.ticketbooking.model.Entity)
	 */
	@Override
	public UserAccount update(UserAccount entity) {
		UserAccount userAccount = read(entity.getId());
		int rows = jdbcTemplate.update(UPDATE_USER_ACCOUNT, entity.getAmount(), entity.getId());
		return rows > 0 ? userAccount : null;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#getAll()
	 */
	@Override
	public List<UserAccount> getAll() {
		return super.getAll(new UserAccountImpl(), userAccountMapper);
	}

}
