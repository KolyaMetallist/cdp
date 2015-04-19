/**
 * 
 */
package com.ticketbooking.storage.impl;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ticketbooking.model.Entity;
import com.ticketbooking.model.impl.EventImpl;
import com.ticketbooking.model.impl.TicketImpl;
import com.ticketbooking.model.impl.UserImpl;
import com.ticketbooking.storage.Functions;
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
		try(BufferedReader br = new BufferedReader(  
			     new FileReader(filePath))) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, Functions.jsonDateConverter);
			
			Gson gson = gsonBuilder.create();
			DataWrapper data = gson.fromJson(br, DataWrapper.class);
			
			Stream.of(data.getUsers()).forEach(user -> memory.put(Functions.composeId.apply(user), user));
			Stream.of(data.getEvents()).forEach(event -> memory.put(Functions.composeId.apply(event), event));
			Stream.of(data.getTickets()).forEach(ticket -> memory.put(Functions.composeId.apply(ticket), ticket));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public class DataWrapper {
		private UserImpl[] users;
		private EventImpl[] events;
		private TicketImpl[] tickets;
		
		public UserImpl[] getUsers() {
			return users;
		}
		public void setUsers(UserImpl[] users) {
			this.users = users;
		}
		public EventImpl[] getEvents() {
			return events;
		}
		public void setEvents(EventImpl[] events) {
			this.events = events;
		}
		public TicketImpl[] getTickets() {
			return tickets;
		}
		public void setTickets(TicketImpl[] tickets) {
			this.tickets = tickets;
		}
	}

	
	public static void main(String[] args) throws ParseException, FileNotFoundException {
		/*Date date = new Date();
		System.out.println(date);
		
		date = Functions.DATE_FORMAT.parse("03.04.2005");
		
		System.out.println(date);
		
		BufferedReader br = new BufferedReader(  
			     new FileReader("src/main/resources/document.json")); 
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, Functions.jsonDateConverter);
		
		Gson gson = gsonBuilder.create();
		
		DataWrapper data = gson.fromJson(br, DataWrapper.class);
		
		System.out.println(data);
		
		User user = new UserImpl();
		
		System.out.println(user.getClass().getInterfaces()[0].getSimpleName().toLowerCase());*/
		MapStorage ms = new MapStorage();
		ms.setFilePath("src/main/resources/inputData.json");
		
		ms.initFromFile();
		System.out.println(ms.memory);

	}
	
	
 
}
