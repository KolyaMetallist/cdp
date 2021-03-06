/**
 * 
 */
package com.ticketbooking.jdbc.dao;

import static com.ticketbooking.common.Functions.getEntityName;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ticketbooking.dao.Dao;
import com.ticketbooking.model.Entity;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.User;
import com.ticketbooking.model.UserAccount;
import com.ticketbooking.model.impl.EventImpl;
import com.ticketbooking.model.impl.TicketImpl;
import com.ticketbooking.model.impl.UserAccountImpl;
import com.ticketbooking.model.impl.UserImpl;

/**
 * Holds the common Dao operations, CRUD, link to database
 *
 */
public abstract class AbstractJdbcDao<E extends Entity> implements Dao<E> {
	
	protected static final Logger logger = LogManager.getLogger();
	
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
	protected static final String SELECT_USER_BY_EMAIL = "SELECT * FROM USER WHERE EMAIL = ?";
	protected static final String SELECT_USERS_BY_NAME = "SELECT * FROM USER WHERE NAME LIKE ?";
	protected static final String SELECT_EVENT_BY_TITLE = "SELECT * FROM EVENT WHERE TITLE LIKE ?";
	protected static final String SELECT_EVENT_BY_DATE = "SELECT * FROM EVENT WHERE DATE = ?";
	protected static final String SELECT_TICKET_BY_USER = "SELECT TICKET.ID, TICKET.EVENT_ID, TICKET.USER_ID, TICKET.CATEGORY, TICKET.PLACE FROM TICKET JOIN EVENT ON TICKET.EVENT_ID = EVENT.ID WHERE TICKET.USER_ID = ? ORDER BY EVENT.DATE DESC";
	protected static final String SELECT_TICKET_BY_EVENT = "SELECT TICKET.ID, TICKET.EVENT_ID, TICKET.USER_ID, TICKET.CATEGORY, TICKET.PLACE FROM TICKET JOIN USER ON TICKET.USER_ID = USER.ID WHERE TICKET.EVENT_ID = ? ORDER BY USER.EMAIL ASC";
	protected static final String INSERT_USER = "INSERT INTO USER (NAME, EMAIL) VALUES(?,?)";
	protected static final String INSERT_EVENT = "INSERT INTO EVENT (TITLE, DATE, TICKETPRICE) VALUES (?,?,?)";
	protected static final String INSERT_TICKET = "INSERT INTO TICKET (EVENT_ID, USER_ID, CATEGORY, PLACE) VALUES (?,?,?,?)";
	protected static final String INSERT_USER_ACCOUNT = "INSERT INTO USERACCOUNT VALUES (?,?)";
	protected static final String UPDATE_USER = "UPDATE USER SET NAME = ?, EMAIL = ? WHERE ID = ?";
	protected static final String UPDATE_EVENT = "UPDATE EVENT SET TITLE = ?, DATE = ?, TICKETPRICE = ? WHERE ID = ?";
	protected static final String UPDATE_TICKET = "UPDATE TICKET SET EVENT_ID = ?, USER_ID = ?, CATEGORY = ?, PLACE = ? WHERE ID = ?";
	protected static final String UPDATE_USER_ACCOUNT = "UPDATE USERACCOUNT SET AMOUNT = ? WHERE ID = ?";
	protected static final String DELETE_STATEMENT = "DELETE FROM %s WHERE ID = ?";
	
	protected JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/* (non-Javadoc)
	 * @see com.ticketbooking.dao.Dao#delete(com.ticketbooking.model.Entity)
	 */
	@Override
	public E delete(E entity) {
		logger.info("DAO: Deleting {} with id {}", getEntityName.apply(entity), entity.getId());
		E oldEntity = read(entity.getId());
		int rows = jdbcTemplate.update(String.format(DELETE_STATEMENT, getEntityName.apply(entity)), entity.getId());
		return rows > 0 ? oldEntity : null;
	}
	
	protected void createEntityWithAutoIncrement(PreparedStatementCreator psc, E entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		logger.info("DAO: Autogenerated id = {}", keyHolder.getKey().longValue());
	}
	
	protected E read(E entity, long id, RowMapper<E> rowMapper) {
		return jdbcTemplate.queryForObject(String.format(SELECT_STATEMENT, getEntityName.apply(entity)), new Object[] {id}, rowMapper);
	}
	
	protected List<E> getAll(E entity, RowMapper<E> rowMapper) {
		return jdbcTemplate.query(String.format(SELECT_ALL_STATEMENT, getEntityName.apply(entity)), rowMapper);
	}

}
