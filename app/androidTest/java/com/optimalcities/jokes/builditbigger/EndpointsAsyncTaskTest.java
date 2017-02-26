package com.optimalcities.jokes.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by obelix on 16/02/2017.
 */
@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest implements ResultCallbackListener{


    private static final String EMPTY_STRING = "";

    @Test
    public void testEndPoints(){

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this, null);
        endpointsAsyncTask.execute(EMPTY_STRING);
    }
    @Override
    public void resultCallback(String response) {
        assertNotEquals(response, EMPTY_STRING);
    }
}