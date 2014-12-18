package model;

/**
 * Created by katenzo on 12/11/14.
 */
public class OAuthToken {

    String token;



    String tokenSecret;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

}
