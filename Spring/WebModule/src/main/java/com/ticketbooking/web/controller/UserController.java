package com.ticketbooking.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ticketbooking.facade.BookingFacade;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private BookingFacade bookingFacade;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		model.addAttribute("users", bookingFacade.getAllUsers());
		return "usersList";
	}
	
	@RequestMapping(value = "/user/id/{id}", method = RequestMethod.GET)
	public String getUserDetail(@PathVariable(value = "id") long userId, Model model) {
		model.addAttribute("user", bookingFacade.getUserById(userId));
		model.addAttribute("userAccount", bookingFacade.getUserAccountById(userId));
		return "userDetails";
	}
}
