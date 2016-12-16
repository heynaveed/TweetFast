package com.heynaveed.tweetfast.utils;


import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.simple.JSONObject;


@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class Session {

    public static String username;
    public static String tokenString;
    public static JSONObject userProfile;

}
