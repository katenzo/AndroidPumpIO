package activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;

import com.katenzo.androidpumpio.R;

import se.akerfeldt.signpost.retrofit.RetrofitHttpOAuthConsumer;
import service.PumpIORestAPI;

/**
 * Created by garry on 12/18/14.
 */
public class PostActivity extends ActionBarActivity {
    private SharedPreferences sharedPref;
    private  String clientId = "";
    private  String clientSecret = "";
    private String token = "";
    private String  tokenSecret = "";
    private String nickname;

    public SharedPreferences getSharedPref() {
        return sharedPref;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getToken() {
        return token;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public String getNickname() {
        return nickname;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        initPref();
    }


    private void initPref() {
        Context context = getApplicationContext();
        sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);


        clientId = sharedPref.getString(getString(R.string.client_id), clientId);

        if ("".equals(clientId)) {
            clientId = "dr2bpvpTraVvyiDSN64rJQ";
        }



        clientSecret = sharedPref.getString(getString(R.string.client_secret), clientSecret);

        if ("".equals(clientSecret)) {
            clientSecret = "_btRT79RCgddPd6XB6Ve4Bs-hY61-bhiVtcbz6u1Qpo";
        }

        token = sharedPref.getString(getString(R.string.token), token);
        tokenSecret = sharedPref.getString(getString(R.string.tokenSecret), tokenSecret);
        nickname = "katenzo";

    }

    public RetrofitHttpOAuthConsumer getRetrofitHttpOAuthConsumer() {
        RetrofitHttpOAuthConsumer retrofitHttpOAuthConsumer = new RetrofitHttpOAuthConsumer(getClientId(), getClientSecret());
        retrofitHttpOAuthConsumer.setTokenWithSecret(getToken(), getTokenSecret());
        return retrofitHttpOAuthConsumer;
    }


}
