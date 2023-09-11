package ca.khaledactivity.flickrproject.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlickrApiClient {
    private static final String BASE_URL = "https://api.flickr.com/";
    private static FlickrApi api;



    public static FlickrApi getApi() {
        // Check if the API instance is null
        if (api == null) {
            // If it is null, create a new Retrofit instance
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            // Create an instance of the FlickrApi using the Retrofit instance
            api = retrofit.create(FlickrApi.class);
        }
        // Return the FlickrApi instance
        return api;
    }

}
