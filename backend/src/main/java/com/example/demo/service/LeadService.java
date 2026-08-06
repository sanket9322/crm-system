package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Lead;
import com.example.demo.repository.LeadRepository;

@Service
public class LeadService {

    @Autowired
    private LeadRepository repo;

    public Lead save(Lead lead) {
        return repo.save(lead);
    }

    public List<Lead> getAll() {
        return repo.findAll();
    }

    public Lead getById(Long id) {
        return repo.findById(id).orElseThrow();
    }
    
    public Lead updateLead(Long id, Lead leaddetails) {
    	
    	
    	Lead lead = repo.findById(id).orElseThrow();
    	lead.setName(leaddetails.getName());
    	lead.setEmail(leaddetails.getEmail());
    	lead.setContact(leaddetails.getContact());
    	lead.setCity(leaddetails.getCity());
    
    	return repo.save(lead);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
