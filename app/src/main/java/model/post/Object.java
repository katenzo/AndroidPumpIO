
package model.post;

import com.google.gson.annotations.Expose;

public class Object {

    @Expose
    private String objectType;
    @Expose
    private String content;

    @Expose
    private String id;


    /**
     *
     * @return
     * The objectType
     */
    public String getObjectType() {
        return objectType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
     * The content
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @param content
     * The content
     */
    public void setContent(String content) {
        this.content = content;
    }

}
