package com.domandre.services;

import com.domandre.config.JwtTokenProvider;
import com.domandre.controllers.request.LoginRequest;
import com.domandre.controllers.request.RegisterRequest;
import com.domandre.entities.InvalidToken;
import com.domandre.entities.User;
import com.domandre.entities.enums.Role;
import com.domandre.repositories.InvalidTokenRepository;
import com.domandre.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final InvalidTokenRepository invalidTokenRepository;

    public User register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Username is already taken");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role role = request.getRole() != null ? request.getRole() : Role.USER;
        user.setRole(role);

        return userRepository.save(user);
    }

    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        return tokenProvider.generateToken(authentication);
    }

    public void logout(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);  // Remover o prefixo "Bearer "
        }

        if (tokenProvider.validateToken(token)) {
            InvalidToken invalidToken = new InvalidToken(token, tokenProvider.getExpirationDateFromJWT(token));
            invalidTokenRepository.save(invalidToken);
        }
    }

    public boolean isTokenBlacklisted(String token) {
        return invalidTokenRepository.existsByToken(token);
    }
}