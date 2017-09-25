package com.ats.sap_recruitment.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ats.sap_recruitment.R;

import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

public class TestResultFragment extends Fragment {

    private TextView tvResultHead, tvResultScore;
    // private RatingBar rtbResult;
    private Button btnFeedback, btnNoThanks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_result, container, false);
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        tvTitle.setText("Test Result");
        tvTitle.setTypeface(myTypeface);

        tvResultHead = (TextView) view.findViewById(R.id.tvResultHead);
        tvResultScore = (TextView) view.findViewById(R.id.tvResultScore);

        tvResultHead.setTypeface(myTypeface);
        tvResultScore.setTypeface(myTypeface);

        //rtbResult = (RatingBar) view.findViewById(R.id.rtbResult);

        btnFeedback = (Button) view.findViewById(R.id.btnResultFeedback);
        btnNoThanks = (Button) view.findViewById(R.id.btnResultNoThanks);

        btnFeedback.setTypeface(myTypeface);
        btnNoThanks.setTypeface(myTypeface);

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new TestFeedbackFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "TestResult");
                ft.commit();
            }
        });

        btnNoThanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new HomeFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "Home");
                ft.commit();
            }
        });


        return view;
    }

}
