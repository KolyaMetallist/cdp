package com.ticketbooking.oxm.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.impl.TicketImpl;

@XmlRootElement(name = "tickets")
public class Tickets {
	
	private List<Ticket> tickets;

	@XmlElement(type = TicketImpl.class, name = "ticket")
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
}
