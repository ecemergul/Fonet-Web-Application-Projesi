package com.example.projectEmlak.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.projectEmlak.model.Customer;
import com.example.projectEmlak.model.EstateCompany;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Service for handling JWTs for user authentication.
 */
@Service
public class JWTService {

    /** The secret key to encrypt the JWTs with. */
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    /** The issuer the JWT is signed with. */
    @Value("${jwt.issuer}")
    private String issuer;

    /** How many seconds from generation should the JWT expire? */
    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;

    /** The algorithm generated post construction. */
    private Algorithm algorithm;

    /** The JWT claim key for the username. */
    private static final String USERNAME_KEY = "USERNAME";

    /**
     * Post construction method.
     */
    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    /**
     * Generates a JWT based on the given customer.
     * @param customer The customer to generate for.
     * @return The JWT.
     */
    public String generateJWT(Customer customer) {
        return JWT.create()
                .withClaim(USERNAME_KEY, customer.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String generateJWT(EstateCompany company) {
        return JWT.create()
                .withClaim(USERNAME_KEY, company.getAgentUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);
    }
}
