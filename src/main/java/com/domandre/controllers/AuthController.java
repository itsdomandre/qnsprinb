package com.domandre.controllers;

import com.domandre.config.JwtTokenProvider;
import com.domandre.controllers.request.LoginRequest;
import com.domandre.controllers.request.RegisterRequest;
import com.domandre.entities.User;
import com.domandre.exceptions.UserAlreadyExistsException;
import com.domandre.repositories.UserRepository;
import com.domandre.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints to register, login and logout.")
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "Register new User", description = "Create new account for users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully."),
            @ApiResponse(responseCode = "401", description = "Authorization bearer token invalid or not informed", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "409", description = "User already exists."),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) throws UserAlreadyExistsException {
        User user = authService.register(request);
        return ResponseEntity.ok("User " + "» " + request.getUsername() + " «" + " created successfully!");
    }

    @Operation(summary = "Login", description = "App Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successfully."),
            @ApiResponse(responseCode = "401", description = "Authorization bearer token invalid or not informed", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        return ResponseEntity.ok("User: " + loginRequest.getUsername() + " logged succesfully! \n\nToken: " + token);
    }

    @Operation(summary = "Logout", description = "App Logout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logout successfully."),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        jwtTokenProvider.invalidateToken(token);
        return ResponseEntity.ok("Logout successfully");
    }
}