package com.heynaveed.tweetfast.entities;


import com.heynaveed.tweetfast.keys.JSONKeys;
import com.heynaveed.tweetfast.utils.BearerToken;
import com.heynaveed.tweetfast.utils.ConnectionHandler;

import java.io.IOException;
import java.net.ProtocolException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public final class User {

    private final JSONObject profileInfo;
    private boolean isValidUsername;
    private String username;

    public User(String username, BearerToken bToken) throws IOException{
        profileInfo = requestProfileInfo(new ConnectionHandler("https://api.twitter.com/1.1/users/show.json?screen_name=" + username + "&user_id=2868022389", bToken));
        this.username = isValidUsername ?profileInfo.get(JSONKeys.username.toString()).toString() :"";
    }

    private JSONObject requestProfileInfo(ConnectionHandler con) throws ProtocolException, IOException{
        isValidUsername = !con.getResponse().isEmpty();
        return (JSONObject)JSONValue.parse(con.getResponse());
    }

    public JSONObject getProfileInfo(){
        return profileInfo;
    }

    public String getUsername(){
        return username;
    }
}
