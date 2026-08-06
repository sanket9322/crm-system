package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Ticket;

public interface TicketService {
	
	List<Ticket> getAllTickets();
	
	Ticket addTicket(Ticket  ticket);
	
	void deleteTicket(Long id);
	 Ticket getTicketById(Long id);
	 
	 Ticket updateTicket(Long id, Ticket ticketDetails);
	

}
