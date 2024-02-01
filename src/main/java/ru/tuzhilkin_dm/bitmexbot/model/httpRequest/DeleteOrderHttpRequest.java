package ru.tuzhilkin_dm.bitmexbot.model.httpRequest;

import ru.tuzhilkin_dm.bitmexbot.model.AuthenticationHeaders;
import ru.tuzhilkin_dm.bitmexbot.model.Order;
import ru.tuzhilkin_dm.bitmexbot.model.OrderData;
import ru.tuzhilkin_dm.bitmexbot.model.OrderRequest;
import ru.tuzhilkin_dm.bitmexbot.util.AppСonstants;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

public class DeleteOrderHttpRequest extends BaseOrderHttpRequest {

    public DeleteOrderHttpRequest(String apiKey, String apiSecretKey, List<String> orderId, String baseUrl, String endpoint) {
        super(apiKey, apiSecretKey, null, orderId, baseUrl, endpoint, AppСonstants.DELETE);
    }

    @Override
    protected void createHttpRequest() {
        String data = "";
        if (orderID != null) {
            OrderData orderData = new OrderData();
            orderData.setOrderID(orderID);
            data = gson.toJson(orderData);
        }

        AuthenticationHeaders authenticationHeaders = new AuthenticationHeaders(apiKey, apiSecretKey,
                httpMethod, AppСonstants.BASE + endpoint, data);

        httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .method(httpMethod, HttpRequest.BodyPublishers.ofString(data))
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
