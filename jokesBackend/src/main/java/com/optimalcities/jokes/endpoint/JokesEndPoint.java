/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.optimalcities.jokes.endpoint;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.jokes.Jokes;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokeApi",
        version = "v2",
        namespace = @ApiNamespace(
                ownerDomain = "endpoint.jokes.optimalcities.com",
                ownerName = "endpoint.jokes.optimalcities.com",
                packagePath = ""
        )
)
public class JokesEndPoint {

    /**
     * A simple endpoint method that returns a joke
     */
    @ApiMethod(name = "retrieveJokes")
    public MyBean getJokes() {
        MyBean response = new MyBean();

        Jokes jokes = Jokes.getInstance();
        response.setData(jokes.joke);

        return response;
    }

}
