package service;


import model.register.RegisterPumpIo;
import model.register.RegisterPumpIoResponse;
import model.registerapi.RegisterAPI;
import model.registerapi.RegisterAPIResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface PumpIORestAPI {
    @POST("/api/client/registerAPI")
    void registerAPI(@Body RegisterAPI register, Callback<RegisterAPIResponse> registerAPIResponseCallback);

    @POST("/api/users")
    void register(@Body RegisterPumpIo registerPumpIo, Callback<RegisterPumpIoResponse> registerPumpIoResponseCallback) ;
}
