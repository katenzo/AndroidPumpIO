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

public class PostActivity extends ActionBarActivity {
    private SharedPreferences sharedPref;
    private  String clientId = "";
    private  String clientSecret = "";
    private String token = "";
    private String  tokenSecret = "";

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



    }

    private void initPref() {
        Context context = getApplicationContext();
        sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);


        clientId = sharedPref.getString(getString(R.string.client_id), clientId);

        if ("".equals(clientId)) {
            clientId = "_OcSJ3Wi8BVDwIrr6wPdqA";
        }



        clientSecret = sharedPref.getString(getString(R.string.client_secret), clientSecret);

        if ("".equals(clientSecret)) {
            clientSecret = "ZLAwmRasc5JQjemXi2piSZ36u1SMLGKXWRomOHlGRMg";
        }

        token = sharedPref.getString(getString(R.string.token), token);
        token = sharedPref.getString(getString(R.string.tokenSecret), tokenSecret);

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
