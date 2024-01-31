package ru.tuzhilkin_dm.bitmexbot.service;

import ru.tuzhilkin_dm.bitmexbot.util.Endpoints;

import java.net.http.HttpClient;

public class BitmexClientFactory {
    public static BitmexClient newTestnetBitmexClient(String apiKey, String apiSecretKey) {
        return new BitmexClient(apiSecretKey, apiKey, Endpoints.BASE_TEST_URL, false);
    }

    public static BitmexClient newRealBitmexClient(String apiKey, String apiSecretKey) {
        return new BitmexClient(apiSecretKey, apiKey, Endpoints.BASE_REAL_URL, true);
    }
}
