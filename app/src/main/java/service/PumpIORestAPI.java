package service;


import model.OAuthToken;
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
import retrofit.http.POST;
import retrofit.http.Path;

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
    void postNote(@Path("nickname") String nickname ,@Body PostNote postNote);


}
