package com.optimalcities.jokes.builditbigger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class JokesFragment extends Fragment {

    InterstitialAd mInterstitialAd;
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
        View root = inflater.inflate(R.layout.fragment_jokes, container, false);

        ButterKnife.bind(this, root);
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstial_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                requestNewInterstitial();
                if(jokeGetterCallbcak != null){
                    jokeGetterCallbcak.jokeGetter();
                }
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);

                Toast.makeText(getActivity(),"Failed to Load Ad", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });
       /* mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                //requestNewInterstitial();


            }
        });
*/
        requestNewInterstitial();

        return root;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.show();

    }

    /**
     * Registers the tell joke button click listener.
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.tellJoke)
    public void showJoke(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            //beginPlayingGame();

         /*   if(jokeGetterCallbcak != null){
                jokeGetterCallbcak.jokeGetter();
            }
*/
        }

    }


    public void setFragmentCallback(JokeGetterCallbcak jokeGetterCallbcak) {
        this.jokeGetterCallbcak = jokeGetterCallbcak;
    }



}
