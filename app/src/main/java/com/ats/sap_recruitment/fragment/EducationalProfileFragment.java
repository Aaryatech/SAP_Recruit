package com.ats.sap_recruitment.fragment;


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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.EduPerProfile;
import com.ats.sap_recruitment.bean.EduStatusCode;
import com.ats.sap_recruitment.bean.EducationalProfile;
import com.ats.sap_recruitment.bean.LoginBean;
import com.ats.sap_recruitment.retroInt.APIClient;
import com.ats.sap_recruitment.retroInt.APIInterface;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
    String userType = "NA", userId = "NA";


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

        String json2 = pref.getString("loginBean", "");
        LoginBean loginBean = gson.fromJson(json2, LoginBean.class);
        if (loginBean != null) {
            userId = loginBean.getUserId();
            userType = loginBean.getUserType();
        }
        setBinData();

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

    private void setBinData() {


        String json = pref.getString("eduPerProfile", "");
        EduPerProfile eduPerProfile = gson.fromJson(json, EduPerProfile.class);

        if (eduPerProfile != null) {
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
            Log.e(TAG, "onCreateView: Educational Profile  " + eduPerProfile);
        } else
            Log.e(TAG, "onCreateView: No educational details found yet");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.e(TAG, "onOptionsItemSelected: " + edCourse.getText());
        Log.e(TAG, "onOptionsItemSelected: " + edMiscSkills.getText());
        Log.e(TAG, "onOptionsItemSelected: " + edGrade.getText());
        Log.e(TAG, "onOptionsItemSelected: " + edSpecialisation.getText());
        Log.e(TAG, "onOptionsItemSelected: " + edInstitute.getText());

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

                if (edCourse.getText().toString().equals("")) {

                    validData = false;
                    Log.e(TAG, "onOptionsItemSelected: ValidData  " + validData);
                    edCourse.setError("field required");
                }
                if (edSpecialisation.getText().toString().equals("")) {
                    validData = false;
                    Log.e(TAG, "onOptionsItemSelected: ValidData  " + validData);
                    edSpecialisation.setError("field required");
                }
                if (edInstitute.getText().toString().equals("")) {
                    validData = false;
                    Log.e(TAG, "onOptionsItemSelected: ValidData  " + validData);
                    edInstitute.setError("field required");
                }
                if (edPassYear.getText().toString().equals("")) {
                    validData = false;
                    edPassYear.requestFocus();
                    Log.e(TAG, "onOptionsItemSelected: ValidData  " + validData);
                    edPassYear.setError("field required");
                }
                if (edGrade.getText().toString().equals("")) {
                    validData = false;
                    edGrade.requestFocus();
                    Log.e(TAG, "onOptionsItemSelected: ValidData  " + validData);
                    edGrade.setError("field required");
                }
                if (edMiscSkills.getText().toString().equals("")) {
                    validData = false;
                    edMiscSkills.requestFocus();
                    Log.e(TAG, "onOptionsItemSelected: ValidData  " + validData);
                    edMiscSkills.setError("field required");
                }
                Log.e(TAG, "onOptionsItemSelected: Validation " + validData);
                if (validData) {
                    final AlertDialog dialog = new SpotsDialog(getActivity());
                    dialog.show();

                    Call<EduStatusCode> eduStatusCodeCall = apiInterface.saveEducationalDetails("sav_educa", userType, userId, edCourse.getText().toString(), edSpecialisation.getText().toString(), edInstitute.getText().toString(), edPassYear.getText().toString(), edGrade.getText().toString(), edMiscSkills.getText().toString());
                    eduStatusCodeCall.enqueue(new Callback<EduStatusCode>() {
                        @Override
                        public void onResponse(Call<EduStatusCode> call, Response<EduStatusCode> response) {
                            dialog.dismiss();
                            if (response.body() != null) {
                                EduStatusCode eduStatusCode = response.body();
                                if (eduStatusCode.getStatus().equalsIgnoreCase("successs")) {
                                    Toast.makeText(getContext(), "Educational Information Save Successfully", Toast.LENGTH_SHORT).show();
                                    getEducationalDetails();
                                } else {
                                    Toast.makeText(getContext(), "can't save record", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(getContext(), "No valid Response from server", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<EduStatusCode> call, Throwable t) {
                            dialog.dismiss();
                            Log.e(TAG, "onFailure: " + t.getMessage());

                        }
                    });

                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void getEducationalDetails() {
        final AlertDialog dialog = new SpotsDialog(getActivity());
        dialog.show();

        Call<EducationalProfile> educationalProfileCall = apiInterface.getEducationalDetails("get_education", userType, userId);
        educationalProfileCall.enqueue(new Callback<EducationalProfile>() {
            @Override
            public void onResponse(Call<EducationalProfile> call, Response<EducationalProfile> response) {
                dialog.dismiss();
                Log.e(TAG, "onResponse: " + response.body());
                if (response.body() != null) {
                    EducationalProfile educationalProfile = response.body();
                    Log.e(TAG, "onResponse: Educational Profile " + educationalProfile);
                    if (educationalProfile.getPerProfile().size() > 0) {
                        EduPerProfile eduPerProfile = educationalProfile.getPerProfile().get(0);
                        Log.e(TAG, "onResponse: " + eduPerProfile);
                        if (eduPerProfile != null) {
//                            tvPersonDegree.setText(eduPerProfile.getProfEduCourseDetail());

                            pref = getActivity().getApplicationContext().getSharedPreferences(Constants.myPref, MODE_PRIVATE);
                            editor = pref.edit();
                            gson = new Gson();
                            String json = gson.toJson(eduPerProfile);
                            editor.putString("eduPerProfile", json);
                            editor.apply();

                        }
                    }

                } else {
                    Log.e(TAG, "onResponse: " + response.body());
                    Toast.makeText(getActivity(), "No Educational details Updated Since", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EducationalProfile> call, Throwable t) {
                dialog.dismiss();
                Log.e(TAG, "onFailure: getEducationalDetails" + t.getMessage());

            }
        });
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
