package com.spring.core.assignment.java_config;

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
