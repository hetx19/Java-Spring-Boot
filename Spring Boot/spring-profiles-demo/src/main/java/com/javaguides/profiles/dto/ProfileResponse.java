package com.javaguides.profiles.dto;

import java.util.List;

public record ProfileResponse(
        List<String> activeProfiles,
        String loadedMailEnvironment,
        String emailServerUrl,
        boolean sendRealEmails
) {
}
