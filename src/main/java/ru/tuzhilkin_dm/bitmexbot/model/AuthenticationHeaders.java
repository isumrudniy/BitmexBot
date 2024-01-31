package ru.tuzhilkin_dm.bitmexbot.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tuzhilkin_dm.bitmexbot.util.AppСonstants;
import ru.tuzhilkin_dm.bitmexbot.util.Signature;

@Data
public class AuthenticationHeaders {
    private String expires;
    private String apiKey;
    private String signature;

    private final Signature signatureObject = new Signature();

    public AuthenticationHeaders(String apiKey, String apiSecretKey, String httpMethod, String path, String data) {
        long expires = System.currentTimeMillis() / 1000 + AppСonstants.EXPIRES_DELAY;
        String signatureStr = signatureObject.getSignature(apiSecretKey, httpMethod + path + expires + data);
        this.expires = Long.toString(expires);
        this.apiKey = apiKey;
        this.signature = signatureStr;
    }

}
