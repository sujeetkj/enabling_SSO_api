package com.example.authapi.controller;

import com.example.authapi.dto.UserRequest;
import com.example.authapi.service.IdpClient;
import com.example.authapi.service.IdpTokenResponse;
import com.example.authapi.util.JwtGenerator;
import com.example.authapi.util.TokenVerifier;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody UserRequest request) throws Exception {
        IdpTokenResponse idpTokenResp = IdpClient.getToken();
        if (!TokenVerifier.verify(idpTokenResp.getAccessToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid IdP token");
        }

        String appToken = JwtGenerator.generateToken(request.getUserId(), idpTokenResp.getExpiresAtEpochMillis());
        return ResponseEntity.ok(Map.of("token", appToken));
    }
}