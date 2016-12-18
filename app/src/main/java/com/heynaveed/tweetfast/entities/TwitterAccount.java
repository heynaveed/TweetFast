package com.heynaveed.tweetfast.entities;

import com.heynaveed.tweetfast.keys.JSONKeys;
import org.json.simple.JSONObject;


public class TwitterAccount {

//    private final String username;
    private final JSONObject profileInfo;

    public TwitterAccount(JSONObject profileInfo){
//        this.username = profileInfo.get(JSONKeys.username.toString()).toString();
        this.profileInfo = profileInfo;
    }

//    public String getUsername(){
//        return username;
//    }

    public JSONObject getProfileInfo(){
        return profileInfo;
    }
}
