package com.ticketbooking.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ticketbooking.facade.BookingFacade;
import com.ticketbooking.model.User;
import com.ticketbooking.model.UserAccount;
import com.ticketbooking.model.impl.UserImpl;

@Controller
@RequestMapping("/users")
public class UserController {
	
	private static final Logger logger = LogManager.getLogger();
	
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
	
	@RequestMapping(value = "/user/create",  method = RequestMethod.GET)
	public String createUser() {
		return "createUser";
	}
	
	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	@Transactional
	public String submitUser(@ModelAttribute("name")String name,
							@ModelAttribute("email")String email,
							@ModelAttribute("amount")String amount, BindingResult result, Model model) {
		if (result.hasErrors()) {
			result.getFieldErrors().forEach(logger::error);
			return "createUser";
		} else {
			try {
				logger.info("Input parameters from form: name = {}, email = {}, amount = {}", name, email, amount);
				User user = new UserImpl();
				user.setName(name);
				user.setEmail(email);
				bookingFacade.createUser(user);
				bookingFacade.createUserAccount(user, Double.parseDouble(amount));
				return "redirect:/users/user/id/" + user.getId();
			} catch (Exception e) {
				logger.error(e);
				return "createUser";
			}
		}
	}
}