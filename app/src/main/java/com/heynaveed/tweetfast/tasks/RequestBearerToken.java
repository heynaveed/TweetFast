package com.heynaveed.tweetfast.tasks;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.heynaveed.tweetfast.activities.LoginActivity;
import com.heynaveed.tweetfast.utils.ConnectionHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class RequestBearerToken extends AsyncTask<Void, Void, String> {

    private static final String TOKEN_RESOURCE_URL = "https://api.twitter.com/oauth2/token";

    private String tokenString;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(Void... params) {
        try {
            tokenString = sendRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tokenString;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String encodeKeys(String consumerKey, String consumerSecret){

        try{
            String encodedConsumerKey = URLEncoder.encode(consumerKey, "UTF-8");
            String encodedConsumerSecret = URLEncoder.encode(consumerSecret, "UTF-8");
            String fullKey = encodedConsumerKey + ":" + encodedConsumerSecret;

            byte[] encodedBytes = Base64.encodeBase64(fullKey.getBytes());

            return new String(encodedBytes);
        }
        catch(UnsupportedEncodingException e){
            throw new AssertionError("Invalid encoding!", e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String sendRequest() throws IOException{

        HttpURLConnection bearerCon = null;
        String encodedCredentials = encodeKeys(LoginActivity.KEY, LoginActivity.SECRET);

        try{
            URL url = new URL(TOKEN_RESOURCE_URL);
            bearerCon = (HttpURLConnection) url.openConnection();
            bearerCon.setDoOutput(true);
            bearerCon.setDoInput(true);
            bearerCon.setInstanceFollowRedirects(false);
            bearerCon.setRequestMethod("POST");
            bearerCon.setRequestProperty("Host", "api.twitter.com");
            bearerCon.setRequestProperty("RequestProfileInfo-Agent", "TweetFast");
            bearerCon.setRequestProperty("Authorization", "Basic " + encodedCredentials);
            bearerCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            bearerCon.setRequestProperty("Content-Length", "29");
            bearerCon.setUseCaches(false);

            writeRequest(bearerCon, "grant_type=client_credentials");

            ConnectionHandler con = new ConnectionHandler(bearerCon);

            JSONObject obj = (JSONObject)JSONValue.parse(con.getResponse());

            if(obj != null){
                String tokenType = (String)obj.get("token_type");
                String token = (String)obj.get("access_token");

                return ((tokenType.equals("bearer")) && (token != null)) ? token : "";
            }
            return "";
        }

        finally{
            if(bearerCon != null){
                bearerCon.disconnect();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean writeRequest(HttpURLConnection con, String bodyParameters) throws IOException{

        try (BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()))) {
            wr.write(bodyParameters);
            wr.flush();
        }

        return true;
    }

    public String getTokenString(){
        return tokenString;
    }
}
