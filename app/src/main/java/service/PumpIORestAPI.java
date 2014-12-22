package service;


import model.OAuthToken;
import model.post.PostResponse;
import model.register.Login;
import model.register.RegisterUser;
import model.register.RegisterUserResponse;
import model.post.PostNote;
import model.registerClient.RegisterApplication;
import model.registerClient.RegisterClient;
import model.registerClient.RegisterClientWithAccount;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;

public interface PumpIORestAPI {
    @POST("/api/client/register")
    void registerClient(@Body RegisterApplication register, Callback<RegisterClient> registerClientResponseCallback);

    @POST("/api/client/register")
    RegisterClient getRegisterClient(@Body RegisterApplication register);

    @POST("/api/users")
    void registerUser(@Body RegisterUser registerUser, Callback<RegisterUserResponse> registerPumpIoResponseCallback) ;

    @POST("/oauth/request_token")
    void getOAuthToken(@Body RegisterClientWithAccount registerClient,Callback<OAuthToken> oAuthGetTokenCallback);

    @POST("/main/login")
    OAuthToken getToken(@Body RegisterUser registerClient);

    @FormUrlEncoded
    @POST("/main/login")
    Login mainLogin(@Field("nickname") String nickname, @Field("password") String password);

    @POST("/api/user/{nickname}/feed")
    PostResponse postNote(@Path("nickname") String nickname ,@Body PostNote postNote);

    @FormUrlEncoded
    @POST("/api/user/{nickname}/uploads")
    PostResponse uploadImage(@Path("nickname") String nickname,@Field("title") String title, @Field("description") String description, @Field("qqfile") TypedFile photo);


}
