package com.example.authapi.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.*;

import java.util.Date;

public class JwtGenerator {
    private static final String SECRET = "supersecretkey1234567890";

    public static String generateToken(String userId, long expMillis) throws JOSEException {
        JWSSigner signer = new MACSigner(SECRET.getBytes());

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(userId)
                .issuer("your-app")
                .issueTime(new Date())
                .expirationTime(new Date(expMillis))
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }
}