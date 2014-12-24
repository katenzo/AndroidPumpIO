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
import model.OAuth;
import model.OAuthFields;
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


        textInfo.setText(PumpIORestAdapter.API_URL);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        Context context = getApplicationContext();
        sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        Observable<OnClickEvent> onClickEventObservable = ViewObservable.clicks(button);

        Observable<OAuthFields> oAuthFieldsObservable = onClickEventObservable.flatMap(new Func1<OnClickEvent, Observable<OAuthFields>>() {
            @Override
            public Observable<OAuthFields> call(OnClickEvent onClickEvent) {
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

                OAuthFields fields = new OAuthFields(clientId, clientSecret);
                return Observable.just(fields);
            }
        });

        Observable<RetrofitHttpOAuthConsumer> retrofitObservable = oAuthFieldsObservable.flatMap(new Func1<OAuthFields, Observable<RetrofitHttpOAuthConsumer>>() {
            @Override
            public Observable<RetrofitHttpOAuthConsumer> call(OAuthFields oAuthFields) {
                RetrofitHttpOAuthConsumer retrofitHttpOAuthConsumer = new RetrofitHttpOAuthConsumer(oAuthFields.getClientId(), oAuthFields.getClientSecret());
                retrofitHttpOAuthConsumer.setTokenWithSecret(null, null);
                return Observable.just(retrofitHttpOAuthConsumer);
            }
        });

        Subscriber<RetrofitHttpOAuthConsumer> subscriber = new Subscriber<RetrofitHttpOAuthConsumer>() {
            @Override
            public void onCompleted() {
                Intent intent = new Intent(getApplicationContext(), MainFeedActivity.class);
                intent.setAction(Intent.ACTION_VIEW);

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
                    onCompleted();
                }catch (Exception e) {
                    onError(e);
                }
            }
        };
        retrofitObservable.subscribe(subscriber);


        observTextfield();
    }


    private void observTextfield() {
        Observable<OnTextChangeEvent> textNickName = ViewObservable.text(nickName);
        Observable<OnTextChangeEvent> textPassword = ViewObservable.text(password);

        Observable.combineLatest(textNickName, textPassword, new Func2<OnTextChangeEvent, OnTextChangeEvent, Boolean>() {
            @Override
            public Boolean call(OnTextChangeEvent onNickNameChangeEvent, OnTextChangeEvent onPasswordChangeEvent) {
                String sNickName = onNickNameChangeEvent.text.toString();
                String sPassword = onPasswordChangeEvent.text.toString();

                return (sNickName.isEmpty() || sPassword.isEmpty()) ;
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if(aBoolean) {
                    button.setEnabled(false);
                } else {
                    button.setEnabled(true);
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
