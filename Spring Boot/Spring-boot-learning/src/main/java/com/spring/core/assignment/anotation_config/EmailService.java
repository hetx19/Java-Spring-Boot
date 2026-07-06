package com.spring.core.assignment.anotation_config;

import org.springframework.stereotype.Component;

@Component
public class EmailService {
    private DataSource dataSource;

    public EmailService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void sendEmail() {
        String[] emails = dataSource.getEmails();

        for (String email : emails) {
            System.out.println("Sending email: " + email );
        }
    }
}
