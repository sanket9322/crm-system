package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmailLog;
import com.example.demo.repository.EmailLogRepository;

import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class CrmEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailLogRepository emailLogRepo;

    public void sendCrmEmail(
            String to,
            String subject,
            String body,
            String module) {

        EmailLog log = new EmailLog();

        log.setToEmail(to);
        log.setSubject(subject);
        log.setBody(body);
        log.setModule(module);

        try {

            // Create email message
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            message,
                            true,
                            "UTF-8"
                    );

            // Recipient
            helper.setTo(to);

            // Subject
            helper.setSubject(subject);

            // Email body
            helper.setText(body, true);

            // Send email
            mailSender.send(message);

            // Save successful log
            log.setStatus("SENT");
            emailLogRepo.save(log);

            System.out.println(
                    "======================================"
            );
            System.out.println(
                    "EMAIL SENT SUCCESSFULLY"
            );
            System.out.println(
                    "TO: " + to
            );
            System.out.println(
                    "SUBJECT: " + subject
            );
            System.out.println(
                    "MODULE: " + module
            );
            System.out.println(
                    "======================================"
            );

        } catch (Exception e) {

            // Save failed log
            log.setStatus("FAILED");
            emailLogRepo.save(log);

            System.err.println(
                    "======================================"
            );
            System.err.println(
                    "EMAIL SENDING FAILED"
            );
            System.err.println(
                    "TO: " + to
            );
            System.err.println(
                    "ERROR: " + e.getMessage()
            );
            System.err.println(
                    "======================================"
            );

            e.printStackTrace();

            // Important:
            // Don't return success if email actually failed
            throw new RuntimeException(
                    "Email sending failed: " + e.getMessage()
            );
        }
    }
}