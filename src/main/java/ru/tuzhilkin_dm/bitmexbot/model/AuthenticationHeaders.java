package ru.tuzhilkin_dm.bitmexbot.model;

import lombok.Data;

@Data
public class AuthenticationHeaders {
    private String expires;
    private String apiKey;
    private String signature;
}
