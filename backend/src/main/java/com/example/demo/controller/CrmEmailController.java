package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.CrmEmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/crm/email")
@RequiredArgsConstructor
public class CrmEmailController {

    private final CrmEmailService crmEmailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body,
            @RequestParam String module) {

        try {

            crmEmailService.sendCrmEmail(
                to,
                subject,
                body,
                module
            );

            return ResponseEntity.ok(
                "Email sent successfully!"
            );

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.internalServerError().body(
                "Email sending failed: " + e.getMessage()
            );
        }
    }
}