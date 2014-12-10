package activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.katenzo.androidpumpio.R;

import model.register.Profile;
import model.register.RegisterPumpIo;
import model.register.RegisterPumpIoResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import service.PumpIORestAPI;
import service.PumpIORestAdapter;


public class RegistrationActivity extends ActionBarActivity {
    private SharedPreferences sharedPref;

    private EditText nickname;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Context context = getApplicationContext();
        sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        nickname = (EditText) findViewById(R.id.editTextNickName);
        password = (EditText) findViewById(R.id.editTextPassword);

        Button buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });



    }


    private void registerUser() {
        PumpIORestAPI pumpIORestAPI = PumpIORestAdapter.getApiInterface();

        RegisterPumpIo registerPumpIo =  new RegisterPumpIo();
        registerPumpIo.setNickName(nickname.getText().toString());
        registerPumpIo.setPassword(password.getText().toString());

        pumpIORestAPI.register(registerPumpIo, new Callback<RegisterPumpIoResponse>() {

            @Override
            public void success(RegisterPumpIoResponse registerPumpIoResponse, Response response) {
                String nickname  = registerPumpIoResponse.getNickname();
                Profile profile = registerPumpIoResponse.getProfile();
                String secret = registerPumpIoResponse.getSecret();



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
