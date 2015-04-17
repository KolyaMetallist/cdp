/**
 * 
 */
package com.ticketbooking.dao;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Generic interface for CRUD operations 
 *
 */
public interface Dao<E> {
	
	/**
	 * Creates a new entity object in a storage
	 * 
	 * @param entity - the object to be created
	 * @return created object
	 */
	E create(E entity);

	/**
	 * Get the entity object by id
	 * 
	 * @param id - object id
	 * @return the object from the storage
	 */
	E read(long id);
	
	/**
	 * Updates the entity object
	 * 
	 * @param entity- the object to be updated
	 * @return updated object
	 */
	E update(E entity);
	
	/**
	 * Removes the entity object from the storage
	 * 
	 * @param entity - the object to be removed
	 * @return the removed object
	 */
	E delete(E entity);
	
	/**
	 * Get all entity objects
	 * 
	 * @return
	 */
	List<E> getAll();
	
	/**
	 * Get all entity object sorted
	 * 
	 * @return
	 */
	default List<E> getAllSorted(){
		return this
				.getAll()
				.stream()
				.sorted()
				.collect(Collectors.toList());
	}
}
