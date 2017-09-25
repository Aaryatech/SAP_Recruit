package com.ats.sap_recruitment.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.utils.PreparationCounter;

import java.util.Timer;

import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

public class PreparationFragment extends Fragment {

    private TextView tvCounter, tvHead;
    int count = 10;
    Timer T;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preparation, container, false);
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        tvTitle.setText("Preparation");
        tvTitle.setTypeface(myTypeface);

        tvCounter = (TextView) view.findViewById(R.id.tvCounter);
        tvHead = view.findViewById(R.id.tvHeadPreparation);

        tvCounter.setTypeface(myTypeface);
        tvHead.setTypeface(myTypeface);


        PreparationCounter preparationCounter = new PreparationCounter(getContext());
        preparationCounter.createAndStart(11);
        preparationCounter.setTextView(tvCounter);

        Log.e("Preparation Counter", " : " + preparationCounter.getInstance());

       /* tvCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.createAndStart(600);
                counter.setTextView(tvCounter);

                Fragment fragment = new AssesmentStartFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });*/


        /*T = new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvCounter.setText("" + count);
                        count--;
                        if (count == -1) {
                            T.cancel();
                            Fragment fragment = new TermsAndConditionsFragment();
                            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.content_frame, fragment);
                            ft.commit();
                        }

                    }
                });

            }
        }, 1000, 1000);*/


        return view;
    }

}
