package com.example.authapi.service;

public class IdpTokenResponse {
    private String accessToken;
    private long expiresAtEpochMillis;

    public IdpTokenResponse(String token, long expiresAt) {
        this.accessToken = token;
        this.expiresAtEpochMillis = expiresAt;
    }

    public String getAccessToken() { return accessToken; }
    public long getExpiresAtEpochMillis() { return expiresAtEpochMillis; }
}