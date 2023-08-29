package com.example.security.login;

import lombok.Getter;


public record LoginDto(@Getter String username, @Getter String password) {
}
