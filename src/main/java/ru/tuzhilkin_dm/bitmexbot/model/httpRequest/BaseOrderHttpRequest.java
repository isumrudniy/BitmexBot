package ru.tuzhilkin_dm.bitmexbot.model.httpRequest;

import com.google.gson.Gson;
import ru.tuzhilkin_dm.bitmexbot.model.Order;
import ru.tuzhilkin_dm.bitmexbot.util.AppСonstants;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

public abstract class BaseOrderHttpRequest {

    protected static final Gson gson = new Gson();
    protected HttpRequest httpRequest;
    protected final String apiKey;
    protected final String apiSecretKey;
    protected final Order order;

    protected final List<String> orderID;
    protected final String baseUrl;
    protected final String endpoint;
    protected final String httpMethod;

    protected BaseOrderHttpRequest(String apiKey, String apiSecretKey, Order order, List<String> orderID, String baseUrl,
                                   String endpoint, String httpMethod) {
        this.order = order;
        this.orderID = orderID;
        this.apiKey = apiKey;
        this.apiSecretKey = apiSecretKey;
        this.baseUrl = baseUrl;
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
        createHttpRequest();
    }

    protected abstract void createHttpRequest();

    public HttpRequest getHttpRequest() {
        return httpRequest;
    }

}
