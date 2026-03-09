package com.learning.taskmanager.Service;

import com.learning.taskmanager.Dto.*;
import com.learning.taskmanager.Entity.*;
import com.learning.taskmanager.Repository.*;
import com.learning.taskmanager.Security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenService RefreshTokenService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtUtil jwtUtil;


    public void register(RegisterRequest request) {

        Role role = roleRepository.findByName("ROLE_USER").orElseThrow();

        User user = new User();
        user.setUsername(request.username);
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setRoles(Set.of(role));

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.username).orElseThrow();

        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        
        //JWT access token generated
        String token = jwtUtil.generateToken(user.getUsername());

        RefreshToken refreshToken = RefreshTokenService.createRefreshToken(user);

        AuthResponse response = new AuthResponse();
        response.accessToken = token;
        response.refreshToken = refreshToken.getToken();

        return response;
    }
    public AuthResponse refreshToken(RefreshTokenRequest request) {

    String requestToken = request.getRefreshToken();

    RefreshToken refreshToken = RefreshTokenService
            .findByToken(requestToken)
            .orElseThrow(() -> new RuntimeException("Refresh token not found"));

    RefreshTokenService.verifyExpiration(refreshToken);

    User user = refreshToken.getUser();

    String newAccessToken = jwtUtil.generateToken(user.getUsername());

    AuthResponse response = new AuthResponse();
    response.accessToken = newAccessToken;
    response.refreshToken = refreshToken.getToken();

    return response;
}
}
