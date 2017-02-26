package com.optimalcities.jokes.builditbigger;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.optimalcities.jokes.endpoint.jokeApi.JokeApi;

import java.io.IOException;


public class EndpointsAsyncTask extends AsyncTask<String,Void, String> {

    private final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();
    private static JokeApi jokeApi = null;
    private ResultCallbackListener resultCallbackListener;
    private ProgressBar mProgressBar;
    private final String EMPTY_STRING = "";

    /**
     *  Initializes resultCallbackListener and progress bar.
     *
     * @param resultCallbackListener
     * @param progressBar
     */
    public EndpointsAsyncTask(ResultCallbackListener resultCallbackListener,
                              ProgressBar progressBar) {

        if(resultCallbackListener == null)
            throw new NullPointerException("ResultCallbackListener is required.");

        this.resultCallbackListener = resultCallbackListener;
        this.mProgressBar = progressBar;
    }

    /**
     * Makes the progress indicator visible.
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(mProgressBar != null)
            mProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Fetches the joke from google cloud endpoint using client api.
     *
     * @param params
     * @return
     */
    @Override
    protected String doInBackground(String... params) {
        String rootUrl = "https://udacity-jokes-156916.appspot.com/_ah/api/";

        if (jokeApi == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(rootUrl)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            jokeApi = builder.build();
        }
        try {
            return jokeApi.retrieveJokes().execute().getData();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        return EMPTY_STRING;
    }

    /**
     * Makes the progress indicator invisible and delegates the
     * control to result callback handler.
     *
     * @param jokeText
     */
    @Override
    protected void onPostExecute(String jokeText) {
        if(mProgressBar != null)
            mProgressBar.setVisibility(View.GONE);

        resultCallbackListener.resultCallback(jokeText);
    }

}