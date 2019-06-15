package com.example.news.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {
    public static final String SOURCE_NAME = "source_name";
    public static final String URL = "url";
    public static final CharSequence FETCHING_NEWS = "Fetching News for you";


    public static boolean getConnectivityStatusString(Context context){
        boolean status = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
              //  status = "Wifi enabled";
                status = true;
                return status;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
              //  status = "Mobile data enabled";
               status = true;
                return status;
            }
        } else {
          //  status = "No internet is available";
            status = false;
            return status;
        }
        return status;
    }
}
