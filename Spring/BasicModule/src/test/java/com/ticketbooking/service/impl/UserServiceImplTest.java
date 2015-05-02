/**
 * 
 */
package com.ticketbooking.service.impl;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ticketbooking.app.AbstractTest;
import com.ticketbooking.dao.model.TicketDao;
import com.ticketbooking.dao.model.UserDao;
import com.ticketbooking.model.User;

/**
 * Test for UserServiceImpl
 *
 */
public class UserServiceImplTest extends AbstractTest{
	
	private static final long userId = 123l;
	
	@InjectMocks
	private UserServiceImpl userService;
	@Mock
	private UserDao userDao;
	@Mock
	private TicketDao ticketDao;
	
	private User user;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		user = buildUser();
		when(userDao.read(userId)).thenReturn(user);
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.UserServiceImpl#getUserById(long)}.
	 */
	@Test
	public void testGetUserById() {
		assertThat(userService.getUserById(userId), equalTo(user));
		verify(userDao, times(1)).read(userId);
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.UserServiceImpl#getUserByEmail(java.lang.String)}.
	 */
	@Test
	public void testGetUserByEmail() {
		
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.UserServiceImpl#getUsersByName(java.lang.String, int, int)}.
	 */
	@Test
	public void testGetUsersByName() {
		
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.UserServiceImpl#createUser(com.ticketbooking.model.User)}.
	 */
	@Test
	public void testCreateUser() {
		
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.UserServiceImpl#updateUser(com.ticketbooking.model.User)}.
	 */
	@Test
	public void testUpdateUser() {
		
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.UserServiceImpl#deleteUser(long)}.
	 */
	@Test
	public void testDeleteUser() {
		
	}

}
