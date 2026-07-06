package com.spring.core.assignment;

import org.springframework.stereotype.Component;

@Component
public class MySQLDataSource implements DataSource {
    @Override
    public String[] getEmails() {
        String[] emails = {"tony@gmail.com", "pepper@gmail.com", "peter@gmail.com"};

        return emails;
    };
}
