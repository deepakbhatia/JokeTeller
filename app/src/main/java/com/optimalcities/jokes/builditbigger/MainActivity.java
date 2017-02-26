package com.optimalcities.jokes.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.optimalcities.jokedisplaylib.JokeDisplayActivity;


public class MainActivity extends AppCompatActivity implements JokeGetterCallbcak,
        ResultCallbackListener{


    private EndpointsAsyncTask mEndpointsAsyncTask;

    private static final String INTENT_JOKE_EXTRA = "JOKE_INTENT";

    private JokesFragment jokesFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jokesFragment = new JokesFragment();
        getSupportFragmentManager().beginTransaction().
                add(R.id.fragment,jokesFragment).commit();



   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void jokeGetter() {
        mEndpointsAsyncTask = new EndpointsAsyncTask(this, jokesFragment.jokeTaskProgress);
        mEndpointsAsyncTask.execute();
    }

    /**
     * Registers the fragment callback.
     */
    @Override
    protected void onStart() {
        super.onStart();

        if(jokesFragment != null){
            jokesFragment.setFragmentCallback(this);
        }
    }

    @Override
    public void resultCallback(String joke) {

        Intent intent = new Intent(this, JokeDisplayActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(INTENT_JOKE_EXTRA, joke);
        startActivity(intent);
    }
}
