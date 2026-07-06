package com.spring.core.assignment;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class PostgreSQLDataSource implements DataSource {

    @Override
    public String[] getEmails() {
        String[] emails = {"tony@gmail.com", "pepper@gmail.com", "peter@gmail.com"};

        return emails;
    }
}
