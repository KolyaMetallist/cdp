package com.ticketbooking.app.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;

import com.ticketbooking.facade.BookingFacade;

@Configuration
public class TestContext {
	
	@Bean
	public BookingFacade bookingFacade() {
		return Mockito.mock(BookingFacade.class);
	}

}
