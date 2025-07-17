package com.example.authapi.util;

public class TokenVerifier {
    public static boolean verify(String token) {
        return token != null && !token.isEmpty();
    }
}