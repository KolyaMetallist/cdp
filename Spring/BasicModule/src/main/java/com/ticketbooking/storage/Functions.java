/**
 * 
 */
package com.ticketbooking.storage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

import com.google.gson.JsonDeserializer;
import com.ticketbooking.model.Entity;

/**
 * Common functions
 *
 */
public abstract class Functions {
	
	private static final String COMPOSE_ID_DELIMITER = ":";
	
	public static final Function<Entity, String> composeId = x -> x
																.getClass()
																.getInterfaces()[0]
																.getSimpleName()
																.toLowerCase() +
																COMPOSE_ID_DELIMITER +
																x.getId();

	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
	
	public static final JsonDeserializer<Date> jsonDateConverter = (json, typeOfT, context) -> {
		try {
			return Functions.DATE_FORMAT.parse(json.getAsString());
		} catch (ParseException e) {
			e.printStackTrace();
			// add log
		}
	    return null;
	};

}
