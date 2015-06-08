package com.ticketbooking.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ticketbooking.facade.BookingFacade;

@Controller
@RequestMapping("/events")
public class EventController {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private BookingFacade bookingFacade;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllTickets(Model model) {
		model.addAttribute("events", bookingFacade.getAllEvents ());
		return "eventsList";
	}
	
	@RequestMapping(value = "/event/id/{id}", method = RequestMethod.GET)
	public String getUserDetail(@PathVariable(value = "id") long eventId, Model model) {
		model.addAttribute("event", bookingFacade.getEventById(eventId));
		return "eventDetails";
	}

}
