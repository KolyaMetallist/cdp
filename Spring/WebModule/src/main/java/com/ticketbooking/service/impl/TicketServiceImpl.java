/**
 * 
 */
package com.ticketbooking.service.impl;

import java.io.InputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ticketbooking.dao.model.EventDao;
import com.ticketbooking.dao.model.TicketDao;
import com.ticketbooking.dao.model.UserAccountDao;
import com.ticketbooking.dao.model.UserDao;
import com.ticketbooking.model.Event;
import com.ticketbooking.model.Ticket;
import com.ticketbooking.model.Ticket.Category;
import com.ticketbooking.model.User;
import com.ticketbooking.model.UserAccount;
import com.ticketbooking.model.impl.TicketImpl;
import com.ticketbooking.oxm.bean.Tickets;
import com.ticketbooking.oxm.core.OXMProcessor;
import com.ticketbooking.service.TicketService;

/**
 * TicketService implementation
 *
 */
@Transactional(readOnly = true)
public class TicketServiceImpl implements TicketService {
	
	private static final Logger logger = LogManager.getLogger();
	
	private TicketDao ticketDao;
	private UserAccountDao userAccountDao;
	private UserDao userDao;
	private EventDao eventDao;
	private OXMProcessor oxmProcessor;
	private PlatformTransactionManager txManager;
	
	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}
	
	public void setUserAccountDao(UserAccountDao userAccountDao) {
		this.userAccountDao = userAccountDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	public void setOxmProcessor(OXMProcessor oxmProcessor) {
		this.oxmProcessor = oxmProcessor;
	}
	
	public void setTxManager(PlatformTransactionManager txManager) {
		this.txManager = txManager;
	}


	/* (non-Javadoc)
	 * @see com.ticketbooking.service.TicketService#bookTicket(long, long, int, com.ticketbooking.model.Ticket.Category)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Ticket bookTicket(long userId, long eventId, int place,
			Category category) {
		logger.info("Booking ticket: userId {}, eventId {}, place {}, cat {}",
				userId, eventId, place, category);
		//Check user
		User user = userDao.read(userId);
		if (user == null) {
			logger.error("User with id {} doesn't exist", userId);
			throw new RuntimeException("The ticket cannot be booked due to the non-existed user");
		}
		
		//Check user account
		UserAccount userAccount = userAccountDao.read(userId);
		if (userAccount == null) {
			logger.error("User {} has no account", user.getName());
			throw new RuntimeException("The ticket cannot be booked due to the non-existed user account");
		} else if (userAccount.getAmount() <= 0) {
			logger.error("User's {} Account is not positive {}", user.getName(), userAccount.getAmount());
			throw new RuntimeException("The ticket cannot be booked due to the lack of user acoount amount");
		}
		
		//Check event and price
		Event event = eventDao.read(eventId);
		if (event == null) {
			logger.error("Event with id {} doesn't exist", eventId);
			throw new RuntimeException("The ticket cannot be booked due to the non-existed event");
		} else if (event.getTicketPrice() > userAccount.getAmount()) {
			logger.error("User has no enough money. User amount {}. Event price {}", userAccount.getAmount(), event.getTicketPrice());
			throw new RuntimeException("The ticket cannot be booked due to the lack of user acoount amount");
		}
		
		//Create ticket
		Ticket ticket = new TicketImpl();
		ticket.setUserId(userId);
		ticket.setEventId(eventId);
		ticket.setPlace(place);
		ticket.setCategory(category);
		ticketDao.create(ticket);
		
		//Withdraw user account
		userAccount.setAmount(userAccount.getAmount() - event.getTicketPrice());
		userAccountDao.update(userAccount);
		return ticket;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.TicketService#getBookedTickets(com.ticketbooking.model.User, int, int)
	 */
	@Override
	public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
		logger.info("Getting tickets by user {}", user.getName());
		return getPageList(ticketDao.getTicketsByUser(user.getId()), pageNum, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.TicketService#getBookedTickets(com.ticketbooking.model.Event, int, int)
	 */
	@Override
	public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
		logger.info("Getting tickets by event {}", event.getTitle());
		return getPageList(ticketDao.getTicketsByEvent(event.getId()), pageNum, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.service.TicketService#cancelTicket(long)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean cancelTicket(long ticketId) {
		logger.info("Canceling ticket {}", ticketId);
		Ticket ticket = ticketDao.read(ticketId);
		if (ticket == null) {
			logger.error("Ticket {} doesn't exist");
			return false;
		} else {
			Event event = eventDao.read(ticket.getEventId());
			UserAccount userAccount = userAccountDao.read(ticket.getUserId());
			userAccount.setAmount(userAccount.getAmount() + event.getTicketPrice());
			userAccountDao.update(userAccount);
			return ticketDao.delete(ticket) == null ? false : true;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public boolean loadTicketBase(InputStream inputStream) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		
		def.setName("XMLBatchInsert");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = txManager.getTransaction(def);
		try {
			Tickets tickets = oxmProcessor.loadTicketsInfo(inputStream);
			int[] batchCount = ticketDao.batchInsert(tickets.getTickets());
			if (batchCount != null && batchCount.length == tickets.getTickets().size()) {
				logger.info("All XML tickets have been loaded.");
			} else {
				throw new RuntimeException("XML tickets batch insert is unsuccessful");
			}
			txManager.commit(status);
			return true;
		} catch(Exception e) {
			logger.error(e);
			txManager.rollback(status);
		}
		return false;
	}
	
	@Override
	public List<Ticket> getBookedTicketsDefault(User user, int pageSize, int pageNum) {
		logger.info("Getting tickets by user {}", user.getName());
		return getPageList(ticketDao.getTicketsByUserDefault(user), pageNum, pageSize);
	}
	
	@Override
	public List<Ticket> getBookedTicketsDefault(Event event, int pageSize, int pageNum) {
		logger.info("Getting tickets by event {}", event.getTitle());
		return getPageList(ticketDao.getTicketsByEventDefault(event), pageNum, pageSize);
	}

	@Override
	public List<Ticket> getAllTickets() {
		logger.info("Getting all tickets");
		return ticketDao.getAllSorted();
	}

}
