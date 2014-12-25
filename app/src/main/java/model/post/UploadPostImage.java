package model.post;

import com.google.gson.annotations.Expose;

import retrofit.mime.TypedFile;

/**
 * Created by garry on 12/22/14.
 */
public class UploadPostImage {
    @Expose
    private String title;
    @Expose
    private String description;

    @Expose
    private TypedFile qqfile;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypedFile getQqfile() {
        return qqfile;
    }

    public void setQqfile(TypedFile qqfile) {
        this.qqfile = qqfile;
    }
}
