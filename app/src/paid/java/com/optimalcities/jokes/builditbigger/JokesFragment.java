package com.optimalcities.jokes.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokesFragment extends Fragment {

    @BindView(R.id.tellJoke)
    Button tellerJoke;

    @BindView(R.id.progressBar)
    ProgressBar jokeTaskProgress;

    private JokeGetterCallbcak jokeGetterCallbcak;

    public JokesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_jokes, container, false);


        ButterKnife.bind(this,root);

        return root;
    }


    @OnClick(R.id.tellJoke)
    public void tellJoke(View view) {

        if(jokeGetterCallbcak != null){
            jokeGetterCallbcak.jokeGetter();
        }


    }




    public void setFragmentCallback(JokeGetterCallbcak jokeGetterCallbcak) {
        this.jokeGetterCallbcak = jokeGetterCallbcak;
    }

}
