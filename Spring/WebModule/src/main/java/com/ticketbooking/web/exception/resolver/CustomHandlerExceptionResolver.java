package com.ticketbooking.web.exception.resolver;

import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {
	
	private static final Logger logger = LogManager.getLogger();
	
	public CustomHandlerExceptionResolver() {
		logger.info("CustomHandlerExceptionResolver init");
	}

	@Override
	public ModelAndView resolveException(
			HttpServletRequest request,
			HttpServletResponse response, Object paramObject,
			Exception exception){
		logger.info("Resolve exception: {}", exception.getMessage());
		Stream.of(exception.getStackTrace()).forEach(logger::info);
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("error", exception);
		return mav;
	}

}
