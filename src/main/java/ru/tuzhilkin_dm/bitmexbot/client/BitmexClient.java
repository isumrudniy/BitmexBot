package ru.tuzhilkin_dm.bitmexbot.client;

import com.google.gson.Gson;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.tuzhilkin_dm.bitmexbot.model.Order;
import ru.tuzhilkin_dm.bitmexbot.model.httpRequest.GetOrderHttpRequest;
import ru.tuzhilkin_dm.bitmexbot.model.httpRequest.PostOrderHttpRequest;
import ru.tuzhilkin_dm.bitmexbot.util.Endpoints;
import ru.tuzhilkin_dm.bitmexbot.util.Signature;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

@Data
@RequiredArgsConstructor
public class BitmexClient {
    private final HttpClient httpClient = HttpClient.newBuilder().build();
    private final Signature signature = new Signature();
    private final String apiSecretKey;
    private final String apiKey;
    private final String baseUrl;
    private final boolean isReal;

    private static final int EXPIRES_DELAY = 10;

    private final Gson gson = new Gson();

    public void sendOrder(Order order) {
        PostOrderHttpRequest postOrderHttpRequest = new PostOrderHttpRequest(apiKey, apiSecretKey, order, baseUrl, Endpoints.ORDER_ENDPOINT);
        try {
            String response = httpClient.send(postOrderHttpRequest.getHttpRequest(), HttpResponse.BodyHandlers.ofString()).body();
            System.out.println(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOrder(String orderId) {

    }

    public void getOrders() {
        GetOrderHttpRequest getOrderHttpRequest = new GetOrderHttpRequest(apiKey, apiSecretKey, baseUrl, Endpoints.ORDER_ENDPOINT);
        try {
            String response = httpClient.send(getOrderHttpRequest.getHttpRequest(), HttpResponse.BodyHandlers.ofString()).body();
            System.out.println(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
