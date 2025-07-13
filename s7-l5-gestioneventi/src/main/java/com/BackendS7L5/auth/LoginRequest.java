package com.BackendS7L5.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
