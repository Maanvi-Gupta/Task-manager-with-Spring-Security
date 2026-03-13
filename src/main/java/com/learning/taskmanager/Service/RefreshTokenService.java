package com.learning.taskmanager.Service;

import com.learning.taskmanager.Entity.RefreshToken;
import com.learning.taskmanager.Repository.RefreshTokenRepository;
import com.learning.taskmanager.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;   
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;  //Universally Unique Identifier- to generate random token strings

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


    // refresh token validity = 7 days 
    private final long refreshTokenDurationDays = 7;

    // create refresh token
    public RefreshToken createRefreshToken(User user) {


        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);

        // generate random token
        refreshToken.setToken(UUID.randomUUID().toString());

        // set expiry date
        refreshToken.setExpiryDate(
                Instant.now().plus(refreshTokenDurationDays, ChronoUnit.DAYS)
        );

        return refreshTokenRepository.save(refreshToken);
    }

    // find token in DB
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    // check if token expired
    public RefreshToken verifyExpiration(RefreshToken token) {

        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {

            refreshTokenRepository.delete(token);

            throw new RuntimeException("Refresh token expired. Please login again.");
        }

        return token;
    }

}