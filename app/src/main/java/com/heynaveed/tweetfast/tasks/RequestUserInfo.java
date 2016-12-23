package com.heynaveed.tweetfast.tasks;


import android.os.AsyncTask;

import com.heynaveed.tweetfast.utils.Session;

import twitter4j.TwitterException;
import twitter4j.User;


public final class RequestUserInfo extends AsyncTask<String, Void, User>{

    private User user;

    @Override
    protected User doInBackground(String... params) {
        try {
            sendRequest(params);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void sendRequest(String... params) throws TwitterException {
        user = Session.resources.showUser(params[0]);
    }

    public User getUser(){
        return user;
    }

}
