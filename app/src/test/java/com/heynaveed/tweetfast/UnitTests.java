package com.heynaveed.tweetfast;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.heynaveed.tweetfast.keys.JSONKeys;
import com.heynaveed.tweetfast.tasks.RequestProfileInfo;
import com.heynaveed.tweetfast.tasks.RequestBearerToken;

import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class UnitTests {

    private static final String testUsername = "heynaveed";

    @Test
    public void requestsCorrectBearerToken() throws IOException{
        final String expectedToken = "AAAAAAAAAAAAAAAAAAAAAHOWyQAAAAAAHwkJ3Ol8ircLJ718IRKF04GlNVs%3D93RhcbRWsIvGRcGuQVGJ7Aex0CDIJ9TeygwrLoVbfNrZuBCzG8";
        final MockLogin mockLogin = new MockLogin();
        assertEquals(expectedToken, mockLogin.getTokenString());
    }

    @Test
    public void checkProfileInfoObjectIsNotNull() throws IOException {
        final MockLogin mockLogin = new MockLogin();
        assertEquals(false, mockLogin.getProfileInfo() == null);
    }

    @Test
    public void checkJSONObjectIsNotEmpty() throws IOException {
        final MockLogin mockLogin = new MockLogin();
        assertEquals(false, mockLogin.getProfileInfo().isEmpty());
    }

    @Test
    public void checkJSONObjectReturnsField() throws IOException{
        final MockLogin mockLogin = new MockLogin();
        assertEquals(testUsername.toLowerCase(), mockLogin.getProfileInfo().get(JSONKeys.username.toString()).toString().toLowerCase());
    }

    private class MockLogin {

        private final RequestBearerToken requestBearerToken;
        private final RequestProfileInfo requestProfileInfo;

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        private MockLogin() throws IOException {
            requestBearerToken = new RequestBearerToken();
            requestProfileInfo = new RequestProfileInfo();
            requestBearerToken.sendRequest();
            requestProfileInfo.sendRequest(testUsername, getTokenString());
        }

        private String getTokenString(){
            return requestBearerToken.getTokenString();
        }

        private JSONObject getProfileInfo(){
            return requestProfileInfo.getProfileInfo();
        }
    }
}