package com.BackendS7L5.auth;

import com.BackendS7L5.auth.user.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
