package com.spring.core.assignment.java_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.spring.core.assignment")
public class AppConfig {
    @Bean
    public DataSource mysqlDataSource() {
        return new MySQLDataSource();
    }

    @Bean
    public DataSource postgresqlDataSource() {
        return new PostgreSQLDataSource();
    }

    @Bean
    public EmailService emailService() {
        return new EmailService(postgresqlDataSource());
    }
}
