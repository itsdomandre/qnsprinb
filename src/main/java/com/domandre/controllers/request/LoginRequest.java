package com.domandre.controllers.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    @NotEmpty(message = "Password cannot be in blank")
    private String password;
}