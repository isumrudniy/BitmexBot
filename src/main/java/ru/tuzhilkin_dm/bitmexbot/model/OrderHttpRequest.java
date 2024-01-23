package ru.tuzhilkin_dm.bitmexbot.model;

import java.net.URI;
import java.net.http.HttpRequest;

public class OrderHttpRequest {
    private HttpRequest httpRequest;
    private final Order order;

    public OrderHttpRequest(Order order, String baseUrl, String endpoint, String httpMethod, AuthenticationHeaders authenticationHeaders) {
        // вынести в отдельный метод
        String data;
        if (order == null) {
            data = "";
        } else {
            data = "";
        }

        HttpRequest.BodyPublisher bodyPublisher = httpMethod.equals("GET") ? HttpRequest.BodyPublishers.noBody()
                : HttpRequest.BodyPublishers.ofString(data);

        httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .method(httpMethod, bodyPublisher)
                .header("api-key", authenticationHeaders.getApiKey())
                .header("api-signature", authenticationHeaders.getSignature())
                .header("api-expires", authenticationHeaders.getExpires())
                .build();
        this.order = order;
    }
}
