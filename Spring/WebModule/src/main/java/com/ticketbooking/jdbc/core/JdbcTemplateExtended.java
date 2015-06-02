/**
 * 
 */
package com.ticketbooking.jdbc.core;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * @author kolya_metallist
 *
 */
public class JdbcTemplateExtended extends JdbcTemplate {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.JdbcTemplate#queryForObject(java.lang.String, java.lang.Object[], org.springframework.jdbc.core.RowMapper)
	 */
	@Override
	public <T> T queryForObject(String sql, Object[] args,
			RowMapper<T> rowMapper) throws DataAccessException {
		List<T> results = super.query(sql, args, rowMapper);
		if (results == null || results.isEmpty()) {
			logger.warn("Query for object returns null");
            return null;
        }
        else if (results.size() > 1) {
        	logger.error("Query for object returns more 1 value");
            throw new IncorrectResultSizeDataAccessException(1, results.size());
        }
        else{
            return results.iterator().next();
        }
	}

	

}
