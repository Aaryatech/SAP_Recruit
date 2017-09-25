package com.ats.sap_recruitment.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ats.sap_recruitment.R;

import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

public class TestFeedbackFragment extends Fragment {

    private TextInputLayout textFeedback;
    private EditText edFeedback;
    private Button btnFeedback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_feedback, container, false);
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        tvTitle.setText("Test Feedback");
        tvTitle.setTypeface(myTypeface);

        btnFeedback = view.findViewById(R.id.btnFeedback);
        edFeedback = view.findViewById(R.id.edTestFeedback);
        textFeedback = view.findViewById(R.id.textTestFeedback);

        btnFeedback.setTypeface(myTypeface);
        edFeedback.setTypeface(myTypeface);
        textFeedback.setTypeface(myTypeface);


        return view;
    }

}
