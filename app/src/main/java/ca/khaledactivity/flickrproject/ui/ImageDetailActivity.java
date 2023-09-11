package ca.khaledactivity.flickrproject.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ca.khaledactivity.flickrproject.R;
import ca.khaledactivity.flickrproject.models.FlickrImage;
import com.squareup.picasso.Picasso;

public class ImageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        // Find the ImageView and TextView in the layout
        ImageView imageView = findViewById(R.id.imageView);
        TextView tvMetadata = findViewById(R.id.tvMetadata);

        // Get the FlickrImage object from the intent
        FlickrImage image = (FlickrImage) getIntent().getSerializableExtra("image");

        // Check if the image object is not null
        if (image != null) {
            // load the image into the ImageView
            Picasso.get().load(image.getImageUrl()).into(imageView);

            // Set the title of the image in the TextView
            tvMetadata.setText(image.getTitle());
        }
    }

}
