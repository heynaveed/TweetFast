package com.heynaveed.tweetfast;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.heynaveed.tweetfast.entities.User;
import com.heynaveed.tweetfast.utils.BearerToken;

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

    private static BearerToken token = null;

    static {
        try {
            token = new BearerToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Test
    public void bearerToken_isCorrect() throws IOException {
        final String expected = "AAAAAAAAAAAAAAAAAAAAAHOWyQAAAAAAHwkJ3Ol8ircLJ718IRKF04GlNVs%3D93RhcbRWsIvGRcGuQVGJ7Aex0CDIJ9TeygwrLoVbfNrZuBCzG8";
        assertEquals(expected, token.getBearerToken());
    }

    @Test
    public void user_loadsWithUsername() throws IOException {
        final String username = "heynaveed";
        User user = new User(username, token);
        assertEquals(username, user.getUsername().toLowerCase());
    }
}