package com.ats.sap_recruitment.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ats.sap_recruitment.R;

import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

public class PersonalProfileFragment extends Fragment {

    private RadioButton rbFresher, rbExp, rbFreelancer, rbEmployee, rbFullTime, rbPartTime;
    private LinearLayout llExp, llFreelancer, llPartTime, llEmployee;
    private RadioGroup rgWorkStatus;

    private EditText edFirstName, edLastName, edMiddleName, edDOB, edLocation, edMonth, edYear, edCurrentSal, edCompName, edCompEmail, edMobile1, edMobile2, edEmail;
    private CheckBox cbExperience, cbFreelancer;
    private TextView tvLabelWorkStatus, tvLabelWilling;
    private TextInputLayout textFirstName, textMiddleName, textLastName, textDOB, textLocation, textCurrentSal, textCompName, textCompEmail, textMobile1, textMobile2, textEmail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_profile, container, false);

        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        Typeface myTypefaceBold = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");

        tvTitle.setText("SAP Profile - Personal");
        tvTitle.setTypeface(myTypeface);


        rbFresher = (RadioButton) view.findViewById(R.id.rbFresher);
        rbExp = (RadioButton) view.findViewById(R.id.rbExperience);
        rbEmployee = (RadioButton) view.findViewById(R.id.rbEmployee);
        rbFreelancer = (RadioButton) view.findViewById(R.id.rbFreelancer);
        rbFullTime = (RadioButton) view.findViewById(R.id.rbFullTime);
        rbPartTime = (RadioButton) view.findViewById(R.id.rbPartTime);

        rgWorkStatus = (RadioGroup) view.findViewById(R.id.rgWorkStatus);

        llExp = (LinearLayout) view.findViewById(R.id.llExperience);
        llFreelancer = (LinearLayout) view.findViewById(R.id.llFreelancer);
        llPartTime = (LinearLayout) view.findViewById(R.id.llPartTime);
        llEmployee = (LinearLayout) view.findViewById(R.id.llEmployee);

        edFirstName = (EditText) view.findViewById(R.id.edFirstName);
        edLastName = (EditText) view.findViewById(R.id.edLastName);
        edMiddleName = (EditText) view.findViewById(R.id.edMiddleName);
        edDOB = (EditText) view.findViewById(R.id.edDOB);
        edLocation = (EditText) view.findViewById(R.id.edPersonLocation);
        edMonth = (EditText) view.findViewById(R.id.edPersonalMonth);
        edYear = (EditText) view.findViewById(R.id.edPersonalYear);
        edCurrentSal = (EditText) view.findViewById(R.id.edCurrentSalary);
        edCompName = (EditText) view.findViewById(R.id.edCompanyName);
        edCompEmail = (EditText) view.findViewById(R.id.edCompanyEmail);
        edMobile1 = (EditText) view.findViewById(R.id.edMobileNO1);
        edMobile2 = (EditText) view.findViewById(R.id.edMobileNo2);
        edEmail = (EditText) view.findViewById(R.id.edPersonEmail);

        cbExperience = (CheckBox) view.findViewById(R.id.cbHideExperience);
        cbFreelancer = (CheckBox) view.findViewById(R.id.cbHideFreelancer);

        tvLabelWorkStatus = (TextView) view.findViewById(R.id.tvLabelWorkStatus);
        tvLabelWilling = (TextView) view.findViewById(R.id.tvLabelWillingToWork);

        textFirstName = (TextInputLayout) view.findViewById(R.id.textFirstName);
        textMiddleName = (TextInputLayout) view.findViewById(R.id.textMiddleName);
        textLastName = (TextInputLayout) view.findViewById(R.id.textLastName);
        textDOB = (TextInputLayout) view.findViewById(R.id.textDOB);
        textLocation = (TextInputLayout) view.findViewById(R.id.textLocation);
        textCurrentSal = (TextInputLayout) view.findViewById(R.id.textCurrentSal);
        textCompName = (TextInputLayout) view.findViewById(R.id.textCompName);
        textCompEmail = (TextInputLayout) view.findViewById(R.id.textCompEmail);
        textMobile1 = (TextInputLayout) view.findViewById(R.id.textMobile1);
        textMobile2 = (TextInputLayout) view.findViewById(R.id.textMobile2);
        textEmail = (TextInputLayout) view.findViewById(R.id.textPersonEmail);

        edFirstName.setTypeface(myTypeface);
        edLastName.setTypeface(myTypeface);
        edMiddleName.setTypeface(myTypeface);
        edDOB.setTypeface(myTypeface);
        edLocation.setTypeface(myTypeface);
        edMonth.setTypeface(myTypeface);
        edYear.setTypeface(myTypeface);
        edCurrentSal.setTypeface(myTypeface);
        edCompName.setTypeface(myTypeface);
        edCompEmail.setTypeface(myTypeface);
        edMobile1.setTypeface(myTypeface);
        edMobile2.setTypeface(myTypeface);
        edEmail.setTypeface(myTypeface);

        textFirstName.setTypeface(myTypeface);
        textMiddleName.setTypeface(myTypeface);
        textLastName.setTypeface(myTypeface);
        textDOB.setTypeface(myTypeface);
        textLocation.setTypeface(myTypeface);
        textCurrentSal.setTypeface(myTypeface);
        textCompName.setTypeface(myTypeface);
        textCompEmail.setTypeface(myTypeface);
        textMobile1.setTypeface(myTypeface);
        textMobile2.setTypeface(myTypeface);
        textEmail.setTypeface(myTypeface);

        cbExperience.setTypeface(myTypeface);
        cbFreelancer.setTypeface(myTypeface);

        tvLabelWorkStatus.setTypeface(myTypeface);
        tvLabelWilling.setTypeface(myTypeface);

        rbFresher.setTypeface(myTypeface);
        rbExp.setTypeface(myTypeface);
        rbEmployee.setTypeface(myTypeface);
        rbFreelancer.setTypeface(myTypeface);
        rbFullTime.setTypeface(myTypeface);
        rbPartTime.setTypeface(myTypeface);

        llEmployee.setVisibility(View.GONE);
        llExp.setVisibility(View.GONE);
        llFreelancer.setVisibility(View.GONE);
        llPartTime.setVisibility(View.GONE);

//        if (rbExp.isChecked()) {
//            llExp.setVisibility(View.VISIBLE);
//            Log.e("Experienced ", " checked");
//        } else {
//            Log.e("Experienced ", " not checked");
//            llExp.setVisibility(View.GONE);
//        }

        rbExp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rbExp.isChecked()) {
                    llExp.setVisibility(View.VISIBLE);
                    Log.e("Experienced ", " checked");
                } else {
                    Log.e("Experienced ", " not checked");
                    llExp.setVisibility(View.GONE);
                }
            }
        });

        rbEmployee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rbEmployee.isChecked()) {
                    llEmployee.setVisibility(View.VISIBLE);
                } else {
                    llEmployee.setVisibility(View.GONE);
                }
            }
        });

        rbFreelancer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rbFreelancer.isChecked()) {
                    llFreelancer.setVisibility(View.VISIBLE);
                    rbPartTime.setChecked(false);
                    rbFullTime.setChecked(false);
                } else {
                    llFreelancer.setVisibility(View.GONE);
                    llPartTime.setVisibility(View.GONE);
                }
            }
        });

        rbPartTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rbPartTime.isChecked()) {
                    llPartTime.setVisibility(View.VISIBLE);
                } else {
                    llPartTime.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.findItem(R.id.action_save);
        item.setVisible(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
