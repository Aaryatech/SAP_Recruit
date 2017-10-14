package com.ats.sap_recruitment.fragment;


import android.content.SharedPreferences;
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
import com.ats.sap_recruitment.bean.PerProfile;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;
import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

public class PersonalProfileFragment extends Fragment {

    private static final String TAG = "PersonalProfileFragment";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Gson gson;
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

        rbFresher = view.findViewById(R.id.rbFresher);
        rbExp = view.findViewById(R.id.rbExperience);
        rbEmployee = view.findViewById(R.id.rbEmployee);
        rbFreelancer = view.findViewById(R.id.rbFreelancer);
        rbFullTime = view.findViewById(R.id.rbFullTime);
        rbPartTime = view.findViewById(R.id.rbPartTime);

        rgWorkStatus = view.findViewById(R.id.rgWorkStatus);

        llExp = view.findViewById(R.id.llExperience);
        llFreelancer = view.findViewById(R.id.llFreelancer);
        llPartTime = view.findViewById(R.id.llPartTime);
        llEmployee = view.findViewById(R.id.llEmployee);

        edFirstName = view.findViewById(R.id.edFirstName);
        edLastName = view.findViewById(R.id.edLastName);
        edMiddleName = view.findViewById(R.id.edMiddleName);
        edDOB = view.findViewById(R.id.edDOB);
        edLocation = view.findViewById(R.id.edPersonLocation);
        edMonth = view.findViewById(R.id.edPersonalMonth);
        edYear = view.findViewById(R.id.edPersonalYear);
        edCurrentSal = view.findViewById(R.id.edCurrentSalary);
        edCompName = view.findViewById(R.id.edCompanyName);
        edCompEmail = view.findViewById(R.id.edCompanyEmail);
        edMobile1 = view.findViewById(R.id.edMobileNO1);
        edMobile2 = view.findViewById(R.id.edMobileNo2);
        edEmail = view.findViewById(R.id.edPersonEmail);

        cbExperience = view.findViewById(R.id.cbHideExperience);
        cbFreelancer = view.findViewById(R.id.cbHideFreelancer);

        tvLabelWorkStatus = view.findViewById(R.id.tvLabelWorkStatus);
        tvLabelWilling = view.findViewById(R.id.tvLabelWillingToWork);

        textFirstName = view.findViewById(R.id.textFirstName);
        textMiddleName = view.findViewById(R.id.textMiddleName);
        textLastName = view.findViewById(R.id.textLastName);
        textDOB = view.findViewById(R.id.textDOB);
        textLocation = view.findViewById(R.id.textLocation);
        textCurrentSal = view.findViewById(R.id.textCurrentSal);
        textCompName = view.findViewById(R.id.textCompName);
        textCompEmail = view.findViewById(R.id.textCompEmail);
        textMobile1 = view.findViewById(R.id.textMobile1);
        textMobile2 = view.findViewById(R.id.textMobile2);
        textEmail = view.findViewById(R.id.textPersonEmail);

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

        pref = getActivity().getApplicationContext().getSharedPreferences(Constants.myPref, MODE_PRIVATE);
        gson = new Gson();
        String json = pref.getString("perProfile", "");
        PerProfile perProfile = gson.fromJson(json, PerProfile.class);

        if (perProfile != null) {
            Log.e(TAG, "onCreateView: Personal Profile " + perProfile);
            setBindData(perProfile);
        }


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

    private void setBindData(PerProfile perProfile) {
        edFirstName.setText(perProfile.getProfFname());
        edMiddleName.setText(perProfile.getProfMname());
        edLastName.setText(perProfile.getProfLname());
        edDOB.setText(perProfile.getProfDob());
        edLocation.setText(perProfile.getProfCurrLocation());
        String exp = perProfile.getProfWExpMonth() + " " + perProfile.getProfWExpYear();
        Log.e(TAG, "setBindData: Experience " + exp);

        if (exp.equalsIgnoreCase(" ")) {
            rbFresher.setChecked(true);
        } else {
            rbExp.setChecked(true);
            llExp.setVisibility(View.VISIBLE);
            edMonth.setText(perProfile.getProfWExpMonth());
            edYear.setText(perProfile.getProfWExpYear());
        }
        String empWorkLike = perProfile.getProfWStatus();
        Log.e(TAG, "setBindData: WorkLike " + empWorkLike);

        if (empWorkLike.equalsIgnoreCase("EXP")) {
            rbEmployee.setChecked(true);
            llEmployee.setVisibility(View.VISIBLE);
            edCurrentSal.setText(perProfile.getProfCurrSalary());
            edCompName.setText(perProfile.getProfCompanyName());
            edCompEmail.setText(perProfile.getProfCompanyEmail());
            cbExperience.setChecked(true);
        } else {
            rbFreelancer.setChecked(true);
            llFreelancer.setVisibility(View.VISIBLE);
            if (perProfile.getProfFullPartTime().equals("0")) {
                rbFullTime.setChecked(true);
            } else {
                rbPartTime.setChecked(true);
            }
        }
        edMobile1.setText(perProfile.getProfMobile());
        edMobile2.setText(perProfile.getProfAlternetMobile());
        edEmail.setText(perProfile.getProfEmail());

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
