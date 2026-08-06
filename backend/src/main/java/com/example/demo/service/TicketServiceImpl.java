package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Ticket;
import com.example.demo.repository.TicketRepository;

@Service
public class TicketServiceImpl  implements TicketService{
	
	@Autowired
	TicketRepository repo;

	@Override
	public List<Ticket> getAllTickets() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Ticket addTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return repo.save(ticket);
	}

	@Override
	public void deleteTicket(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Ticket getTicketById(Long id) {
		// TODO Auto-generated method stub
		return repo.getById(id);
	}

	@Override
	public Ticket updateTicket(Long id, Ticket ticketDetails) {
		// TODO Auto-generated method stub
		
		Ticket t = repo.getById(id);
		t.setTitle(ticketDetails.getTitle());
		t.setBody(ticketDetails.getBody());
		t.setContact(ticketDetails.getContact());
		t.setEmail(ticketDetails.getEmail());
	
		return repo.save(t);
	}

}
