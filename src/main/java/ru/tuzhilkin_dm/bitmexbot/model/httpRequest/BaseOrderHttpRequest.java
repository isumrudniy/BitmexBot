package ru.tuzhilkin_dm.bitmexbot.model.httpRequest;

import com.google.gson.Gson;
import ru.tuzhilkin_dm.bitmexbot.model.Order;
import ru.tuzhilkin_dm.bitmexbot.util.AppСonstants;

import java.net.URI;
import java.net.http.HttpRequest;

public abstract class BaseOrderHttpRequest {

    protected static final Gson gson = new Gson();
    protected HttpRequest httpRequest;
    protected final String apiKey;
    protected final String apiSecretKey;
    protected final Order order;
    protected final String baseUrl;
    protected final String endpoint;
    protected final String httpMethod;

    protected BaseOrderHttpRequest(String apiKey, String apiSecretKey, Order order, String baseUrl, String endpoint, String httpMethod) {
        this.order = order;
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
