package com.example.mob_dev_tp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
   ImageView image;
   EditText text;
  final  int Request_code = 1;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.image); // Replace with actual ID from your layout
        text = findViewById(R.id.text);
    }
    public void select(View view){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(i, "Select Picture"),
                Request_code);
    }
    public void shareImg(View view){

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("image/*");;
        i.putExtra(Intent.EXTRA_STREAM, selectedImageUri);
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);;
        startActivity(i);
    }

    public void shareText(View view){

        String message = text.getText().toString();
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(i);
    }
    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data)
    {
        super.onActivityResult(requestCode, resultCode,
                data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == Request_code) {
                // Get the url of the image from data
                 selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the
                    // layout
                    image.setImageURI(
                            selectedImageUri);
                }
            }
        }
    }
}




