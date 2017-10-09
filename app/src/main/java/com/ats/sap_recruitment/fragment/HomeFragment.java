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
import com.ats.sap_recruitment.bean.EducationalProfile;
import com.ats.sap_recruitment.bean.LoginBean;
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
    private TextView tvPersonName, tvPersonDegree, tvPersonExp, tvProfileStatus, tvProfileLastUpdate, tvTestLastDate, tvTestScore, tvLabelStatus, tvLabelCompleted, tvLabelScore, tvLabelLastProfile, tvLabelLastTest, tvLabelRating, tvNotifyHead, tvNotifyText;
    private Button btnProfileUpdate, btnTest;
    private ImageView ivProfileImage;
    private LinearLayout llTestHistory;
    LoginBean loginBean;
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

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
            Log.e(TAG, "onCreateView: Login Details: " + loginBean);
            Log.e(TAG, "onCreateView: User Id: " + loginBean.getUserId());
            Log.e(TAG, "onCreateView: User Type: " + loginBean.getUserType());
        } else {
            Toast.makeText(getActivity(), "No Login Details found", Toast.LENGTH_SHORT).show();
        }

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

                if (response.body() != null) {
                    dialog.dismiss();
                    PersonProfile personProfile = response.body();
                    Log.e(TAG, "onResponse: getProfileDetails" + personProfile.getPerProfile());

                } else {
                    dialog.dismiss();
                    Log.e(TAG, "onResponse: getProfileDetails " + response.body());
                    Toast.makeText(getActivity().getApplicationContext(), "No personal Detail Updated Since", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PersonProfile> call, Throwable t) {
                Log.e(TAG, "onFailure:getProfileDetails" + t.getMessage());

            }
        });
    }


    public void getEducationalDetails() {
        Call<EducationalProfile> educationalProfileCall = apiInterface.getEducationalDetails("get_education", loginBean.getUserType(), loginBean.getUserId());
        educationalProfileCall.enqueue(new Callback<EducationalProfile>() {
            @Override
            public void onResponse(Call<EducationalProfile> call, Response<EducationalProfile> response) {
                Log.e(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<EducationalProfile> call, Throwable t) {

            }
        });
    }


}
