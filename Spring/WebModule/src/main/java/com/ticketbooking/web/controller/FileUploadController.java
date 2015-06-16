package com.ticketbooking.web.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ticketbooking.facade.BookingFacade;

@Controller
public class FileUploadController {
	
	private static final Logger logger = LogManager.getLogger();
	
	protected BookingFacade bookingFacade;
	
	@Autowired
	public FileUploadController(BookingFacade bookingFacade) {
		this.bookingFacade = bookingFacade;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") Part file, RedirectAttributes redirectAttributes) throws IOException {
	
		try {
			InputStream inputStream = file.getInputStream();
			boolean uploaded = bookingFacade.loadTicketBase(inputStream);
			redirectAttributes.addFlashAttribute("uploaded", uploaded);
			redirectAttributes.addFlashAttribute("fileName", file.getSubmittedFileName());
			return "redirect:/tickets";
		} catch (IOException e) {
			logger.error(e);
			throw e;
		}
	}
}
