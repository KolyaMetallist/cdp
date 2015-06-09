package com.ticketbooking.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticketbooking.common.Functions;
import com.ticketbooking.facade.BookingFacade;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.impl.EventImpl;

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
		Event event = bookingFacade.getEventById(eventId);
		if (event == null) {
			throw new RuntimeException("Event not found");
		}
		model.addAttribute("event", event);
		return "eventDetails";
	}
	
	@RequestMapping(value = "/event/create",  method = RequestMethod.GET)
	public String createEvent() {
		return "createEvent";
	}
	
	@RequestMapping(value = "/event/create", method = RequestMethod.POST)
	public String submitEvent(@RequestParam("title")String title,
							@RequestParam("date")String date,
							@RequestParam("ticketPrice")String ticketPrice, Model model) throws Exception {
		try {
			logger.info("Input parameters from form: title = {}, date = {}, ticketPrice = {}", title, date, ticketPrice);
			Event event = new EventImpl();
			event.setTitle(title);
			event.setDate(Functions.DATE_FORMAT.parse(date));
			event.setTicketPrice(Double.parseDouble(ticketPrice));
			bookingFacade.createEvent(event);
			return "redirect:/events/event/id/" + event.getId();
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

}
