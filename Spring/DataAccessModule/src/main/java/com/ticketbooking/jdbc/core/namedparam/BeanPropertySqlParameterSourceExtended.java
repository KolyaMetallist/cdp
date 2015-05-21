package com.ticketbooking.jdbc.core.namedparam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.ticketbooking.model.Entity;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.User;
import com.ticketbooking.model.holder.DefaultHolder;

public class BeanPropertySqlParameterSourceExtended implements SqlParameterSource {

	private BeanPropertySqlParameterSource internalSource;
	protected static final Logger logger = LogManager.getLogger();
	
	/**
	 * @param entity
	 * @param defaultHolder
	 */
	public BeanPropertySqlParameterSourceExtended(Entity entity, DefaultHolder defaultHolder) {
		logger.info("Bean parameter before the default checking {}", entity);
		if (entity instanceof User && defaultHolder.getDefaultUser() != null) {
			entity = defaultHolder.getDefaultUser();
		} else if (entity instanceof Event && defaultHolder.getDefaultEvent() != null) {
			entity = defaultHolder.getDefaultEvent();
		}
		logger.info("Bean parameter after the default checking {}", entity);
		this.internalSource = new BeanPropertySqlParameterSource(entity);
	}

	@Override
	public boolean hasValue(String paramString) {
		return internalSource.hasValue(paramString);
	}

	@Override
	public Object getValue(String paramString) throws IllegalArgumentException {
		return internalSource.getValue(paramString);
	}

	@Override
	public int getSqlType(String paramString) {
		return internalSource.getSqlType(paramString);
	}

	@Override
	public String getTypeName(String paramString) {
		return internalSource.getTypeName(paramString);
	}

}
