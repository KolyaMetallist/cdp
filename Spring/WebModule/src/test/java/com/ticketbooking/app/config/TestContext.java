package com.ticketbooking.app.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.ticketbooking.facade.BookingFacade;

@Configuration
@ImportResource({"classpath:**/ticketbooking-servlet.xml"})
public class TestContext {
	
	@Bean
	public BookingFacade bookingFacade() {
		return Mockito.mock(BookingFacade.class);
	}

}
