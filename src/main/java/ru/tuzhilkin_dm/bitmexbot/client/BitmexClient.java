package ru.tuzhilkin_dm.bitmexbot.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.tuzhilkin_dm.bitmexbot.model.Order;
import ru.tuzhilkin_dm.bitmexbot.model.OrderData;
import ru.tuzhilkin_dm.bitmexbot.model.OrderResponse;
import ru.tuzhilkin_dm.bitmexbot.model.httpRequest.DeleteOrderHttpRequest;
import ru.tuzhilkin_dm.bitmexbot.model.httpRequest.GetOrderHttpRequest;
import ru.tuzhilkin_dm.bitmexbot.model.httpRequest.PostOrderHttpRequest;
import ru.tuzhilkin_dm.bitmexbot.util.Endpoints;
import ru.tuzhilkin_dm.bitmexbot.util.Signature;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BitmexClient {
    private final HttpClient httpClient = HttpClient.newBuilder().build();
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

    public void cancelOrder(List<String> orderId) {
        DeleteOrderHttpRequest deleteOrderHttpRequest = new DeleteOrderHttpRequest(apiKey, apiSecretKey, orderId, baseUrl, Endpoints.ORDER_ENDPOINT);
        try {
            String response = httpClient.send(deleteOrderHttpRequest.getHttpRequest(), HttpResponse.BodyHandlers.ofString()).body();
            System.out.println(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void getOrders() {
        GetOrderHttpRequest getOrderHttpRequest = new GetOrderHttpRequest(apiKey, apiSecretKey, baseUrl, Endpoints.ORDER_ENDPOINT);
        try {
            String response = httpClient.send(getOrderHttpRequest.getHttpRequest(), HttpResponse.BodyHandlers.ofString()).body();
            System.out.println(response);
            Type orderListType = new TypeToken<List<OrderResponse>>() {
            }.getType();
            List<OrderResponse> orderResponseList = gson.fromJson(response, orderListType);

            for (OrderResponse orderResponse : orderResponseList) {
                System.out.println("OrderID: " + orderResponse.getOrderID());
                System.out.println("Symbol: " + orderResponse.getSymbol());
                System.out.println("WorkingIndicator: " + orderResponse.isWorkingIndicator());
                System.out.println();
            }


        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
