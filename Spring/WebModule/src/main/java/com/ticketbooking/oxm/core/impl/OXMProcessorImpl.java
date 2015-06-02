package com.ticketbooking.oxm.core.impl;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.transform.stream.StreamSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import com.ticketbooking.oxm.bean.Tickets;
import com.ticketbooking.oxm.core.OXMProcessor;

public class OXMProcessorImpl implements OXMProcessor {
	
	private static final Logger logger = LogManager.getLogger();
	
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	private Resource ticketsResource;
	 
	@Override
	public void setTicketsResource(Resource resource) {
		this.ticketsResource = resource;
	}
	
	@Override
	public Tickets loadTicketsInfo() {
		Tickets tickets = new Tickets();
		try(FileInputStream inputStream = new FileInputStream(ticketsResource.getFile())) {
			tickets = (Tickets) unmarshaller.unmarshal(new StreamSource(inputStream));
		} catch (IOException e) {
			logger.error(e);
		}
		return tickets;
	}
	@Override
	public Marshaller getMarchaller() {
		return marshaller;
	}
	
	@Override
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}
	
	@Override
	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}
	
	@Override
	public void setUnMarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

}
