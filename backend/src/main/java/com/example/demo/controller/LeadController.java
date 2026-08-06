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
import com.example.demo.service.LeadService;

@RestController
@RequestMapping("/api/leads")
@CrossOrigin("*")
public class LeadController {

    @Autowired
    private LeadService service;
    
    @GetMapping("/test")
    public String test() {
        return "Lead API working fine";
    }


    @PostMapping
    public Lead create(@RequestBody Lead lead) {
    	  lead.setId(null); 
        return service.save(lead);
    }

    @GetMapping
    public List<Lead> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Lead get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Lead update(@PathVariable Long id, @RequestBody Lead leaddetails) {
    
        return service.updateLead(id, leaddetails);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
