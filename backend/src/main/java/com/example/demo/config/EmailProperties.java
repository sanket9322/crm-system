package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "crm.email")
public class EmailProperties {

    /** Email provider identifier (currently: resend). */
    private String provider = "resend";

    /** Sender address, e.g. "CRM <noreply@yourdomain.com>". */
    private String from;

    /** Resend API key (from RESEND_API_KEY env var). */
    private String apiKey;

    /** Resend send-email endpoint. */
    private String apiUrl = "https://api.resend.com/emails";
}
