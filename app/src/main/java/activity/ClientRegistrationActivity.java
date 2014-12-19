package activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.katenzo.androidpumpio.R;

import model.register.Login;
import model.register.RegisterUser;
import model.registerClient.RegisterClient;
import model.registerClient.RegisterClientWithAccount;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import se.akerfeldt.signpost.retrofit.RetrofitHttpOAuthConsumer;
import service.PumpIORestAPI;
import service.PumpIORestAdapter;


public class ClientRegistrationActivity extends ActionBarActivity {
    private SharedPreferences sharedPref;
    private PumpIORestAPI pumpIORestAPI;
    private TextView textInfo;
    private RegisterClientWithAccount registerClientWithAccount;
    private RegisterClient registerClient;

    private EditText nickName;
    private EditText password;
    private Button button;
    private WebView mWebView;
    private OAuthConsumer consumer;
    private String mAuthUrl;
    private OAuthConsumer mConsumer;
    private OAuthProvider mProvider;

    private static final String TAG = "PumpioAuthActivity";
    private final static String CALLBACK = PumpIORestAdapter.API_URL + "DUMMY_OAUTH_CALLBACK";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_registration);
        nickName = (EditText) findViewById(R.id.editTextNickName);
        password = (EditText) findViewById(R.id.editTextPassword);
        textInfo = (TextView) findViewById(R.id.textInfo);
        button = (Button) findViewById(R.id.buttonLogin);
        mWebView = (WebView) findViewById(R.id.webView);


        textInfo.setText(PumpIORestAdapter.API_URL);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        Context context = getApplicationContext();
        sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callLogin();
            }
        });


    }

    private void callLogin() {

        String clientId = "";
        clientId = sharedPref.getString(getString(R.string.client_id), clientId);

        if ("".equals(clientId)) {
            clientId = "dr2bpvpTraVvyiDSN64rJQ";
        }

        String clientSecret = "";

        clientSecret = sharedPref.getString(getString(R.string.client_secret), clientSecret);

        if ("".equals(clientSecret)) {
            clientSecret = "_btRT79RCgddPd6XB6Ve4Bs-hY61-bhiVtcbz6u1Qpo";
        }

        RetrofitHttpOAuthConsumer retrofitHttpOAuthConsumer = new RetrofitHttpOAuthConsumer(clientId, clientSecret);
        retrofitHttpOAuthConsumer.setTokenWithSecret(null, null);

        RegisterUser regC = new RegisterUser();
        regC.setNickName(nickName.getText().toString());
        regC.setPassword(password.getText().toString());


        try {
            pumpIORestAPI = PumpIORestAdapter.getApiInterface(retrofitHttpOAuthConsumer);

            Login login = pumpIORestAPI.mainLogin(regC.getNickName(), regC.getPassword());
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.token), login.getToken());
            editor.putString(getString(R.string.tokenSecret), login.getSecret());
            editor.putString(getString(R.string.nickName),nickName.getText().toString());
            editor.commit();


            Intent intent = new Intent(getApplicationContext(), MainFeedActivity.class);
            intent.setAction(Intent.ACTION_VIEW);

            startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
