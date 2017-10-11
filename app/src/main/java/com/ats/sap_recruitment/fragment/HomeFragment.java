package com.ats.sap_recruitment.fragment;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.EduPerProfile;
import com.ats.sap_recruitment.bean.EducationalProfile;
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


public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    LoginBean loginBean;
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
    String userId = "NA", userType = "NA";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Gson gson;
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

        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences(Constants.myPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = pref.getString("loginBean", "");
        loginBean = gson.fromJson(json, LoginBean.class);

        if (loginBean != null) {
            userId = loginBean.getUserId();
            userType = loginBean.getUserType();
            Log.e(TAG, "onCreateView: Login Details: " + loginBean);
            Log.e(TAG, "onCreateView: User Id: " + loginBean.getUserId());
            Log.e(TAG, "onCreateView: User Type: " + loginBean.getUserType());
        } else {
            Toast.makeText(getActivity(), "No Login Details found", Toast.LENGTH_SHORT).show();
        }

        ivProfileImage =  view.findViewById(R.id.ivUserProfilePic);
        tvPersonName =  view.findViewById(R.id.tvHomeUserName);
        tvPersonDegree =  view.findViewById(R.id.tvHomeUserDegree);
        tvPersonExp =  view.findViewById(R.id.tvHomeUserExperience);
        tvProfileStatus =  view.findViewById(R.id.tvHomeProfileStatusPercent);
        tvProfileLastUpdate =  view.findViewById(R.id.tvHomeProfileLastUpdate);
        tvTestLastDate = view.findViewById(R.id.tvHomeLastTestDate);
        tvTestScore =  view.findViewById(R.id.tvHomeTestScore);
        tvLabelStatus =  view.findViewById(R.id.tvHomeProfileStatus);
        tvLabelCompleted =  view.findViewById(R.id.tvHomeProfileCompleted);
        tvLabelScore =  view.findViewById(R.id.tvLabelScore);
        tvLabelLastProfile =  view.findViewById(R.id.tvHomeProfileLastUpdate);
        tvLabelLastTest =  view.findViewById(R.id.tvHomeLastTestDate);
        tvLabelRating =  view.findViewById(R.id.tvLabelRating);
        tvNotifyHead =  view.findViewById(R.id.tvNotifyHead);
        tvNotifyText =  view.findViewById(R.id.tvNotifyText);

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


        btnProfileUpdate = view.findViewById(R.id.btnHomeProfileUpdate);
        btnTest = view.findViewById(R.id.btnHomeTest);

        btnTest.setTypeface(myTypefaceBold);
        btnProfileUpdate.setTypeface(myTypefaceBold);

        llTestHistory = view.findViewById(R.id.llHomeTestScore);

        getProfileDetails();
        getEducationalDetails();

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

    public void getProfileDetails() {
        final AlertDialog dialog = new SpotsDialog(getActivity());
        dialog.show();

        Call<PersonProfile> personProfileCall = apiInterface.getPersonalDetail("get_personal", loginBean.getUserType(), loginBean.getUserId());
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
                            tvPersonName.setText(perProfile.getProfFname() + " " + perProfile.getProfMname() + " " + perProfile.getProfLname());
                            String exp = perProfile.getProfWExpYear() + " " + perProfile.getProfWExpMonth();
                            Log.e(TAG, "onResponse: Experience" + exp);
                            if (exp.equalsIgnoreCase(" "))
                                tvPersonExp.setText("Fresher");
                            else
                                tvPersonExp.setText(exp);


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


    public void getEducationalDetails() {
        final AlertDialog dialog = new SpotsDialog(getActivity());
        dialog.show();

        Call<EducationalProfile> educationalProfileCall = apiInterface.getEducationalDetails("get_education", loginBean.getUserType(), loginBean.getUserId());
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
                            tvPersonDegree.setText(eduPerProfile.getProfEduCourseDetail());

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


}
