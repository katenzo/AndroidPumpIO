package service;

import retrofit.RestAdapter;

/**
 * Created by katenzo on 12/9/14.
 */
public class PumpIORestAdapter {
    private static final String API_URL = "http://10.0.0.140";
    private static RestAdapter restAdapter;

    private static RestAdapter getRestAdapter() {
        if (restAdapter == null) {
            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(API_URL)
                    .build();
        }
        return restAdapter;
    }

    public static PumpIORestAPI getApiInterface() {

        PumpIORestAPI pumpIORestAPI = null;
        try {
            if (restAdapter == null) {
                restAdapter = getRestAdapter();
            }
            pumpIORestAPI = restAdapter.create(PumpIORestAPI.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pumpIORestAPI;
    }

}
