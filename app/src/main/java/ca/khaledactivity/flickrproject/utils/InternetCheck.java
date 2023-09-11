package ca.khaledactivity.flickrproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetCheck {


    //Check if the device is currently connected to the internet.
    public static boolean isOnline(Context context) {
        // Get the system service for managing network connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = null;

        if (connectivityManager != null) {
            // Get the active network information
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        // Check if networkInfo is not null and the device is connected to a network
        return networkInfo != null && networkInfo.isConnected();
    }

}

