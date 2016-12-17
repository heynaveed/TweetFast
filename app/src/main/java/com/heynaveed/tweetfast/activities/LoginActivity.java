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
import com.heynaveed.tweetfast.tasks.RequestProfileInfo;
import com.heynaveed.tweetfast.tasks.RequestBearerToken;
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

public class LoginActivity extends AppCompatActivity {

    // To be obfuscated.
    public static final String KEY = "o2psFcURWRf6y6ymutuweNLMw";
    public static final String SECRET = "PasA4q4FM7xLDkmsqpJg5qcnxReQd9nozsewoMZuGIaFAPsFLi";

    private TwitterLoginButton loginButton;
    private boolean isLoggedIn = false;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(Colors.TWITTER_BLUE);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(KEY, SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_login);

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void success(Result<TwitterSession> result) {
                Session.username = result.data.getUserName();
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
            RequestBearerToken requestToken = new RequestBearerToken();
            requestToken.execute();
            Session.tokenString = requestToken.getTokenString();
            RequestProfileInfo info = new RequestProfileInfo();
            info.execute(Session.username, Session.tokenString);
            Session.userProfile = info.getProfileInfo();
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }
    }
}
