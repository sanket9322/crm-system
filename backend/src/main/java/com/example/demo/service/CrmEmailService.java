package com.example.demo.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmailLog;
import com.example.demo.repository.EmailLogRepository;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrmEmailService {

    private final JavaMailSender mailSender;
    private final EmailLogRepository emailLogRepo;

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

            System.out.println("=================================");
            System.out.println("EMAIL START");
            System.out.println("TO: " + to);
            System.out.println("SUBJECT: " + subject);
            System.out.println("MODULE: " + module);
            System.out.println("=================================");

            MimeMessage message =
                mailSender.createMimeMessage();

            MimeMessageHelper helper =
                new MimeMessageHelper(
                    message,
                    false,
                    "UTF-8"
                );

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, false);

            mailSender.send(message);

            log.setStatus("SENT");
            emailLogRepo.save(log);

            System.out.println("EMAIL SENT SUCCESSFULLY");

        } catch (Exception e) {

            log.setStatus("FAILED");
            emailLogRepo.save(log);

            System.err.println("=================================");
            System.err.println("EMAIL FAILED");
            System.err.println("TO: " + to);
            System.err.println("ERROR: " + e.getMessage());
            System.err.println("=================================");

            e.printStackTrace();

            throw new RuntimeException(
                "SMTP email sending failed: "
                + e.getMessage()
            );
        }
    }
}