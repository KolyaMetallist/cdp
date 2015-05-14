/**
 * 
 */
package com.ticketbooking.jdbc.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticketbooking.dao.Dao;
import com.ticketbooking.model.Entity;

/**
 * Holds the common Dao operations, CRUD, link to database
 *
 */
public abstract class AbstractJdbcDao<E extends Entity> implements Dao<E> {
	
	protected JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
