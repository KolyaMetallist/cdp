package com.ticketbooking.web.controller;

import static org.junit.Assert.*;

import java.util.Arrays;

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
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.User;
import com.ticketbooking.model.UserAccount;
import com.ticketbooking.web.controller.annotation.WebContextTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@WebContextTest
public class WebContextTicketControllerTest extends AbstractTest {

private MockMvc mockMvc;
	
	@Autowired
	private BookingFacade bookingFacadeMock;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		Mockito.reset(bookingFacadeMock);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}
	
	@Test
	public void testGetPdfUserTickets() throws Exception {
		User user = buildUser();
		Ticket ticket = buildTicket();
		when(bookingFacadeMock.getUserById(USER_ID)).thenReturn(user);
		when(bookingFacadeMock.getBookedTickets(user, 20, 1)).thenReturn(Arrays.asList(ticket));
		
		mockMvc.perform(get("/tickets/user/{id}/download.pdf", USER_ID))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("tickets/user/" + USER_ID + "/download"))
			.andExpect(content().contentType("application/pdf"))
			.andExpect(model().attribute("tickets", hasItem(ticket)));
	}
	
}
