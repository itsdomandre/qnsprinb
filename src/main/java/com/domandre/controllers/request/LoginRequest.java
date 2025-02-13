package com.domandre.controllers.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}