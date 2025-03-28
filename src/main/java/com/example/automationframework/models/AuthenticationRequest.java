package com.example.automationframework.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Random;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    private Long id = new Random().nextLong();
    protected String username;
    protected String password;

    public AuthenticationRequest(String username, String password) {
        this.id = new Random().nextLong();
        this.username = username;
        this.password = password;
    }
}
