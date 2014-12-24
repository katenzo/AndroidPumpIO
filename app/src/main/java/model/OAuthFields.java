package model;

/**
 * Created by noval on 12/24/14.
 */
public class OAuthFields {
    private String clientId;
    private String clientSecret;

    public OAuthFields(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
