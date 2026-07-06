package com.spring.core.assignment.java_config;

public class PostgreSQLDataSource implements DataSource{
    @Override
    public String[] getEmails() {
        String[] emails = {"tony@gmail.com", "pepper@gmail.com", "peter@gmail.com"};

        return emails;
    }
}
