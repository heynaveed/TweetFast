package com.heynaveed.tweetfast.utils;


import android.os.Build;
import android.support.annotation.RequiresApi;

import twitter4j.Twitter;
import twitter4j.User;


@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class Session {

    public static Twitter resources;
    public static long userID;
    public static String username;
    public static User user;

}
