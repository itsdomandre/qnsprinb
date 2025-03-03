package com.domandre.controllers.request;

import com.domandre.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}