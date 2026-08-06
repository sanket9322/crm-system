package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Feedback;

public interface Feedbackservice {
	List<Feedback> getAllFeedback();
	
Feedback addFeedback(Feedback feedback);
	

}
