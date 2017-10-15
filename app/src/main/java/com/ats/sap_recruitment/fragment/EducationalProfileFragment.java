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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.EduPerProfile;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;
import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

public class EducationalProfileFragment extends Fragment {
    private static final String TAG = "EducationalProfile";

    @BindView(R.id.tvLabelHighEdu)
    TextView tvLabelEdu;
    @BindView(R.id.textCourseDetails)
    TextInputLayout textCourse;
    @BindView(R.id.textSpecialisation)
    TextInputLayout textSpecialisation;
    @BindView(R.id.textInstitute)
    TextInputLayout textInstitute;
    @BindView(R.id.textEduGrade)
    TextInputLayout textGrade;
    @BindView(R.id.textPassYear)
    TextInputLayout textPassYear;
    @BindView(R.id.textMiscSkills)
    TextInputLayout textSkills;
    @BindView(R.id.rbEduBachelors)
    RadioButton rbBachelor;
    @BindView(R.id.rbEduMasters)
    RadioButton rbMaster;
    @BindView(R.id.rbEduDoctorate)
    RadioButton rbDoctorate;
    @BindView(R.id.edCourseDetails)
    AutoCompleteTextView edCourse;
    @BindView(R.id.edSpecialisation)
    AutoCompleteTextView edSpecialisation;
    @BindView(R.id.edInstitute)
    AutoCompleteTextView edInstitute;
    @BindView(R.id.edEduGrade)
    EditText edGrade;
    @BindView(R.id.edPassYear)
    EditText edPassYear;
    @BindView(R.id.edMiscSkills)
    EditText edMiscSkills;
    ArrayList<String> uniArrayList = new ArrayList<>(Arrays.asList("Pune", "Mumbai", "Amravati", "Kolhapur", "Nagpur"));
    ArrayList<String> courseArrayList = new ArrayList<>(Arrays.asList("B.E.", "B.Tehc", "BCS", "BCA", "M.E.", "M.Tech", "MCA", "MCS"));
    ArrayList<String> specialArrayList = new ArrayList<>(Arrays.asList("Computer Technology", "Information Technolgoy", "Computer Science", "Networking"));

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Gson gson;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_educational_profile, container, false);
        ButterKnife.bind(this, view);

        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        Typeface myTypefaceBold = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");

        tvTitle.setText("SAP Profile - Educational");
        tvTitle.setTypeface(myTypeface);


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

        ArrayAdapter<String> uniArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, uniArrayList);
        edInstitute.setAdapter(uniArrayAdapter);
        edInstitute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edInstitute.showDropDown();
            }
        });


        edInstitute.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = (String) adapterView.getItemAtPosition(i);
                Log.e(TAG, "onItemClick : String for University " + selected);
                edInstitute.setText(selected);
            }
        });

        ArrayAdapter<String> courseArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, courseArrayList);
        edCourse.setAdapter(courseArrayAdapter);

        edCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edCourse.showDropDown();
            }
        });

        edCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = (String) adapterView.getItemAtPosition(i);
                Log.e(TAG, "onItemClick : String for Course " + selected);
                edCourse.setText(selected);
            }
        });

        ArrayAdapter<String> specialArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, specialArrayList);
        edSpecialisation.setAdapter(specialArrayAdapter);

        edSpecialisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edSpecialisation.showDropDown();
            }
        });

        edSpecialisation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = (String) adapterView.getItemAtPosition(i);
                Log.e(TAG, "onItemClick : String for Specialisation " + selected);
                edSpecialisation.setText(selected);

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:
                boolean validData = true;

                if (rbBachelor.isChecked()) {
                    //validData = true;
                } else if (rbDoctorate.isChecked()) {
                    //validData = true;
                } else if (rbMaster.isChecked()) {
                    //validData = true;
                } else {
                    validData = false;
                    Toast.makeText(getContext(), "select your Degree ", Toast.LENGTH_SHORT).show();
                }

                if (edCourse.equals("")) {
                    validData = false;
                    edCourse.setError("field required");
                }
                if (edSpecialisation.equals("")) {
                    validData = false;
                    edCourse.setError("field required");
                }
                if (edInstitute.equals("")) {
                    validData = false;
                    edInstitute.setError("field required");
                }
                if (edPassYear.equals("")) {
                    validData = false;
                    edPassYear.requestFocus();
                    edPassYear.setError("field required");
                }
                if (edGrade.equals("")) {
                    validData = false;
                    edGrade.requestFocus();
                    edGrade.setError("field required");
                }
                if (edMiscSkills.equals("")) {
                    validData = false;
                    edMiscSkills.requestFocus();
                    edMiscSkills.setError("field required");
                }

                Log.e(TAG, "onOptionsItemSelected: Validation " + validData);
                if (validData) {

                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
