package com.heynaveed.tweetfast;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.heynaveed.tweetfast.utils.BearerToken;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Test
    public void bearerToken_isCorrect() throws IOException {
        String expected = "AAAAAAAAAAAAAAAAAAAAAHOWyQAAAAAAHwkJ3Ol8ircLJ718IRKF04GlNVs%3D93RhcbRWsIvGRcGuQVGJ7Aex0CDIJ9TeygwrLoVbfNrZuBCzG8";
        assertEquals(expected, new BearerToken().getBearerToken());
    }
}