package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CrmEmailService;

@RestController
@RequestMapping("/api/crm/email")
@CrossOrigin(origins = "*")
public class CrmEmailController {

    @Autowired
    private CrmEmailService crmEmailService;

    @PostMapping("/send")
    public String sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body,
            @RequestParam String module) {

        crmEmailService.sendCrmEmail(
                to,
                subject,
                body,
                module
        );

        return "Email sent successfully!";
    }
}