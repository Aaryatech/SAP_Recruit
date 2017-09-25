package com.ats.sap_recruitment.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ats.sap_recruitment.R;

import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;


public class HomeFragment extends Fragment {

    private TextView tvPersonName, tvPersonDegree, tvPersonExp, tvProfileStatus, tvProfileLastUpdate, tvTestLastDate, tvTestScore, tvLabelStatus, tvLabelCompleted, tvLabelScore, tvLabelLastProfile, tvLabelLastTest, tvLabelRating, tvNotifyHead, tvNotifyText;
    private Button btnProfileUpdate, btnTest;
    private ImageView ivProfileImage;
    private LinearLayout llTestHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        Typeface myTypefaceBold = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");

        tvTitle.setText("SAP Recruitment");
        tvTitle.setTypeface(myTypefaceBold);


        ivProfileImage = (ImageView) view.findViewById(R.id.ivUserProfilePic);
        tvPersonName = (TextView) view.findViewById(R.id.tvHomeUserName);
        tvPersonDegree = (TextView) view.findViewById(R.id.tvHomeUserDegree);
        tvPersonExp = (TextView) view.findViewById(R.id.tvHomeUserExperience);
        tvProfileStatus = (TextView) view.findViewById(R.id.tvHomeProfileStatusPercent);
        tvProfileLastUpdate = (TextView) view.findViewById(R.id.tvHomeProfileLastUpdate);
        tvTestLastDate = (TextView) view.findViewById(R.id.tvHomeLastTestDate);
        tvTestScore = (TextView) view.findViewById(R.id.tvHomeTestScore);
        tvLabelStatus = (TextView) view.findViewById(R.id.tvHomeProfileStatus);
        tvLabelCompleted = (TextView) view.findViewById(R.id.tvHomeProfileCompleted);
        tvLabelScore = (TextView) view.findViewById(R.id.tvLabelScore);
        tvLabelLastProfile = (TextView) view.findViewById(R.id.tvHomeProfileLastUpdate);
        tvLabelLastTest = (TextView) view.findViewById(R.id.tvHomeLastTestDate);
        tvLabelRating = (TextView) view.findViewById(R.id.tvLabelRating);
        tvNotifyHead = (TextView) view.findViewById(R.id.tvNotifyHead);
        tvNotifyText = (TextView) view.findViewById(R.id.tvNotifyText);

        tvPersonName.setTypeface(myTypeface);
        tvPersonDegree.setTypeface(myTypeface);
        tvPersonExp.setTypeface(myTypeface);
        tvProfileStatus.setTypeface(myTypefaceBold);
        tvProfileLastUpdate.setTypeface(myTypeface);
        tvTestLastDate.setTypeface(myTypeface);
        tvTestScore.setTypeface(myTypefaceBold);
        tvLabelScore.setTypeface(myTypefaceBold);
        tvLabelStatus.setTypeface(myTypefaceBold);
        tvLabelCompleted.setTypeface(myTypefaceBold);
        tvLabelLastProfile.setTypeface(myTypefaceBold);
        tvLabelLastTest.setTypeface(myTypefaceBold);
        tvLabelRating.setTypeface(myTypefaceBold);
        tvNotifyText.setTypeface(myTypeface);
        tvNotifyHead.setTypeface(myTypefaceBold);

        btnProfileUpdate = (Button) view.findViewById(R.id.btnHomeProfileUpdate);
        btnTest = (Button) view.findViewById(R.id.btnHomeTest);

        btnTest.setTypeface(myTypefaceBold);
        btnProfileUpdate.setTypeface(myTypefaceBold);

        llTestHistory = (LinearLayout) view.findViewById(R.id.llHomeTestScore);

        btnProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new UpdateProfileFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "HomeFragment");
                ft.commit();

            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new TermsAndConditionsFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "HomeFragment");
                ft.commit();
            }
        });

        llTestHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AssesmentHistoryFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "HomeFragment");
                ft.commit();
            }
        });

        return view;
    }

}
