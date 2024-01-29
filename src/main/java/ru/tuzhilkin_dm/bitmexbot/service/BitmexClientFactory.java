package ru.tuzhilkin_dm.bitmexbot.service;

import ru.tuzhilkin_dm.bitmexbot.util.Endpoints;

import java.net.http.HttpClient;

public class BitmexClientFactory {
    public BitmexClient newTestnetBitmexClient(String apiSecretKey, String apiKey) {
        return new BitmexClient(apiSecretKey, apiKey, Endpoints.BASE_TEST_URL, false);
    }

    public BitmexClient newRealBitmexClient(String apiSecretKey, String apiKey) {
        return new BitmexClient(apiSecretKey, apiKey, Endpoints.BASE_REAL_URL, true);
    }
}
