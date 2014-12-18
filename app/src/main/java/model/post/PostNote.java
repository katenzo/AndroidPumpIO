package model.post;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class PostNote {

    @Expose
    private String verb;
    @Expose
    private Object object;
    @Expose
    private List<To> to = new ArrayList<To>();
    @Expose
    private List<Cc> cc = new ArrayList<Cc>();

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
     * @return The to
     */
    public List<To> getTo() {
        return to;
    }

    /**
     * @param to The to
     */
    public void setTo(List<To> to) {
        this.to = to;
    }

    /**
     * @return The cc
     */
    public List<Cc> getCc() {
        return cc;
    }

    /**
     * @param cc The cc
     */
    public void setCc(List<Cc> cc) {
        this.cc = cc;
    }

}

