package com.heynaveed.tweetfast.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.heynaveed.tweetfast.R;
import com.heynaveed.tweetfast.tasks.RequestUserInfo;
import com.heynaveed.tweetfast.utils.Colors;
import com.heynaveed.tweetfast.utils.Session;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class LoginActivity extends AppCompatActivity {

    // To be obfuscated.
    public static final String CONSUMER_KEY = "o2psFcURWRf6y6ymutuweNLMw";
    public static final String CONSUMER_SECRET = "PasA4q4FM7xLDkmsqpJg5qcnxReQd9nozsewoMZuGIaFAPsFLi";
    public static final String ACCESS_TOKEN = "o2psFcURWRf6y6ymutuweNLMw";
    public static final String ACCESS_TOKEN_SECRET = "PasA4q4FM7xLDkmsqpJg5qcnxReQd9nozsewoMZuGIaFAPsFLi";

    private TwitterLoginButton loginButton;
    private boolean isLoggedIn = false;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(Colors.TWITTER_BLUE);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(CONSUMER_KEY, CONSUMER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_login);

        ConfigurationBuilder configBuilder = new ConfigurationBuilder();
        configBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

        Session.resources = new TwitterFactory(configBuilder.build()).getInstance();

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void success(Result<TwitterSession> result) {
                Session.username = result.data.getUserName();
                Session.userID = result.data.getId();
                String msg = "Logged in as: " + Session.username;
                isLoggedIn = true;
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);

        if(isLoggedIn){
            RequestUserInfo requestUserInfo = new RequestUserInfo();
            requestUserInfo.execute(Session.username);
            Session.user = requestUserInfo.getUser();
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }
    }
}
