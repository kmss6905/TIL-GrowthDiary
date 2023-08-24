package com.example.login.controller.request;

import lombok.Getter;

public record LoginRequestDto(@Getter String id, @Getter String password) {
}
