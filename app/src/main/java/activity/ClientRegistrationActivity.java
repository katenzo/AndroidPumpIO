package activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.katenzo.androidpumpio.R;

import model.OAuth;
import model.OAuthField;
import model.register.Login;
import model.register.RegisterUser;
import model.registerClient.RegisterClient;
import model.registerClient.RegisterClientWithAccount;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import rx.Observable;
import rx.Subscriber;
import rx.android.events.OnClickEvent;
import rx.android.events.OnTextChangeEvent;
import rx.android.observables.ViewObservable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import se.akerfeldt.signpost.retrofit.RetrofitHttpOAuthConsumer;
import service.PumpIORestAPI;
import service.PumpIORestAdapter;


public class ClientRegistrationActivity extends ActionBarActivity {
    private static final String TAG = "PumpioAuthActivity";
    private final static String CALLBACK = PumpIORestAdapter.API_URL + "DUMMY_OAUTH_CALLBACK";
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
    private ProgressDialog loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_registration);
        nickName = (EditText) findViewById(R.id.editTextNickName);
        password = (EditText) findViewById(R.id.editTextPassword);
        textInfo = (TextView) findViewById(R.id.textInfo);
        button = (Button) findViewById(R.id.buttonLogin);
        button.setEnabled(false);

        textInfo.setText(PumpIORestAdapter.API_URL);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        Context context = getApplicationContext();
        sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);



//        TODO : Belum Async, belum bisa di coba. tinggal menggunakan show untuk menampilkan
//        dan dismiss untuk close loading indicator
//        loadingIndicator = new ProgressDialog(this);
//        loadingIndicator.setTitle("Loading");
//        loadingIndicator.setMessage("Wait A little Longer");

        createObserverTextOnChange();

        Observable<OnClickEvent> buttonClick = ViewObservable.clicks(button, false);
        buttonClick.map(new Func1<OnClickEvent, OAuthField>() {
            @Override
            public OAuthField call(OnClickEvent onClickEvent) {
                String clientId = "";
                String clientSecret = "";
                clientId = sharedPref.getString(getString(R.string.client_id), clientId);
                clientSecret = sharedPref.getString(getString(R.string.client_secret), clientSecret);
                if ("".equals(clientId)) {

                    OAuthConsumer oAuthConsumer = OAuth.getOAuthConsumerClient();
                    clientId = oAuthConsumer.getConsumerKey();
                    clientSecret = oAuthConsumer.getConsumerSecret();
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(getString(R.string.client_id), clientId);
                    editor.putString(getString(R.string.client_secret), clientSecret);
                    editor.commit();
                }

                OAuthField authField = new OAuthField(clientId, clientSecret);

                return authField;
            }
        }).map(new Func1<OAuthField, RetrofitHttpOAuthConsumer>() {
            @Override
            public RetrofitHttpOAuthConsumer call(OAuthField oAuthField) {
                RetrofitHttpOAuthConsumer retrofitHttpOAuthConsumer =
                        new RetrofitHttpOAuthConsumer(oAuthField.getClientId(), oAuthField.getClientSecret());
                retrofitHttpOAuthConsumer.setTokenWithSecret(null, null);

                return retrofitHttpOAuthConsumer;
            }
        }).subscribe(new Subscriber<RetrofitHttpOAuthConsumer>() {
            @Override
            public void onCompleted() {
                Intent intent = new Intent(getApplicationContext(), MainFeedActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
//                loadingIndicator.dismiss();
                startActivity(intent);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(RetrofitHttpOAuthConsumer retrofitHttpOAuthConsumer) {
                try {
                    pumpIORestAPI = PumpIORestAdapter.getApiInterface(retrofitHttpOAuthConsumer);

                    Login login = pumpIORestAPI.mainLogin(nickName.getText().toString(), password.getText().toString());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(getString(R.string.token), login.getToken());
                    editor.putString(getString(R.string.tokenSecret), login.getSecret());
                    editor.putString(getString(R.string.nickName), nickName.getText().toString());
                    editor.commit();
                    onCompleted();
                }catch (Throwable e){
                    onError(e);
                }
            }
        });

    }

    private void createObserverTextOnChange(){
        Observable<OnTextChangeEvent> nicknameObserve = ViewObservable.text(nickName);
        Observable<OnTextChangeEvent> passwordObserve = ViewObservable.text(password);

        Observable.combineLatest(
                nicknameObserve, passwordObserve,
                new Func2<OnTextChangeEvent, OnTextChangeEvent, Boolean>() {
                    @Override
                    public Boolean call(OnTextChangeEvent nickname, OnTextChangeEvent password) {
                        if(!nickname.text.toString().equals("") && !password.text.toString().equals("")){
                            return true;
                        }
                        return false;
                    }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if(aBoolean){
                    button.setEnabled(true);
                }else {
                    button.setEnabled(false);
                }
            }
        });

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
