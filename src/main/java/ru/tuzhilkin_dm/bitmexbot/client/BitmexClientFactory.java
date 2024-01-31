package ru.tuzhilkin_dm.bitmexbot.client;

import ru.tuzhilkin_dm.bitmexbot.util.Endpoints;

public class BitmexClientFactory {
    public static BitmexClient newTestnetBitmexClient(String apiKey, String apiSecretKey) {
        return new BitmexClient(apiSecretKey, apiKey, Endpoints.BASE_TEST_URL, false);
    }

    public static BitmexClient newRealBitmexClient(String apiKey, String apiSecretKey) {
        return new BitmexClient(apiSecretKey, apiKey, Endpoints.BASE_REAL_URL, true);
    }
}
