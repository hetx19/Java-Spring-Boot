package com.javaguides.profiles.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema (description = "MailResponceDTO Model Information")
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
