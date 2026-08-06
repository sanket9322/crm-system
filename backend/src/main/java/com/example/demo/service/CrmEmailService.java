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

    public void sendCrmEmail(String to, String subject, String body, String module) {
        EmailLog log = new EmailLog();
        log.setToEmail(to);
        log.setSubject(subject);
        log.setBody(body);
        log.setModule(module);

        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            mailSender.send(msg);

            log.setStatus("SENT");
        }  catch (Exception e) {
            log.setStatus("FAILED");
            System.err.println("EMAIL ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        emailLogRepo.save(log);
    }
}
