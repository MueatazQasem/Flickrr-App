package ca.khaledactivity.flickrproject.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ca.khaledactivity.flickrproject.R;
import ca.khaledactivity.flickrproject.models.FlickrImage;
import com.bumptech.glide.Glide;

public class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView; // ImageView to display the image
    private TextView title; // TextView to display the title

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView); //ImageView Reference to Find the ImageView in the item layout
        title = itemView.findViewById(R.id.Title); // TextView Reference to Find the TextView in the item layout
    }

    public void bind(FlickrImage image) {
        // Bind the data of the FlickrImage object to the views in the ViewHolder
        Glide.with(itemView)
                .load(image.getImageUrl())
                .into(imageView); // Load the image into the ImageView
        title.setText(image.getTitle()); // Set the title text in the TextView
    }
}

