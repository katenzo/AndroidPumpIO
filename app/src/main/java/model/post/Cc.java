package model.post;

import com.google.gson.annotations.Expose;

public class Cc {

    @Expose
    private String id;
    @Expose
    private String objectType;

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

}
