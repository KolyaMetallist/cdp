package com.ticketbooking.web.controller;

import static java.util.stream.Collectors.toMap;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticketbooking.facade.BookingFacade;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.User;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private BookingFacade bookingFacade;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllTickets(Model model) {
		model.addAttribute("tickets", bookingFacade.getAllTickets());
		return "ticketsList";
	}
	
	@RequestMapping(value = "/ticket/book", method = RequestMethod.GET)
	public String getUserTickets(Model model) {
		model.addAttribute("userIds", bookingFacade.getAllUsers().stream().collect(toMap(User::getId, User::getName)));
		model.addAttribute("eventIds", bookingFacade.getAllEvents().stream().collect(toMap(Event::getId, Event::getTitle)));
		model.addAttribute("categories", Ticket.Category.values());
		return "bookTicket";
	}
	
	@RequestMapping(value = "/ticket/book", method = RequestMethod.POST)
	public String bookTicket(@RequestParam("userId")Long userId,
							@RequestParam("eventId")Long eventId,
							@RequestParam("category")Ticket.Category category,
							@RequestParam("place")String place, Model model) {
		try {
			bookingFacade.bookTicket(userId, eventId, Integer.parseInt(place), category);
			return "redirect:/tickets";
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/user/{id}/download", method = RequestMethod.GET)
	public List<Ticket> getPdfUserTickets(@PathVariable(value = "id") long userId, Model model) {
		List<Ticket> tickets = null;
		User user = bookingFacade.getUserById(userId);
		tickets = bookingFacade.getBookedTickets(user, 20, 1);
		model.addAttribute("tickets", tickets);
		return tickets;
	}

}
