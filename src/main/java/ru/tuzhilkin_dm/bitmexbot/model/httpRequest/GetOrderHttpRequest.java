package ru.tuzhilkin_dm.bitmexbot.model.httpRequest;

import ru.tuzhilkin_dm.bitmexbot.model.AuthenticationHeaders;
import ru.tuzhilkin_dm.bitmexbot.model.Order;
import ru.tuzhilkin_dm.bitmexbot.model.OrderRequest;
import ru.tuzhilkin_dm.bitmexbot.util.AppСonstants;

import java.net.URI;
import java.net.http.HttpRequest;

public class GetOrderHttpRequest extends BaseOrderHttpRequest {

    public GetOrderHttpRequest(String apiKey, String apiSecretKey, String baseUrl, String endpoint) {
        super(apiKey, apiSecretKey, null, baseUrl, endpoint, AppСonstants.GET);
    }

    @Override
    protected void createHttpRequest() {
        String data = "";
        AuthenticationHeaders authenticationHeaders = new AuthenticationHeaders(apiKey, apiSecretKey, httpMethod, AppСonstants.BASE + endpoint, data);

        httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .method(httpMethod, HttpRequest.BodyPublishers.noBody())
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
