package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import com.example.demo.config.EmailProperties;
import com.example.demo.entity.EmailLog;
import com.example.demo.exception.EmailSendException;
import com.example.demo.repository.EmailLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrmEmailService {

    private final EmailLogRepository emailLogRepo;
    private final EmailProperties emailProperties;
    private final RestClient restClient;

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
            validateConfiguration();

            System.out.println("=================================");
            System.out.println("EMAIL START");
            System.out.println("TO: " + to);
            System.out.println("SUBJECT: " + subject);
            System.out.println("MODULE: " + module);
            System.out.println("PROVIDER: " + emailProperties.getProvider());
            System.out.println("=================================");

            Map<String, Object> payload = Map.of(
                "from", emailProperties.getFrom(),
                "to", List.of(to),
                "subject", subject,
                "text", body
            );

            restClient.post()
                .uri(emailProperties.getApiUrl())
                .header("Authorization", "Bearer " + emailProperties.getApiKey())
                .contentType(MediaType.APPLICATION_JSON)
                .body(payload)
                .retrieve()
                .toBodilessEntity();

            log.setStatus("SENT");
            emailLogRepo.save(log);

            System.out.println("EMAIL SENT SUCCESSFULLY");

        } catch (EmailSendException e) {
            markFailed(log);
            throw e;
        } catch (RestClientResponseException e) {
            markFailed(log);

            System.err.println("=================================");
            System.err.println("EMAIL FAILED (provider response)");
            System.err.println("TO: " + to);
            System.err.println("STATUS: " + e.getStatusCode().value());
            System.err.println("=================================");

            throw new EmailSendException(
                "Email provider rejected the request. Verify sender address and recipient."
            );
        } catch (Exception e) {
            markFailed(log);

            System.err.println("=================================");
            System.err.println("EMAIL FAILED");
            System.err.println("TO: " + to);
            System.err.println("ERROR: " + e.getMessage());
            System.err.println("=================================");

            e.printStackTrace();

            throw new EmailSendException(
                "Unable to send email at this time. Please try again later.",
                e
            );
        }
    }

    private void validateConfiguration() {
        if (!StringUtils.hasText(emailProperties.getApiKey())) {
            throw new EmailSendException(
                "Email service is not configured. Set RESEND_API_KEY."
            );
        }

        if (!StringUtils.hasText(emailProperties.getFrom())) {
            throw new EmailSendException(
                "Email sender is not configured. Set EMAIL_FROM."
            );
        }
    }

    private void markFailed(EmailLog log) {
        log.setStatus("FAILED");
        emailLogRepo.save(log);
    }
}
