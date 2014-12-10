package model.registerapi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by katenzo on 12/9/14.
 */
public class RegisterAPIResponse {
    @SerializedName("client_id")
    private String clientId;
    @SerializedName("client_secret")
    private String clientSecret;
    @SerializedName("expire_at")
    private int expireAt;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public int getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(int expireAt) {
        this.expireAt = expireAt;
    }
}

