package com.ticketbooking.storage.dao;

import java.util.List;
import static java.util.stream.Collectors.toList;

import com.ticketbooking.dao.model.UserDao;
import com.ticketbooking.model.Entity;
import com.ticketbooking.model.User;
import com.ticketbooking.model.impl.UserImpl;
import com.ticketbooking.storage.Storage;
import com.ticketbooking.storage.impl.MapStorage;

public class StorageUserDao extends AbstractStorageDao<User> implements UserDao {

	@Override
	public User read(long id) {
		return read(new UserImpl(), id);
	}


	@Override
	public List<User> getAll() {
		return getStorage()
				.getValues()
				.stream()
				.filter(entity -> entity instanceof User)
				.map(entity -> (User) entity)
				.collect(toList());
	}

	@Override
	public User getUserByEmail(String email) {
		return getAll()
				.stream()
				.filter(user -> user.getEmail().equals(email))
				.findFirst()
				.get();
	}

	@Override
	public List<User> getUsersByName(String name) {
		return getAll()
				.stream()
				.filter(user -> user.getName().contains(name))
				.collect(toList());
	}

}
