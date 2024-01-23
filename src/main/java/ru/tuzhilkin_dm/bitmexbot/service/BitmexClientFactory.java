package ru.tuzhilkin_dm.bitmexbot.service;

import ru.tuzhilkin_dm.bitmexbot.util.Endpoints;

import java.net.http.HttpClient;

public class BitmexClientFactory {
    public BitmexClient newTestnetBitmexClient() {
        return new BitmexClient(Endpoints.BASE_TEST_URL, false);
    }

    public BitmexClient newRealBitmexClient() {
        return new BitmexClient(Endpoints.BASE_REAL_URL, true);
    }
}
