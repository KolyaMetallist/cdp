/**
 * 
 */
package com.ticketbooking.storage;

import java.util.function.Function;

import com.ticketbooking.model.Entity;

/**
 * Common functions
 *
 */
public abstract class Functions {
	
	private static final String COMPOSE_ID_DELIMITER = ":";
	
	public static final Function<Entity, String> composeId = x -> x
																.getClass()
																.getSimpleName()
																.toLowerCase() +
																COMPOSE_ID_DELIMITER +
																x.getId();

}
