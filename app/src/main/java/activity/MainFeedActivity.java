package activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.katenzo.androidpumpio.R;


public class MainFeedActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);

        Button buttonPostingNote = (Button) findViewById(R.id.buttonPostingNote);
        Button buttonPostingImage = (Button) findViewById(R.id.buttonPostingImage);

        buttonPostingNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 postingNote();
            }
        });

        buttonPostingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postingImage();
            }
        });

    }


    private void postingNote() {
        Intent intent = new Intent(getApplicationContext(), PostNoteActivity.class);
        intent.setAction(Intent.ACTION_VIEW);

        startActivity(intent);
    }

    private void postingImage() {

        Intent intent = new Intent(getApplicationContext(), PostImageActivity.class);
        intent.setAction(Intent.ACTION_VIEW);

        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_feed, menu);
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
