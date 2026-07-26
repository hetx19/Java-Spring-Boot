package com.javaguides.profiles.controller;

import com.javaguides.profiles.config.MailProperties;
import com.javaguides.profiles.dto.MailResponse;
import com.javaguides.profiles.dto.ProfileResponse;
import com.javaguides.profiles.service.EmployeeMailService;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    private final EmployeeMailService employeeMailService;
    private final MailProperties mailProperties;
    private final Environment environment;

    public MailController(EmployeeMailService employeeMailService, MailProperties mailProperties, Environment environment) {
        this.employeeMailService = employeeMailService;
        this.mailProperties = mailProperties;
        this.environment = environment;
    }

    @GetMapping("/active-profile")
    public ProfileResponse activeProfile() {
        return new ProfileResponse(
                Arrays.asList(environment.getActiveProfiles()),
                mailProperties.environmentName(),
                mailProperties.mailServerUrl(),
                mailProperties.sendRealEmails()
        );
    }

    @GetMapping("/config")
    public MailProperties config(){
        return mailProperties;
    }

    @GetMapping("/welcome")
    public MailResponse sendWelcomeMail(@RequestParam String email,
                                        @RequestParam String name) {
        return employeeMailService.sendWelcome(email, name);
    }
}
