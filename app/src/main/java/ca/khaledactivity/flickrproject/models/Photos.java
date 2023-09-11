package ca.khaledactivity.flickrproject.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Photos {

    @SerializedName("photo")
    private List<FlickrImage> images;

    // getter and setter
    public List<FlickrImage> getImages() {
        return images;
    }

    public void setImages(List<FlickrImage> images) {
        this.images = images;
    }
}
