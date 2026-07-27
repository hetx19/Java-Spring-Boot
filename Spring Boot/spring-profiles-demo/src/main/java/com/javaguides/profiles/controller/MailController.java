package com.javaguides.profiles.controller;

import com.javaguides.profiles.config.MailProperties;
import com.javaguides.profiles.dto.MailResponse;
import com.javaguides.profiles.dto.ProfileResponse;
import com.javaguides.profiles.service.EmployeeMailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Tag(name = "REST API for Getting Resources", description = "REST API")
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

    @Operation(summary = "GET ALL Active Profiles", description = "This return a list of Active profiles")
    @GetMapping("/active-profile")
    public ProfileResponse activeProfile() {
        return new ProfileResponse(
                Arrays.asList(environment.getActiveProfiles()),
                mailProperties.environmentName(),
                mailProperties.mailServerUrl(),
                mailProperties.sendRealEmails()
        );
    }

    @Operation(summary = "GET current configuration", description = "This return a mail properties")
    @GetMapping("/config")
    public MailProperties config(){
        return mailProperties;
    }

    @Operation(summary = "Send welcome mail", description = "This sends a welcome mail")
    @GetMapping("/welcome")
    public MailResponse sendWelcomeMail(@RequestParam String email,
                                        @RequestParam String name) {
        return employeeMailService.sendWelcome(email, name);
    }
}
