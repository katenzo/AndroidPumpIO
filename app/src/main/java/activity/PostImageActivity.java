package activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.katenzo.androidpumpio.R;
import com.squareup.picasso.Picasso;

import java.io.File;

import model.post.*;
import model.post.Object;
import retrofit.mime.TypedFile;
import se.akerfeldt.signpost.retrofit.RetrofitHttpOAuthConsumer;
import service.PumpIORestAPI;
import service.PumpIORestAdapter;


public class PostImageActivity extends PostActivity {

    private final int SELECT_PICTURE = 1;

    private String selectedImagePath;

    private ImageView imageViewPost;
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Button buttonPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_image);

        imageViewPost = (ImageView) findViewById(R.id.imageViewPost);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        buttonPost = (Button) findViewById(R.id.buttonPost);
        buttonPost.setEnabled(false);
        editTextDescription.setEnabled(false);
        editTextTitle.setEnabled(false);
        

        Button buttonSelectImage = (Button) findViewById(R.id.buttonSelect);
        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });


        buttonPost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                 postImages();


            }
        });




    }


    private void postImages() {
        RetrofitHttpOAuthConsumer retrofitHttpOAuthConsumer = getRetrofitHttpOAuthConsumer();
        PumpIORestAPI pumpIORestAPI = PumpIORestAdapter.getApiInterface(retrofitHttpOAuthConsumer);

        File photo = new File(selectedImagePath);
<<<<<<< HEAD
        String extension = MimeTypeMap.getFileExtensionFromUrl(selectedImagePath);

        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);

        TypedFile typedFilePhoto = new TypedFile(mimeType, photo);

        try {
            PostUploadResponse postResponse = pumpIORestAPI.uploadPostImage(
=======
        TypedFile typedFilePhoto = new TypedFile("image/jpeg", photo);

        try {
            PostResponse postResponse = pumpIORestAPI.uploadImageOnly(
>>>>>>> FETCH_HEAD
                    getNickname(),
                    typedFilePhoto
            );

            Object object = new Object();

            object.setId(postResponse.getId());
            object.setObjectType(postResponse.getObjectType());


            PostImage postImage = new PostImage();

            postImage.setVerb("post");
            postImage.setObject(object);

            PostResponse postingResponse = pumpIORestAPI.postImage(
                    getNickname(), postImage);


            Toast.makeText(getApplicationContext(), "Posting Image done", Toast.LENGTH_LONG).show();
            editTextDescription.setText("");
            editTextDescription.setEnabled(false);
            editTextTitle.setText("");
            editTextTitle.setEnabled(false);
            buttonPost.setEnabled(false);
            imageViewPost.setImageResource(R.drawable.ic_launcher);

        } catch (Exception exception) {
            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                Picasso.with(getApplicationContext()).load(selectedImageUri).into(imageViewPost);
                selectedImagePath = getPath(selectedImageUri);
                buttonPost.setEnabled(true);
                editTextDescription.setEnabled(true);
                editTextTitle.setEnabled(true);
            }
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post_image, menu);
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
