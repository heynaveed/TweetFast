package com.heynaveed.tweetfast;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;

import com.heynaveed.tweetfast.tasks.RequestProfileInfo;
import com.heynaveed.tweetfast.tasks.RequestBearerToken;

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

//    @Test
//    public void user_loadsWithUsername() throws IOException {
//        RequestProfileInfo requestProfileInfo = new RequestProfileInfo(testUsername, token.getTokenString());
//        assertEquals(testUsername, requestProfileInfo.getUsername().toLowerCase());
//    }
//
//    @Test
//    public void user_loadsWithProfileInfo() throws IOException {
//        RequestProfileInfo requestProfileInfo = new RequestProfileInfo(testUsername, token.getTokenString());
//        assertEquals(true, requestProfileInfo.getProfileInfo() != null);
//    }
}