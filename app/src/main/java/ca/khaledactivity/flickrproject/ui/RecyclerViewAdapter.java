package ca.khaledactivity.flickrproject.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ca.khaledactivity.flickrproject.R;
import ca.khaledactivity.flickrproject.models.FlickrImage;
import ca.khaledactivity.flickrproject.ui.ImageDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<FlickrImage> images = new ArrayList<>(); // List to hold the data of FlickrImages
    private Context context;
    private RecyclerViewAdapter adapter;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout (item_image.xml) and create a ViewHolder for it
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind the data at the specified position in the images list to the ViewHolder
        FlickrImage image = images.get(position);
        holder.bind(image);
        holder.itemView.setOnClickListener(v -> {
            // Handle regular click action here
            Intent intent = new Intent(context, ImageDetailActivity.class);
            intent.putExtra("image", image);
            context.startActivity(intent);
        });
        holder.itemView.setOnLongClickListener(v -> {
            // Handle long-click action here, e.g., show a bigger version of the image
            showBiggerImage(image);
            return true;
        });
    }

    public void addImageAtStart(FlickrImage image) {
        images.add(0, image);
        notifyItemInserted(0);
        // Optionally, we can also call notifyItemRangeChanged(0, getItemCount()) instead of notifyDataSetChanged()
        // This will update the entire list, including any existing items, in case we have more than one item
        // notifyDataSetChanged();
        notifyItemRangeChanged(0, getItemCount());
    }


    private void showBiggerImage(FlickrImage image) {
        Intent intent = new Intent(context, BiggerImageActivity.class);
        intent.putExtra("imageUrl", image.getImageUrl());
        context.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return images.size();
    }

    public void setImages(List<FlickrImage> images) {
        this.images = images;
        notifyDataSetChanged();
    }
}
