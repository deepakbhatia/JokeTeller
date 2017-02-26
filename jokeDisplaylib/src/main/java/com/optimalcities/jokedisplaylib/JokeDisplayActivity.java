package com.optimalcities.jokedisplaylib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author obelix
 * @version 1.0
 *
 * Displays the joke received in intent.
 *
 */
public class JokeDisplayActivity extends AppCompatActivity {

    private static final String INTENT_JOKE_EXTRA = "JOKE_INTENT";
    @BindView(R2.id.jokeTextView) TextView textView;

    /**
     * Inflates the layout and initializes the view with the
     * data received in the intent.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_joke_display);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // bind the views
        ButterKnife.bind(this);

        //initialize the view
        init(getIntent());
    }

    /**
     * Initializes the view with intent data.
     *
     * @param intent
     */
    private void init(Intent intent){
        if(intent != null) {

            String joke = intent.getStringExtra(INTENT_JOKE_EXTRA);
            // check weather data is empty.
            if(joke != null && !joke.isEmpty())
                textView.setText(joke);
            else
                textView.setText(R.string.empty_joke_from_server);
        }
    }

    /**
     *  Intent handler callback.
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        init(intent);
    }

    /**
     * Handles up button action.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
