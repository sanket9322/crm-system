package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Feedback;
import com.example.demo.entity.Ticket;
import com.example.demo.service.Feedbackservice;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/feedbacks")
public class FeedbackController {
	
	@Autowired
	public Feedbackservice service;
	
	@PostMapping
	public Feedback addFeedback(@RequestBody Feedback feedback) {
		return service.addFeedback(feedback);
	}
	
	 @GetMapping
	    public List<Feedback> getAllFeedback() {
	        return service.getAllFeedback();
	    }

}
