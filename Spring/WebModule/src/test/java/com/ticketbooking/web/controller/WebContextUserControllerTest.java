package com.ticketbooking.web.controller;

import static org.junit.Assert.*;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ticketbooking.app.config.TestContext;
import com.ticketbooking.app.config.WebAppContext;
import com.ticketbooking.facade.BookingFacade;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyZeroInteractions;
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
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
//@ContextConfiguration(locations = {"file:src/test/resources/test-context.xml", "file:src/main/webapp/WEB-INF/ticketbooking-servlet.xml"})
@WebAppConfiguration
public class WebContextUserControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private BookingFacade bookingFacadeMock;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		Mockito.reset(bookingFacadeMock);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetAllUsers() throws Exception {
		mockMvc.perform(get("/users"))
			//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("usersList"))
			//.andExpect(forwardedUrl("/WEB-INF/content/velocity/usersList.vm"))
			.andExpect(model().attributeExists("users"));;
		verify(bookingFacadeMock, times(1)).getAllUsers();
	}

}
