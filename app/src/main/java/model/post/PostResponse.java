
package model.post;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class PostResponse {

    @Expose
    private String verb;
    @Expose
    private Object object;
    @Expose
    private String url;
    @Expose
    private String published;
    @Expose
    private String content;

    /**
     * @return The verb
     */
    public String getVerb() {
        return verb;
    }

    /**
     * @param verb The verb
     */
    public void setVerb(String verb) {
        this.verb = verb;
    }

    /**
     * @return The object
     */
    public Object getObject() {
        return object;
    }

    /**
     * @param object The object
     */
    public void setObject(Object object) {
        this.object = object;
    }


    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The published
     */
    public String getPublished() {
        return published;
    }

    /**
     * @param published The published
     */
    public void setPublished(String published) {
        this.published = published;
    }

    /**
     * @return The content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content The content
     */
    public void setContent(String content) {
        this.content = content;
    }

}