package ru.tuzhilkin_dm.bitmexbot.model;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpRequest;

public class OrderHttpRequest {

    private static final Gson gson = new Gson();
    private HttpRequest httpRequest;
    private final Order order;

    //Вынести в отдельный класс!

    public OrderHttpRequest(Order order, String baseUrl, String endpoint, String httpMethod, AuthenticationHeaders authenticationHeaders) {
        this.order = order;
        createHttpRequest(baseUrl, endpoint, httpMethod, authenticationHeaders);
        // вынести в отдельный метод

    }

    private void createHttpRequest(String baseUrl, String endpoint, String httpMethod, AuthenticationHeaders authenticationHeaders) {
        String data;
        if (order == null) {
            data = "";
        } else {
            OrderRequest orderRequest = OrderRequest.toRequest(order);
            data = gson.toJson(orderRequest);
        }

        HttpRequest.BodyPublisher bodyPublisher = httpMethod.equals("GET") ? HttpRequest.BodyPublishers.noBody()
                : HttpRequest.BodyPublishers.ofString(data);

        httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .method(httpMethod, bodyPublisher)
                .header("api-key", authenticationHeaders.getApiKey())
                .header("api-signature", authenticationHeaders.getSignature())
                .header("api-expires", authenticationHeaders.getExpires())
                .header("Content-Type", "application/json")
                .build();
    }

    public HttpRequest getHttpRequest() {
        return httpRequest;
    }

}
