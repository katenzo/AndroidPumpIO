package service;


import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import se.akerfeldt.signpost.retrofit.RetrofitHttpOAuthConsumer;
import se.akerfeldt.signpost.retrofit.SigningOkClient;

/**
 * Created by katenzo on 12/9/14.
 */
public class PumpIORestAdapter {
    public static final String API_URL = "http://dev-netzme.duckdns.org:8888";//http://10.0.0.140";//"https://pumprock.net/";
    private static RestAdapter restAdapter;

    private static RestAdapter getRestAdapter() {
    //    if (restAdapter == null) {
            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(API_URL)
                    .build();
      //  }
        return restAdapter;
    }

    private static RestAdapter getRestAdapter(RetrofitHttpOAuthConsumer retrofitHttpOAuthConsumer) {
       // if (restAdapter == null) {
            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setClient(new SigningOkClient(retrofitHttpOAuthConsumer))
                    .setEndpoint(API_URL)
                    .build();
   //     }
        return restAdapter;
    }



    public static PumpIORestAPI getApiInterface(RetrofitHttpOAuthConsumer retrofitHttpOAuthConsumer) {

        PumpIORestAPI pumpIORestAPI = null;
        try {
                restAdapter = getRestAdapter(retrofitHttpOAuthConsumer);

            pumpIORestAPI = restAdapter.create(PumpIORestAPI.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pumpIORestAPI;
    }

    public static PumpIORestAPI getApiInterface() {

        PumpIORestAPI pumpIORestAPI = null;
        try {
           // if (restAdapter == null) {
                restAdapter = getRestAdapter();
           // }
            pumpIORestAPI = restAdapter.create(PumpIORestAPI.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pumpIORestAPI;
    }

}
