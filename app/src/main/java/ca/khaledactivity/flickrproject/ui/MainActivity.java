package ca.khaledactivity.flickrproject.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ca.khaledactivity.flickrproject.api.FlickrApi;
import ca.khaledactivity.flickrproject.api.FlickrApiClient;
import ca.khaledactivity.flickrproject.models.FlickrImage;
import ca.khaledactivity.flickrproject.models.FlickrResponse;
import ca.khaledactivity.flickrproject.utils.InternetCheck;
import ca.khaledactivity.flickrproject.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String API_KEY = "0304074245dee520f96b6c6d73861d73";
    private static final int REQUEST_CODE = 100;

    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Display the username at the top of the page
        String username = getIntent().getStringExtra("username");

        // Show the username in the TextView
        TextView textViewUsername = findViewById(R.id.textViewUsername);
        textViewUsername.setText("Welcome, " + username + "!");

        // Initialize RecyclerView and Adapter
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);

        //Grid Photo Layout
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        if (InternetCheck.isOnline(this)) {
            fetchImages();
        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

        //Upload photo button
        Button buttonUpload = findViewById(R.id.buttonUpload);
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to pick an image from the device's gallery
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                // Start the activity to pick an image and specify the request code
                startActivityForResult(intent, REQUEST_CODE);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Upload photo
        super.onActivityResult(requestCode, resultCode, data);

       // Check if the request code matches the one used to start the activity,
        // and the result is successful and contains data
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Get the selected image URI
            Uri selectedImageUri = data.getData();

            // Create a FlickrImage object with the selected image URI
            FlickrImage flickrImage = new FlickrImage();
            flickrImage.setImageUrl(selectedImageUri.toString());
            flickrImage.setTitle("New Image"); // Set a default title if needed

            // Add the image at the beginning of the RecyclerView
            adapter.addImageAtStart(flickrImage);
        }
    }



    private void fetchImages() {
        // Create an instance of the FlickrApi using the FlickrApiClient
        FlickrApi api = FlickrApiClient.getApi();

        // Create a Call object for the getRecentPhotos API method with the API key and extras
        Call<FlickrResponse> call = api.getRecentPhotos(API_KEY, "url_s");

        // Enqueue the API call asynchronously
        call.enqueue(new Callback<FlickrResponse>() {
            @Override
           // Retrofit onResponse() to receive and parse the JSON response as Array of objects
            public void onResponse(Call<FlickrResponse> call, Response<FlickrResponse> response) {
                // Check if the API call was successful and the response body is not null
                if (response.isSuccessful() && response.body() != null) {
                    // Update the RecyclerView adapter with the fetched images
                    adapter.setImages(response.body().getPhotos().getImages());
                }
            }
            @Override
            public void onFailure(Call<FlickrResponse> call, Throwable t) {
                // Handle the failure of the API call by showing a Toast message
                Toast.makeText(MainActivity.this, "Failed to fetch images", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
