package ru.tuzhilkin_dm.bitmexbot.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import ru.tuzhilkin_dm.bitmexbot.model.AuthenticationHeaders;
import ru.tuzhilkin_dm.bitmexbot.model.Order;
import ru.tuzhilkin_dm.bitmexbot.model.OrderHttpRequest;
import ru.tuzhilkin_dm.bitmexbot.model.OrderRequest;
import ru.tuzhilkin_dm.bitmexbot.util.Endpoints;
import ru.tuzhilkin_dm.bitmexbot.util.Signature;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

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
        String httpMethod = "POST";
        String data;
        if (order == null) {
            data = "";
        } else {
            OrderRequest orderRequest = OrderRequest.toRequest(order);
            data = gson.toJson(orderRequest);
        }
        System.out.println(data);
        String base = "/api/v1";
        OrderHttpRequest orderHttpRequest = new OrderHttpRequest(order, baseUrl, Endpoints.ORDER_ENDPOINT, httpMethod,
                getAuthenticationHeaders(httpMethod, data, base + Endpoints.ORDER_ENDPOINT));

        try {
            String response = httpClient.send(orderHttpRequest.getHttpRequest(), HttpResponse.BodyHandlers.ofString()).body();
            System.out.println(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void cancelOrder(String orderId) {

    }

    public void getOrders() {
        String httpMethod = "GET";
        String data = "";
        String base = "/api/v1";
        OrderHttpRequest orderHttpRequest = new OrderHttpRequest(null, baseUrl, Endpoints.ORDER_ENDPOINT, httpMethod,
                getAuthenticationHeaders(httpMethod, data, base + Endpoints.ORDER_ENDPOINT));

        try {

            HttpResponse response = httpClient.send(orderHttpRequest.getHttpRequest(), HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            System.out.println(response.statusCode());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private AuthenticationHeaders getAuthenticationHeaders(String httpMethod, String data, String path) {
        long expires = System.currentTimeMillis() / 1000 + EXPIRES_DELAY;
        String signatureStr = signature.getSignature(apiSecretKey, httpMethod + path + expires + data);

        return AuthenticationHeaders.builder()
                .apiKey(apiKey)
                .signature(signatureStr)
                .expires(Long.toString(expires))
                .build();
    }

}
