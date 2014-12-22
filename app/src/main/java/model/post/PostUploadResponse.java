package model.post;

import com.google.gson.annotations.Expose;

/**
 * Created by garry on 12/22/14.
 */
public class PostUploadResponse {

  /*
    {
        "id": "https://pump.example/api/image/Xkdk_ffK1dkf2",
            "objectType": "image",
            "image": {
        "url": "https://pump.example/uploads/2012/12/30/ZFxf-3.jpg"
    }
        "fullImage": {
        "url": "https://pump.example/uploads/2012/12/30/ZFxf-3.jpg"
    }
    }
    */

    @Expose
    private String id;
    @Expose
    private String objectType;


    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
