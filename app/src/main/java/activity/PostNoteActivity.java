package activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.katenzo.androidpumpio.R;


import model.post.PostNote;
import model.post.PostResponse;
import model.post.Object;
import se.akerfeldt.signpost.retrofit.RetrofitHttpOAuthConsumer;
import service.PumpIORestAPI;
import service.PumpIORestAdapter;

public class PostNoteActivity extends PostActivity {
    private EditText editTextNotes;
    private EditText editTextResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        editTextNotes = (EditText) findViewById(R.id.editTextNotes);
        Button buttonPost = (Button) findViewById(R.id.buttonPost);
        editTextResponse = (EditText) findViewById(R.id.editTextResponse);
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postNotes();
            }
        });


    }

    private void postNotes() {
        RetrofitHttpOAuthConsumer retrofitHttpOAuthConsumer = getRetrofitHttpOAuthConsumer();
        PumpIORestAPI pumpIORestAPI = PumpIORestAdapter.getApiInterface(retrofitHttpOAuthConsumer);

        PostNote postNote = new PostNote();
        Object object = new Object();

        object.setContent(editTextNotes.getText().toString());


        postNote.setVerb("post");
        postNote.setObject(object);


        try {
            PostResponse postResponse = pumpIORestAPI.postNote(getNickname(), postNote);

            String responseContent = postResponse.getContent();
            editTextResponse.setVisibility(View.VISIBLE);
            editTextResponse.setText(responseContent);

        } catch (Exception exception) {
            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
        }

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
