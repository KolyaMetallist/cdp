package com.ticketbooking.oxm.core;

import org.springframework.core.io.Resource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import com.ticketbooking.oxm.bean.Tickets;

public interface OXMProcessor {
	
	Marshaller getMarchaller();
	
	void setMarshaller(Marshaller marshaller);
	
	Unmarshaller getUnmarshaller();
	
	void setUnMarshaller(Unmarshaller unmarshaller);
	
	void setTicketsResource(Resource resource);
	
	Tickets loadTicketsInfo();

}
