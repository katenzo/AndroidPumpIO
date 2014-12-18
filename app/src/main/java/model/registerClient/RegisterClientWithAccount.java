package model.registerClient;

import com.google.gson.annotations.SerializedName;

/**
 * Created by katenzo on 12/12/14.
 */
public class RegisterClientWithAccount extends RegisterClient {
    @SerializedName("nickname")
    private String nickName;
    @SerializedName("password")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


}
