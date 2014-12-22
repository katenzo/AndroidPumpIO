package model.post;

import com.google.gson.annotations.Expose;

/**
 * Created by garry on 12/22/14.
 */
public class PostImage  {

    @Expose
    private String verb;
    @Expose
    private Object object;

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
