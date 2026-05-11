package com.souza.auth.project_spring.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
