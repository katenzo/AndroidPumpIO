package model.register;


import com.google.gson.annotations.Expose;


public class Login {

    @Expose
    private String updated;
    @Expose
    private String published;
    @Expose
    private Profile profile;
    @Expose
    private String nickname;
    @Expose
    private String token;
    @Expose
    private String secret;

    /**
     *
     * @return
     * The updated
     */
    public String getUpdated() {
        return updated;
    }

    /**
     *
     * @param updated
     * The updated
     */
    public void setUpdated(String updated) {
        this.updated = updated;
    }

    /**
     *
     * @return
     * The published
     */
    public String getPublished() {
        return published;
    }

    /**
     *
     * @param published
     * The published
     */
    public void setPublished(String published) {
        this.published = published;
    }

    /**
     *
     * @return
     * The profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     *
     * @param profile
     * The profile
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     *
     * @return
     * The nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *
     * @param nickname
     * The nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     *
     * @return
     * The token
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     * The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     *
     * @return
     * The secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     *
     * @param secret
     * The secret
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

}
