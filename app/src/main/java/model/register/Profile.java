package model.register;

import com.google.gson.annotations.Expose;

public class Profile {

    @Expose
    private String preferredUsername;
    @Expose
    private String url;
    @Expose
    private String displayName;
    @Expose
    private Links links;
    @Expose
    private String objectType;
    @Expose
    private Followers followers;
    @Expose
    private Following following;
    @Expose
    private Favorites favorites;
    @Expose
    private Lists lists;
    @Expose
    private String updated;
    @Expose
    private String id;

    /**
     *
     * @return
     * The preferredUsername
     */
    public String getPreferredUsername() {
        return preferredUsername;
    }

    /**
     *
     * @param preferredUsername
     * The preferredUsername
     */
    public void setPreferredUsername(String preferredUsername) {
        this.preferredUsername = preferredUsername;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     * The displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     * The links
     */
    public Links getLinks() {
        return links;
    }

    /**
     *
     * @param links
     * The links
     */
    public void setLinks(Links links) {
        this.links = links;
    }

    /**
     *
     * @return
     * The objectType
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     *
     * @param objectType
     * The objectType
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    /**
     *
     * @return
     * The followers
     */
    public Followers getFollowers() {
        return followers;
    }

    /**
     *
     * @param followers
     * The followers
     */
    public void setFollowers(Followers followers) {
        this.followers = followers;
    }

    /**
     *
     * @return
     * The following
     */
    public Following getFollowing() {
        return following;
    }

    /**
     *
     * @param following
     * The following
     */
    public void setFollowing(Following following) {
        this.following = following;
    }

    /**
     *
     * @return
     * The favorites
     */
    public Favorites getFavorites() {
        return favorites;
    }

    /**
     *
     * @param favorites
     * The favorites
     */
    public void setFavorites(Favorites favorites) {
        this.favorites = favorites;
    }

    /**
     *
     * @return
     * The lists
     */
    public Lists getLists() {
        return lists;
    }

    /**
     *
     * @param lists
     * The lists
     */
    public void setLists(Lists lists) {
        this.lists = lists;
    }

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
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

}