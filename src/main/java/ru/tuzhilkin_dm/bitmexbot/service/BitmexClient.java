package ru.tuzhilkin_dm.bitmexbot.service;

import lombok.RequiredArgsConstructor;
import ru.tuzhilkin_dm.bitmexbot.util.Signature;

import java.net.http.HttpClient;

@RequiredArgsConstructor
public class BitmexClient {
    private final HttpClient httpClient = HttpClient.newBuilder().build();
    private final Signature signature = new Signature();
    private final String baseUrl;
    private final boolean isReal;
    public void sendOrder() {

    }

    public void cancelOrder() {

    }
}
