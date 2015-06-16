package com.ticketbooking.web.controller;

import static org.junit.Assert.*;

import org.apache.velocity.tools.view.VelocityViewFilter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ticketbooking.app.AbstractTest;
import com.ticketbooking.app.config.TestContext;
import com.ticketbooking.app.config.WebAppContext;
import com.ticketbooking.facade.BookingFacade;
import com.ticketbooking.model.User;
import com.ticketbooking.model.UserAccount;
import com.ticketbooking.web.controller.annotation.WebContextTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@WebContextTest
//@ContextConfiguration(locations = {"file:src/test/resources/test-context.xml", "file:src/main/webapp/WEB-INF/ticketbooking-servlet.xml"})
public class WebContextUserControllerTest extends AbstractTest{
	
	private MockMvc mockMvc;
	
	@Autowired
	private BookingFacade bookingFacadeMock;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		Mockito.reset(bookingFacadeMock);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				//.addFilters(new VelocityViewFilter())
				.build();
	}

	@Test
	public void testGetAllUsers() throws Exception {
		mockMvc.perform(get("/users"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("usersList"))
			//.andExpect(forwardedUrl("/WEB-INF/content/velocity/usersList.vm"))
			.andExpect(model().attributeExists("users"));;
			
		verify(bookingFacadeMock, times(1)).getAllUsers();
		verifyNoMoreInteractions(bookingFacadeMock);
	}
	
	@Test
	public void testGetUserDetailSuccess() throws Exception {
		User user = buildUser();
		UserAccount userAccount = buildUserAccount(user);
		when(bookingFacadeMock.getUserById(USER_ID)).thenReturn(user);
		when(bookingFacadeMock.getUserAccountById(USER_ID)).thenReturn(userAccount);
		
		mockMvc.perform(get("/users/user/id/{id}", USER_ID))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("userDetails"))
			.andExpect(model().attribute("user", equalTo(user)))
			.andExpect(model().attribute("userAccount", equalTo(userAccount)));
		
		verify(bookingFacadeMock, times(1)).getUserById(USER_ID);
		verify(bookingFacadeMock, times(1)).getUserAccountById(USER_ID);
		verifyNoMoreInteractions(bookingFacadeMock);
	}
	
	@Test
	public void testGetUserDetailFailure() throws Exception {
		when(bookingFacadeMock.getUserById(USER_ID)).thenReturn(null);
		
		MvcResult mvcResult = mockMvc.perform(get("/users/user/id/{id}", USER_ID))
			.andDo(print())
			.andExpect(status().isInternalServerError())
			.andExpect(view().name("error"))
			.andExpect(model().attributeExists("error"))
			.andExpect(forwardedUrl("/WEB-INF/content/jsp/error.jsp"))
			.andReturn();
		
		assertThat(mvcResult.getResolvedException(), instanceOf(RuntimeException.class));
		
		verify(bookingFacadeMock, times(1)).getUserById(USER_ID);
		verifyNoMoreInteractions(bookingFacadeMock);
	}
	
	@Test
	public void testCreateUser() throws Exception {
		mockMvc.perform(get("/users/user/create"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("createUser"))
			.andExpect(forwardedUrl("/WEB-INF/content/jsp/createUser.jsp"));
		
		verifyZeroInteractions(bookingFacadeMock);
	}
	
	@Test
	public void testSubmitUserSuccess() throws Exception {
		mockMvc.perform(post("/users/user/create")
				.param("name", USER_NAME)
				.param("email", USER_EMAIL)
				.param("amount", "100.56")
				)
				.andDo(print())
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/users/user/id/0"))
				.andExpect(redirectedUrl("/users/user/id/0"));
	}
	
	@Test
	public void testSubmitUserFailure() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/users/user/create")
				.param("name", USER_NAME)
				.param("email", USER_EMAIL)
				.param("amount", "symbol")
				)
				.andDo(print())
				.andExpect(status().isInternalServerError())
				.andExpect(view().name("error"))
				.andExpect(model().attributeExists("error"))
				.andExpect(forwardedUrl("/WEB-INF/content/jsp/error.jsp"))
				.andReturn();
		
		assertThat(mvcResult.getResolvedException(), instanceOf(NumberFormatException.class));
	}
	
	@Test
	public void testPdf() throws Exception {
		mockMvc.perform(get("/tickets/user/{id}/download.pdf", USER_ID))
			.andDo(print());
	}

}
