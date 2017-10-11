package com.ats.sap_recruitment.fragment;


import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.EduPerProfile;
import com.ats.sap_recruitment.bean.PerProfile;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;
import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateProfileFragment extends Fragment {

    PerProfile perProfile;
    EduPerProfile eduPerProfile;
    private Button btnBasis, btnAbap, btnFunctional;
    private LinearLayout llBasis, llAbap, llFunctional;
    private Button btnPersonalProfile, btnEduProfile;
    private TextView tvPersonal, tvEdu, tvSAP, tvSAPText, tvBasis, tvAbap, tvFunctional, tvName, tvDegree, tvExp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_profile, container, false);

        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        Typeface myTypefaceBold = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");

        tvTitle.setText("Update Profile");
        tvTitle.setTypeface(myTypefaceBold);

        tvName = view.findViewById(R.id.tvProfileName);
        tvDegree = view.findViewById(R.id.tvProfileDegree);
        tvExp = view.findViewById(R.id.tvProfileExp);
        tvPersonal = view.findViewById(R.id.tvLabelPersonalPro);
        tvEdu = view.findViewById(R.id.tvLabelEduPro);
        tvSAP = view.findViewById(R.id.tvLabelSAP);
        tvSAPText = view.findViewById(R.id.tvLabelSAPText);
        tvBasis = view.findViewById(R.id.tvLabelBasis);
        tvAbap = view.findViewById(R.id.tvLabelAbap);
        tvFunctional = view.findViewById(R.id.tvLabelFunctional);

        tvName.setTypeface(myTypeface);
        tvDegree.setTypeface(myTypeface);
        tvExp.setTypeface(myTypeface);
        tvPersonal.setTypeface(myTypefaceBold);
        tvEdu.setTypeface(myTypefaceBold);
        tvSAP.setTypeface(myTypefaceBold);
        tvSAPText.setTypeface(myTypeface);
        tvBasis.setTypeface(myTypefaceBold);
        tvAbap.setTypeface(myTypefaceBold);
        tvFunctional.setTypeface(myTypefaceBold);


        btnBasis = view.findViewById(R.id.btnEditBasis);
        btnAbap = view.findViewById(R.id.btnEditABAP);
        btnFunctional = view.findViewById(R.id.btnEditFunctional);
        btnPersonalProfile = view.findViewById(R.id.btnPersonalProfile);
        btnEduProfile = view.findViewById(R.id.btnEducationalProfile);


        llBasis = view.findViewById(R.id.llUpdateBasis);
        llAbap = view.findViewById(R.id.llUpdateAbap);
        llFunctional = view.findViewById(R.id.llUpdateFunctional);


        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences(Constants.myPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = pref.getString("perProfile", "");
        perProfile = gson.fromJson(json, PerProfile.class);
        if (perProfile != null) {
            tvName.setText(perProfile.getProfFname() + " " + perProfile.getProfMname() + " " + perProfile.getProfLname());
            String exp = perProfile.getProfWExpYear() + " " + perProfile.getProfWExpMonth();

            if (exp.equalsIgnoreCase(" "))
                tvExp.setText("Fresher");
            else
                tvExp.setText(exp);
        }


        String json2 = pref.getString("eduPerProfile", "");
        eduPerProfile = gson.fromJson(json2, EduPerProfile.class);
        if (eduPerProfile != null) {
            tvDegree.setText(eduPerProfile.getProfEduCourseDetail());
        }

       /* cdPersonal = (CircleDisplay) view.findViewById(R.id.circleDisplayPersonal);
        cdPersonal.setAnimDuration(3000);
        cdPersonal.setValueWidthPercent(33f);
        cdPersonal.setTextSize(12f);
        cdPersonal.setColor(Color.argb(255, 0, 122, 173));
        cdPersonal.setDrawText(true);
        cdPersonal.setDrawInnerCircle(true);
        cdPersonal.setFormatDigits(1);
        cdPersonal.setTouchEnabled(false);
        cdPersonal.setUnit("%");
        cdPersonal.setStepSize(0.5f);
        cdPersonal.showValue(84, 100, true);


        cdEducational = (CircleDisplay) view.findViewById(R.id.circleDisplayEducation);
        cdEducational.setAnimDuration(3000);
        cdEducational.setValueWidthPercent(33f);
        cdEducational.setTextSize(12f);
        cdEducational.setColor(Color.argb(255, 0, 122, 173));
        cdEducational.setDrawText(true);
        cdEducational.setDrawInnerCircle(true);
        cdEducational.setFormatDigits(1);
        cdEducational.setTouchEnabled(false);
        cdEducational.setUnit("%");
        cdEducational.setStepSize(0.5f);
        cdEducational.showValue(84, 100, true);*/

        btnPersonalProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new PersonalProfileFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "UpdateProfile");
                ft.commit();
            }
        });


        btnEduProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new EducationalProfileFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "UpdateProfile");
                ft.commit();
            }
        });

        btnBasis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new BasisFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "UpdateProfile");
                ft.commit();
            }
        });

        btnAbap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ABAPFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "UpdateProfile");
                ft.commit();
            }
        });


        btnFunctional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FunctionalFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "UpdateProfile");
                ft.commit();
            }
        });

        llBasis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new BasisFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "UpdateProfile");
                ft.commit();
            }
        });

        llAbap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ABAPFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "UpdateProfile");
                ft.commit();
            }
        });


        llFunctional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FunctionalFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "UpdateProfile");
                ft.commit();
            }
        });

        return view;
    }

}
