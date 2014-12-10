package activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.katenzo.androidpumpio.R;

import model.Constanta;
import model.registerapi.RegisterAPI;
import model.registerapi.RegisterAPIResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import service.PumpIORestAPI;
import service.PumpIORestAdapter;


public class ClientRegistrationActivity extends ActionBarActivity {
    private SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_registration);


        Context context = getApplicationContext();
        sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String client_id = getResources().getString(R.string.client_id);
        String clientId = sharedPref.getString(getString(R.string.client_id), client_id);
        String client_secret = getResources().getString(R.string.client_secret);
        String clientSecret = sharedPref.getString(getString(R.string.client_id), client_secret);
        Toast.makeText(getApplicationContext(),clientSecret,Toast.LENGTH_LONG).show();

        PumpIORestAPI pumpIORestAPI = PumpIORestAdapter.getApiInterface();
        RegisterAPI register = new RegisterAPI();
        register.setType(Constanta.REGISTER_CLIENT_ASSOCIATE);
        register.setApplicationType(Constanta.REGISTER_APPLICATION_TYPE_NATIVE);
        register.setApplicationName(Constanta.REGISTER_APPLICATION_NAME);

        pumpIORestAPI.registerAPI(register, new Callback<RegisterAPIResponse>() {
            @Override
            public void success(RegisterAPIResponse registerResponse, Response response) {
                if (response.getStatus() == Constanta.RESPONSE_STATUS_OK) {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(getString(R.string.client_id), registerResponse.getClientId());
                    editor.putString(getString(R.string.client_secret), registerResponse.getClientSecret());
                    editor.commit();
                    Toast.makeText(getApplicationContext(), registerResponse.getClientId(), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                    intent.setAction(Intent.ACTION_VIEW);

                    startActivity(intent);

                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
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
