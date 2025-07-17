package com.example.authapi.service;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.CloseableHttpClient;
import org.apache.hc.client5.http.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.UrlEncodedFormEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;

import java.util.*;

public class IdpClient {
    public static IdpTokenResponse getToken() throws Exception {
        HttpPost post = new HttpPost("https://idp.example.com/oauth2/token");
        List<NameValuePair> params = List.of(
            new BasicNameValuePair("grant_type", "client_credentials"),
            new BasicNameValuePair("client_id", "your-client-id"),
            new BasicNameValuePair("client_secret", "your-client-secret")
        );
        post.setEntity(new UrlEncodedFormEntity(params));

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(post)) {

            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject json = new JSONObject(responseBody);
            String token = json.getString("access_token");
            long expiresIn = json.getLong("expires_in");

            long expirationMillis = System.currentTimeMillis() + (expiresIn * 1000);
            return new IdpTokenResponse(token, expirationMillis);
        }
    }
}