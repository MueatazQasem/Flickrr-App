package ca.khaledactivity.flickrproject.ui;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import ca.khaledactivity.flickrproject.R;

public class BiggerImageActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigger_image);

        imageView = findViewById(R.id.imageView);

        // Get the image URL from the intent
        String imageUrl = getIntent().getStringExtra("imageUrl");

        // Load the image using Picasso or any other image loading library
        Picasso.get().load(imageUrl).into(imageView);
    }
}
