package com.example.security.domain.login;

import lombok.Getter;


public record LoginDto(@Getter String username, @Getter String password) {
}
