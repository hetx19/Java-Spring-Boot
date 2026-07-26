package com.javaguides.profiles.dto;

public record MailResponse(
        String approach,
        String activeEnvironment,
        String mailServerUrl,
        String supportEmail,
        boolean sendRealEmails,
        String toEmail,
        String subject,
        String message
) {
}
