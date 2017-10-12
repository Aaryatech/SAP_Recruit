package com.ats.sap_recruitment.fragment;


import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.EduPerProfile;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;
import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

public class EducationalProfileFragment extends Fragment {

    private static final String TAG = "EducationalProfile";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Gson gson;
    private EditText edCourse, edSpecialisation, edInstitute, edPassYear, edGrade, edMiscSkills;
    private TextInputLayout textCourse, textSpecialisation, textInstitute, textPassYear, textGrade, textSkills;
    private RadioButton rbBachelor, rbMaster, rbDoctorate;
    private TextView tvLabelEdu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_educational_profile, container, false);

        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        Typeface myTypefaceBold = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");

        tvTitle.setText("SAP Profile - Educational");
        tvTitle.setTypeface(myTypeface);


        edCourse = view.findViewById(R.id.edCourseDetails);
        edSpecialisation = view.findViewById(R.id.edSpecialisation);
        edInstitute = view.findViewById(R.id.edInstitute);
        edGrade = view.findViewById(R.id.edEduGrade);
        edPassYear = view.findViewById(R.id.edPassYear);
        edMiscSkills = view.findViewById(R.id.edMiscSkills);

        textCourse = view.findViewById(R.id.textCourseDetails);
        textSpecialisation = view.findViewById(R.id.textSpecialisation);
        textInstitute = view.findViewById(R.id.textInstitute);
        textGrade = view.findViewById(R.id.textEduGrade);
        textPassYear = view.findViewById(R.id.textPassYear);
        textSkills = view.findViewById(R.id.textMiscSkills);

        rbBachelor = view.findViewById(R.id.rbEduBachelors);
        rbMaster = view.findViewById(R.id.rbEduMasters);
        rbDoctorate = view.findViewById(R.id.rbEduDoctorate);

        tvLabelEdu = view.findViewById(R.id.tvLabelHighEdu);

        edCourse.setTypeface(myTypeface);
        edSpecialisation.setTypeface(myTypeface);
        edInstitute.setTypeface(myTypeface);
        edGrade.setTypeface(myTypeface);
        edPassYear.setTypeface(myTypeface);
        edMiscSkills.setTypeface(myTypeface);

        textCourse.setTypeface(myTypeface);
        textSpecialisation.setTypeface(myTypeface);
        textInstitute.setTypeface(myTypeface);
        textGrade.setTypeface(myTypeface);
        textPassYear.setTypeface(myTypeface);
        textSkills.setTypeface(myTypeface);

        rbBachelor.setTypeface(myTypeface);
        rbMaster.setTypeface(myTypeface);
        rbDoctorate.setTypeface(myTypeface);
        tvLabelEdu.setTypeface(myTypeface);


        pref = getActivity().getApplicationContext().getSharedPreferences(Constants.myPref, MODE_PRIVATE);
        gson = new Gson();
        String json = pref.getString("eduPerProfile", "");
        EduPerProfile eduPerProfile = gson.fromJson(json, EduPerProfile.class);

        if (eduPerProfile != null) {
            Log.e(TAG, "onCreateView: Educational Profile  " + eduPerProfile);
            setBinData(eduPerProfile);
        } else
            Log.e(TAG, "onCreateView: No educational details found yet");


        edCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCourseDialog();
            }
        });

        edSpecialisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSpecialisationDialog();
            }
        });

        edInstitute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInstituteDialog();
            }
        });

        return view;
    }

    private void setBinData(EduPerProfile eduPerProfile) {
        String eduHighest = eduPerProfile.getProfEduHighest();
        if (eduHighest.equalsIgnoreCase("0")) {
            rbBachelor.setChecked(true);
        }
        edCourse.setText(eduPerProfile.getProfEduCourseDetail());
        edSpecialisation.setText(eduPerProfile.getProfSpecilalzation());
        edInstitute.setText(eduPerProfile.getProfEduUniversity());
        edPassYear.setText(eduPerProfile.getProfEduPassingYear());
        edGrade.setText(eduPerProfile.getProfEduGradeRange());
        edMiscSkills.setText(eduPerProfile.getProfEduMiscSkillDetails());

    }


    public void showCourseDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        //dialog.setTitle("Select Course");
        dialog.setCancelable(true);

        View view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.custom_course_dialog, null);
        dialog.setView(view);
        dialog.show();
    }

    public void showSpecialisationDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        //dialog.setTitle("Select Course");
        dialog.setCancelable(true);

        View view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.custom_specialisation_dialog, null);
        dialog.setView(view);
        dialog.show();
    }

    public void showInstituteDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        //dialog.setTitle("Select Course");
        dialog.setCancelable(true);

        View view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.custom_institute_dialog, null);
        dialog.setView(view);
        dialog.show();
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
