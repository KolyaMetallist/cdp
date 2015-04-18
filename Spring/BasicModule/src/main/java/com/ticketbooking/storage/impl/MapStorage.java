/**
 * 
 */
package com.ticketbooking.storage.impl;

import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ticketbooking.model.Entity;
import com.ticketbooking.storage.Storage;

/**
 * Storage with in-memory map
 *
 */
public class MapStorage implements Storage<Entity> {
	
	private Map<String, Entity> memory;
	private String filePath;
	
	public MapStorage() {
		memory = new HashMap<>();
	}

	@Override
	public Entity put(String key, Entity value) {
		return memory.put(key, value);
	}

	@Override
	public Entity get(String key) {
		return memory.get(key);
	}

	@Override
	public Entity remove(String key) {
		return memory.remove(key);
	}

	@Override
	public List<Entity> getValues() {
		return memory.values().stream().collect(toList());
	}

	@Override
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void initFromFile() {
		// TODO Auto-generated method stub
		
	}

}
