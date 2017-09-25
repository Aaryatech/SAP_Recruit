package com.ats.sap_recruitment.fragment;


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

import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateProfileFragment extends Fragment {

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

        tvName = (TextView) view.findViewById(R.id.tvProfileName);
        tvDegree = (TextView) view.findViewById(R.id.tvProfileDegree);
        tvExp = (TextView) view.findViewById(R.id.tvProfileExp);
        tvPersonal = (TextView) view.findViewById(R.id.tvLabelPersonalPro);
        tvEdu = (TextView) view.findViewById(R.id.tvLabelEduPro);
        tvSAP = (TextView) view.findViewById(R.id.tvLabelSAP);
        tvSAPText = (TextView) view.findViewById(R.id.tvLabelSAPText);
        tvBasis = (TextView) view.findViewById(R.id.tvLabelBasis);
        tvAbap = (TextView) view.findViewById(R.id.tvLabelAbap);
        tvFunctional = (TextView) view.findViewById(R.id.tvLabelFunctional);

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


        btnBasis = (Button) view.findViewById(R.id.btnEditBasis);
        btnAbap = (Button) view.findViewById(R.id.btnEditABAP);
        btnFunctional = (Button) view.findViewById(R.id.btnEditFunctional);
        btnPersonalProfile = (Button) view.findViewById(R.id.btnPersonalProfile);
        btnEduProfile = (Button) view.findViewById(R.id.btnEducationalProfile);


        llBasis = (LinearLayout) view.findViewById(R.id.llUpdateBasis);
        llAbap = (LinearLayout) view.findViewById(R.id.llUpdateAbap);
        llFunctional = (LinearLayout) view.findViewById(R.id.llUpdateFunctional);



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
