package com.gateway.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private  String userId;
    private String accessToken;
    private String refreshToken;

    private long expireAt;
    private Collection<String> authorities;
}
