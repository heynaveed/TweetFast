package com.heynaveed.tweetfast.tasks;


import android.os.AsyncTask;

import com.heynaveed.tweetfast.utils.ConnectionHandler;
import com.heynaveed.tweetfast.utils.Session;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public final class RequestProfileInfo extends AsyncTask<String, Void, JSONObject>{

    private JSONObject profileInfo;

    @Override
    protected JSONObject doInBackground(String... params) {
        try {
            sendRequest(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profileInfo;
    }

    public void sendRequest(String... params) throws IOException{
        ConnectionHandler con = new ConnectionHandler("https://api.twitter.com/1.1/users/show.json?screen_name=" + params[0] + "&user_id=" + Session.userID);
        profileInfo = (JSONObject)JSONValue.parse(con.getResponse());
    }

    public JSONObject getProfileInfo(){
        return profileInfo;
    }

}
