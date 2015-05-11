package com.ticketbooking.storage.dao;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.ticketbooking.dao.model.UserDao;
import com.ticketbooking.model.User;
import com.ticketbooking.model.impl.UserImpl;

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
