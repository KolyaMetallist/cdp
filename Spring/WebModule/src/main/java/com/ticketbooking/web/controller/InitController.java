package com.ticketbooking.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class InitController {
	
	private static final Logger logger = LogManager.getLogger();
	
	@RequestMapping(method = RequestMethod.GET)
	public String init(ModelMap model){
		return "index";
	}
	
	@RequestMapping("404")
	public ModelAndView error404(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Resolve exception: 404");
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("error", "Resource not found");
		return mav;
	}

}
