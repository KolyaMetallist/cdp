/**
 * 
 */
package com.ticketbooking.storage.dao;

import java.util.concurrent.atomic.AtomicLong;

import com.ticketbooking.dao.Dao;
import com.ticketbooking.model.Entity;
import com.ticketbooking.storage.Functions;
import com.ticketbooking.storage.Storage;

/**
 * Holds the common Dao operations, CRUD, link to storage
 *
 */
public abstract class AbstractStorageDao<E extends Entity> implements Dao<E> {
	
	private static AtomicLong idCounter;
	
	private Storage<Entity> storage;
	
	protected Storage<Entity> getStorage() {
		return storage;
	}
	
	public void setStorage(Storage<Entity> storage) {
		this.storage = storage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E create(E entity) {
		entity.setId(nextId());
		return (E) storage.put(Functions.composeId.apply(entity), entity);
	}

	@SuppressWarnings("unchecked")
	public E read(Entity dummy, long id) {
		dummy.setId(id);
		return (E) storage.get(Functions.composeId.apply(dummy));
	}

	@SuppressWarnings("unchecked")
	@Override
	public E update(E entity) {
		return (E) storage.put(Functions.composeId.apply(entity), entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public E delete(E entity) {
		return (E) storage.remove(Functions.composeId.apply(entity));
	}
	
	public static long nextId() {
		return idCounter.incrementAndGet();
	}
	
	public synchronized long computeMaxId() {
		return storage
				.getValues()
				.stream()
				.map(Entity::getId)
				.max(Long::compare)
				.get();
	}
	
	public void initIdCounter() {
		idCounter = new AtomicLong(computeMaxId());
	}
}
