package ca.khaledactivity.flickrproject.models;

import com.google.gson.annotations.SerializedName;

public class FlickrResponse {

    @SerializedName("photos")
    private Photos photos;

    // getter and setter
    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }
}
