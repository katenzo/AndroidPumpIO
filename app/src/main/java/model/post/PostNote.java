package model.post;

/**
 * Created by katenzo on 12/17/14.
 */
public class PostNote {
    String objectType ="note";
    String content;

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
