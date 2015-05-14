/**
 * 
 */
package com.ticketbooking.jdbc.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ticketbooking.dao.Dao;
import com.ticketbooking.model.Entity;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.User;
import com.ticketbooking.model.UserAccount;
import com.ticketbooking.model.impl.UserImpl;
import com.ticketbooking.model.impl.EventImpl;
import com.ticketbooking.model.impl.TicketImpl;
import com.ticketbooking.model.impl.UserAccountImpl;

/**
 * Holds the common Dao operations, CRUD, link to database
 *
 */
public abstract class AbstractJdbcDao<E extends Entity> implements Dao<E> {
	
	protected static final RowMapper<User> userMapper = (rs, rowNum) -> {
		User user = new UserImpl();
		user.setId(rs.getLong("ID"));
		user.setName(rs.getString("NAME"));
		user.setEmail(rs.getString("EMAIL"));
		return user;
	};
	
	protected static final RowMapper<Event> eventMapper = (rs, rowNum) -> {
		Event event = new EventImpl();
		event.setId(rs.getLong("ID"));
		event.setTitle(rs.getString("TITLE"));
		event.setDate(rs.getDate("DATE"));
		event.setTicketPrice(rs.getDouble("TICKETPRICE"));
		return event;
	};
	
	protected static final RowMapper<Ticket> ticketMapper = (rs, rowNum) -> {
		Ticket ticket = new TicketImpl();
		ticket.setId(rs.getLong("ID"));
		ticket.setEventId(rs.getLong("EVENT_ID"));
		ticket.setUserId(rs.getLong("USER_ID"));
		ticket.setCategory(Ticket.Category.valueOf(rs.getString("CATEGORY")));
		ticket.setPlace(rs.getInt("PLACE"));
		return ticket;
	};
	
	protected static final RowMapper<UserAccount> userAccountMapper = (rs, rowNum) -> {
		UserAccount userAccount = new UserAccountImpl();
		userAccount.setId(rs.getLong("ID"));
		userAccount.setAmount(rs.getDouble("AMOUNT"));
		return userAccount;
	};
	
	protected static final String SELECT_STATEMENT = "SELECT * FROM %s WHERE ID = ?";
	protected static final String SELECT_ALL_STATEMENT = "SELECT * FROM %s";
	
	protected JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
