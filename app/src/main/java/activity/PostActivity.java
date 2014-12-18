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
import android.widget.TextView;

import com.katenzo.androidpumpio.R;

import model.post.PostNote;
import model.post.PostResponse;
import se.akerfeldt.signpost.retrofit.RetrofitHttpOAuthConsumer;
import service.PumpIORestAPI;
import service.PumpIORestAdapter;

public class PostActivity extends ActionBarActivity {
    private SharedPreferences sharedPref;
    private  String clientId = "";
    private  String clientSecret = "";
    private String token = "";
    private String  tokenSecret = "";
    private String nickname;
    private PumpIORestAPI pumpIORestAPI;

    private EditText editTextNotes;
    private Button buttonPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        initPref();
        editTextNotes = (EditText) findViewById(R.id.editTextNotes);
        buttonPost = (Button) findViewById(R.id.buttonPost);

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postNotes();
            }
        });







    }

    private void postNotes() {
        RetrofitHttpOAuthConsumer retrofitHttpOAuthConsumer = new RetrofitHttpOAuthConsumer(clientId, clientSecret);
        retrofitHttpOAuthConsumer.setTokenWithSecret(token, tokenSecret);
        pumpIORestAPI = PumpIORestAdapter.getApiInterface(retrofitHttpOAuthConsumer);

        PostNote postNote = new PostNote();
        postNote.setContent(editTextNotes.getText().toString());

        PostResponse postResponse = pumpIORestAPI.postNote(nickname,postNote);




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
        nickname = "daori";

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post, menu);
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
