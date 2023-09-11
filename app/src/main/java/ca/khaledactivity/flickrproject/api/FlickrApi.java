package ca.khaledactivity.flickrproject.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ca.khaledactivity.flickrproject.models.Photos;
import ca.khaledactivity.flickrproject.models.FlickrResponse;

public interface FlickrApi {
    @GET("services/rest/?method=flickr.photos.getRecent&format=json&nojsoncallback=1")
        //converting JSON to an array of objects using Retrofit
    Call<FlickrResponse> getRecentPhotos(@Query("api_key") String apiKey, @Query("extras") String extras);
}
