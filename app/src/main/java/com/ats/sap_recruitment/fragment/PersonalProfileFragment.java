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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.EduStatusCode;
import com.ats.sap_recruitment.bean.LoginBean;
import com.ats.sap_recruitment.bean.PerProfile;
import com.ats.sap_recruitment.bean.PersonProfile;
import com.ats.sap_recruitment.retroInt.APIClient;
import com.ats.sap_recruitment.retroInt.APIInterface;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

public class PersonalProfileFragment extends Fragment {

    private static final String TAG = "PersonalProfileFragment";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Gson gson;
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
    String userType = "NA", userId = "NA";
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


        String json2 = pref.getString("loginBean", "");
        LoginBean loginBean = gson.fromJson(json2, LoginBean.class);
        if (loginBean != null) {
            userId = loginBean.getUserId();
            userType = loginBean.getUserType();
        }


        setBindData();


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

    private void setBindData() {


        String json = pref.getString("perProfile", "");
        PerProfile perProfile = gson.fromJson(json, PerProfile.class);
        if (perProfile != null) {
            edFirstName.setText(perProfile.getProfFname());
            edMiddleName.setText(perProfile.getProfMname());
            edLastName.setText(perProfile.getProfLname());
            edDOB.setText(perProfile.getProfDob());
            edLocation.setText(perProfile.getProfCurrLocation());
            String exp = perProfile.getProfWExpMonth() + " " + perProfile.getProfWExpYear();
            Log.e(TAG, "setBindData: Experience " + exp);

            if (perProfile.getProfWStatus().toString().equalsIgnoreCase("FRS")) {
                rbFresher.setChecked(true);
            } else {
                rbExp.setChecked(true);
                llExp.setVisibility(View.VISIBLE);
                edMonth.setText(perProfile.getProfWExpMonth());
                edYear.setText(perProfile.getProfWExpYear());
            }
            String empWorkLike = perProfile.getProfWStatus();
            Log.e(TAG, "setBindData: WorkLike " + empWorkLike);

            if (empWorkLike.equalsIgnoreCase("EMP")) {
                rbEmployee.setChecked(true);
                llEmployee.setVisibility(View.VISIBLE);
                edCurrentSal.setText(perProfile.getProfCurrSalary());
                edCompName.setText(perProfile.getProfCompanyName());
                edCompEmail.setText(perProfile.getProfCompanyEmail());

                if(perProfile.getProfProfileFlag().toString().equalsIgnoreCase("0")){
                    cbExperience.setChecked(true);
                }else
                    cbExperience.setChecked(false);

            } else {
                rbFreelancer.setChecked(true);
                llFreelancer.setVisibility(View.VISIBLE);
                if (perProfile.getProfFullPartTime().equals("0")) {
                    rbFullTime.setChecked(true);
                    llPartTime.setVisibility(View.GONE);
                } else {
                    rbPartTime.setChecked(true);
                    llPartTime.setVisibility(View.VISIBLE);
                    if(perProfile.getProfProfileFlag().toString().equalsIgnoreCase("0")){
                        cbFreelancer.setChecked(true);
                    }else
                        cbFreelancer.setChecked(false);

                }
            }
            edMobile1.setText(perProfile.getProfMobile());
            edMobile2.setText(perProfile.getProfAlternetMobile());
            edEmail.setText(perProfile.getProfEmail());
        } else {
            Log.e(TAG, "onCreateView: No educational details found yet");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:

                String workstatus = "FRS";
                String month = "NA";
                String year = "NA";
                String worklike = "NA";
                String currSal = "0";
                String companyName = "NA";
                String companyEmail = "NA";
                String fullPartTime = "-1";
                String profileFlag = "0";


                boolean validData = true;
                if (edFirstName.getText().toString().equals("")) {
                    Log.e(TAG, "onOptionsItemSelected: First Name empty");
                    validData = false;
                    edFirstName.setError("please enter first name");
                    edFirstName.requestFocus();
                }
                if (edMiddleName.getText().toString().equals("")) {
                    validData = false;
                    edMiddleName.setError("please enter middel name");
                    edMiddleName.requestFocus();
                }
                if (edLastName.getText().toString().equals("")) {
                    validData = false;
                    edLastName.setError("please enter last name");
                    edLastName.requestFocus();
                }
                if (edDOB.getText().toString().equals("")) {
                    validData = false;
                    edDOB.setError("please enter birthdate");
                    edDOB.requestFocus();
                }
                if (edLocation.getText().toString().equals("")) {
                    validData = false;
                    edLocation.setError("please enter your location");
                    edLocation.requestFocus();
                }
                // radio button click experience of fresher
                if (rbFreelancer.isChecked()) {
                    worklike = "FRS";

                } else if (rbExp.isChecked()) {
                    worklike = "EMP";
                    if (edMonth.getText().toString().equals("")) {
                        validData = false;
                        edMonth.setError("please enter experince of month");
                        edMonth.requestFocus();
                    } else {
                        month = edMonth.getText().toString();
                    }
                    if (edYear.getText().toString().equals("")) {
                        validData = false;
                        edYear.setError("please enter experince of year");
                        edYear.requestFocus();
                    } else {
                        year = edYear.getText().toString();
                    }

                } else {
                    validData = false;
                    Toast.makeText(getContext(), "Please select either Fresher or Experience", Toast.LENGTH_SHORT).show();
                }
                // radio button check work like employee or freelancer
                if (rbEmployee.isChecked()) {
                    worklike = "EMP";
                    if (edCurrentSal.getText().toString().equals("")) {
                        validData = false;
                        edCurrentSal.setError("please enter current salary");
                        edCurrentSal.requestFocus();
                    } else {
                        currSal = edCurrentSal.getText().toString();
                    }
                    if (edCompName.getText().toString().equals("")) {
                        validData = false;
                        edCompName.setError("Please enter company name");
                        edCompName.requestFocus();
                    } else {
                        companyName = edCompName.getText().toString();
                    }
                    if (edCompEmail.getText().toString().equals("")) {
                        validData = false;
                        edCompEmail.setError("please enter company email");
                        edCompEmail.requestFocus();
                    } else {
                        companyEmail = edCompEmail.getText().toString();
                    }
                } else if (rbFreelancer.isChecked()) {
                    worklike = "FLR";
                    if (rbFullTime.isChecked()) {
                        fullPartTime = "0";
                    } else if (rbPartTime.isChecked()) {
                        fullPartTime = "1";
                    } else {
                        validData = false;
                        Toast.makeText(getContext(), "Please select either FullTime or PartTime", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    validData = false;
                    Toast.makeText(getContext(), "Please select either Employee or Freelancer", Toast.LENGTH_SHORT).show();
                }
//
                if (edMobile1.getText().toString().equals("")) {
                    validData = false;
                    edMobile1.setError("please enter mobile no.");
                    edMobile1.requestFocus();
                }
                if (edMobile2.getText().toString().equals("")) {
                    validData = false;
                    edMobile2.setError("pleae enter mobile no.");
                    edMobile2.requestFocus();
                }
                if (edEmail.getText().toString().equals("")) {
                    validData = false;
                    edEmail.setError("please enter email Id");
                    edEmail.requestFocus();
                }

                if (cbFreelancer.isChecked() || cbExperience.isChecked()) {
                    profileFlag = "1";
                }
                if (validData) {
                    final AlertDialog dialog = new SpotsDialog(getActivity());
                    dialog.show();

                    Call<EduStatusCode> eduStatusCodeCall = apiInterface.savePersonalDetails("sav_perso", userType, userId, edFirstName.getText().toString(), edMiddleName.getText().toString(), edLastName.getText().toString(), edDOB.getText().toString(), edLocation.getText().toString(), workstatus, year, month, worklike, fullPartTime, currSal, companyName, companyEmail, profileFlag, edMobile1.getText().toString(), edMobile2.getText().toString(), edEmail.getText().toString());
                    eduStatusCodeCall.enqueue(new Callback<EduStatusCode>() {
                        @Override
                        public void onResponse(Call<EduStatusCode> call, Response<EduStatusCode> response) {
                            dialog.dismiss();
                            if (response.body() != null) {
                                EduStatusCode eduStatusCode = response.body();
                                if (eduStatusCode.getStatus().equalsIgnoreCase("successs")) {
                                    Toast.makeText(getContext(), "Personal Inforamation save successfully", Toast.LENGTH_SHORT).show();
                                    getProfileDetails();
                                } else
                                    Toast.makeText(getContext(), "can't save record", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getContext(), "Not Valid Response from server", Toast.LENGTH_SHORT).show();
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

    public void getProfileDetails() {
        final AlertDialog dialog = new SpotsDialog(getActivity());
        dialog.show();

        Call<PersonProfile> personProfileCall = apiInterface.getPersonalDetail("get_personal", userType, userId);
        personProfileCall.enqueue(new Callback<PersonProfile>() {
            @Override
            public void onResponse(Call<PersonProfile> call, Response<PersonProfile> response) {
                dialog.dismiss();
                if (response.body() != null) {

                    PersonProfile personProfile = response.body();
                    Log.e(TAG, "onResponse: getProfileDetails" + personProfile.getPerProfile());
                    if (personProfile.getPerProfile().size() > 0) {
                        PerProfile perProfile = personProfile.getPerProfile().get(0);
                        Log.e(TAG, "onResponse: perProfile Data " + perProfile);
                        if (perProfile != null) {

                            pref = getActivity().getApplicationContext().getSharedPreferences(Constants.myPref, MODE_PRIVATE);
                            editor = pref.edit();
                            gson = new Gson();
                            String json = gson.toJson(perProfile);
                            editor.putString("perProfile", json);
                            editor.apply();
                        }
                    }
                } else {
                    Log.e(TAG, "onResponse: getProfileDetails " + response.body());
                    Toast.makeText(getActivity().getApplicationContext(), "No personal Detail Updated Since", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PersonProfile> call, Throwable t) {
                dialog.dismiss();
                Log.e(TAG, "onFailure:getProfileDetails" + t.getMessage());

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
