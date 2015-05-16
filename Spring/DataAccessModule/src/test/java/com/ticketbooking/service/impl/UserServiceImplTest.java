/**
 * 
 */
package com.ticketbooking.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ticketbooking.app.AbstractTest;
import com.ticketbooking.dao.model.TicketDao;
import com.ticketbooking.dao.model.UserAccountDao;
import com.ticketbooking.dao.model.UserDao;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.User;
import com.ticketbooking.model.UserAccount;
import com.ticketbooking.model.impl.TicketImpl;

/**
 * Test for UserServiceImpl
 *
 */
public class UserServiceImplTest extends AbstractTest {
	
	@InjectMocks
	private UserServiceImpl userService;
	@Mock
	private UserDao userDao;
	@Mock
	private TicketDao ticketDao;
	@Mock
	private UserAccountDao userAccountDao;
	
	private User user;
	private User updatedUser;
	private Ticket ticket1;
	private Ticket ticket2;
	private UserAccount userAccount;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		user = buildUser();
		updatedUser = buildCloneUser(user);
		ticket1 = new TicketImpl();
		ticket2 = new TicketImpl();
		userAccount = buildUserAccount(user);
		when(userDao.read(USER_ID)).thenReturn(user);
		when(userDao.getUserByEmail(USER_EMAIL)).thenReturn(user);
		when(userDao.getUsersByName(USER_NAME)).thenReturn(Arrays.asList(user));
		when(userDao.create(user)).thenAnswer(invocation -> 
			{ User user = (User) invocation.getArguments()[0];
			  user.setId(AbstractTest.USER_ID);
			  return null;});
		when(userDao.update(user)).thenReturn(updatedUser);
		when(userDao.delete(user)).thenReturn(user);
		when(ticketDao.getTicketsByUser(USER_ID)).thenReturn(Arrays.asList(ticket1, ticket2));
		when(ticketDao.delete(any(Ticket.class))).thenReturn(any(Ticket.class));
		when(userAccountDao.read(USER_ID)).thenReturn(userAccount);
		when(userAccountDao.delete(userAccount)).thenReturn(userAccount);
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.UserServiceImpl#getUserById(long)}.
	 */
	@Test
	public void testGetUserById() {
		assertThat(userService.getUserById(USER_ID), equalTo(user));
		verify(userDao, times(1)).read(USER_ID);
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.UserServiceImpl#getUserByEmail(java.lang.String)}.
	 */
	@Test
	public void testGetUserByEmail() {
		assertThat(userService.getUserByEmail(USER_EMAIL), equalTo(user));
		verify(userDao, times(1)).getUserByEmail(USER_EMAIL);
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.UserServiceImpl#getUsersByName(java.lang.String, int, int)}.
	 */
	@Test
	public void testGetUsersByName() {
		assertThat(userService.getUsersByName(USER_NAME, 10, 1), hasItem(user));
		verify(userDao, times(1)).getUsersByName(USER_NAME);
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.UserServiceImpl#createUser(com.ticketbooking.model.User)}.
	 */
	@Test
	public void testCreateUser() {
		assertThat(user.getId(), not(AbstractTest.USER_ID));
		assertNull(userService.createUser(user));
		assertThat(user.getId(), equalTo(AbstractTest.USER_ID));
		verify(userDao, times(1)).create(user);
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.UserServiceImpl#updateUser(com.ticketbooking.model.User)}.
	 */
	@Test
	public void testUpdateUser() {
		assertThat(userService.updateUser(user), equalTo(updatedUser));
		verify(userDao, times(1)).update(user);
	}

	/**
	 * Test method for {@link com.ticketbooking.service.impl.UserServiceImpl#deleteUser(long)}.
	 */
	@Test
	public void testDeleteUser() {
		assertTrue(userService.deleteUser(USER_ID));
		verify(userDao, times(1)).delete(user);
		verify(ticketDao, times(1)).getTicketsByUser(USER_ID);
		verify(ticketDao, times(2)).delete(any(Ticket.class));
		verify(userAccountDao, times(1)).delete(userAccount);
	}

}
