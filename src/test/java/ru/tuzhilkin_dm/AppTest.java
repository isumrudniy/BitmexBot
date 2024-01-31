package ru.tuzhilkin_dm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import ru.tuzhilkin_dm.bitmexbot.client.BitmexClient;
import ru.tuzhilkin_dm.bitmexbot.client.BitmexClientFactory;
import ru.tuzhilkin_dm.bitmexbot.model.httpRequest.GetOrderHttpRequest;
import ru.tuzhilkin_dm.bitmexbot.model.httpRequest.PostOrderHttpRequest;
import ru.tuzhilkin_dm.bitmexbot.util.AppСonstants;
import ru.tuzhilkin_dm.bitmexbot.util.Endpoints;

import java.io.IOException;
import java.net.http.HttpResponse;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void testConnection() {
        BitmexClient bitmexClient = BitmexClientFactory.newTestnetBitmexClient(AppСonstants.API_KEY, AppСonstants.API_SECRET_KEY);
        assertEquals(getOrders(bitmexClient), 200);
    }

    public Integer getOrders(BitmexClient bitmexClient) {
        GetOrderHttpRequest getOrderHttpRequest = new GetOrderHttpRequest(bitmexClient.getApiKey(), bitmexClient.getApiSecretKey(), bitmexClient.getBaseUrl(), Endpoints.ORDER_ENDPOINT);
        try {
            HttpResponse response = bitmexClient.getHttpClient().send(getOrderHttpRequest.getHttpRequest(), HttpResponse.BodyHandlers.ofString());
            return response.statusCode();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
