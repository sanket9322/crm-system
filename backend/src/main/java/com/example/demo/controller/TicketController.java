package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Lead;
import com.example.demo.entity.Ticket;
import com.example.demo.service.TicketService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/tickets")
public class TicketController {
    
	@Autowired 
	private TicketService service;
	
	@PostMapping
	public Ticket createTickets(@RequestBody Ticket ticket) {
		return service.addTicket(ticket);
	}
	
	 @GetMapping
	    public List<Ticket> getAllTickets() {
	        return service.getAllTickets();
	    }

	    @GetMapping("/{id}")
	    public Ticket get(@PathVariable Long id) {
	        return service.getTicketById(id);
	    }

	    @PutMapping("/{id}")
	    public Ticket update(@PathVariable Long id, @RequestBody Ticket ticketDetails) {
	    
	        return service.updateTicket(id, ticketDetails);
	    }

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        service.deleteTicket(id);
	    }
}
