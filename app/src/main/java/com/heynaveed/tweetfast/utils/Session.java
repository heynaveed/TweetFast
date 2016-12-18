package com.heynaveed.tweetfast.utils;


import android.os.Build;
import android.support.annotation.RequiresApi;

import com.heynaveed.tweetfast.entities.TwitterAccount;


@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class Session {

    public static long userID;
    public static String username;
    public static String tokenString;
    public static TwitterAccount user;

}
