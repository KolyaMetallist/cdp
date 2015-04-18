/**
 * 
 */
package com.ticketbooking.storage;

import java.util.List;

import com.ticketbooking.model.Entity;

/**
 * Interface for Storage of object data
 *
 */
public interface Storage<E extends Entity> {
	
	/**
	 * Put an entry into the storage
	 * 
	 * @param key - object key
	 * @param value - object
	 * @return null if the object doesn't exist, else the previous value
	 */
	E put(String key, E value);
	
	/**
	 * Get the object by the key
	 * 
	 * @param key - the key
	 * @return the found object
	 */
	E get(String key);
	
	/**
	 * Deletes the object from the storage
	 * 
	 * @param key
	 * @return
	 */
	E remove(String key);
	
	/**
	 * Get all objects from the storage
	 * 
	 * @return list of objects
	 */
	List<E> getValues();
	
	/**
	 * Set the source file
	 * 
	 * @param filePath - path to the file
	 */
	void setFilePath(String filePath);
	
	/**
	 *  Inits the storage
	 */
	void initFromFile();

}
