package com.ats.sap_recruitment.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.ats.sap_recruitment.activity.HomeActivity;
import com.ats.sap_recruitment.bean.CompanyPerProfile;
import com.ats.sap_recruitment.bean.CompanyProfile;
import com.ats.sap_recruitment.bean.JobProfileDetails;
import com.ats.sap_recruitment.bean.LoginBean;
import com.ats.sap_recruitment.retroInt.APIClient;
import com.ats.sap_recruitment.retroInt.APIInterface;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeEmployerFragment extends Fragment {


    @BindView(R.id.llEmprViewPost)
    LinearLayout llEmprViewPost;
    @BindView(R.id.llEmprMatchProfile)
    LinearLayout llEmprMatchProfile;
    @BindView(R.id.fab_post_job)
    FloatingActionButton postJobFab;
    @BindView(R.id.ivEmprProfilePic)
    ImageView imageView;
    @BindView(R.id.tvEmprPersonName)
    TextView tvEmprPersonName;
    @BindView(R.id.tvEmprLocation)
    TextView tvEmprLocation;
    @BindView(R.id.tvEmprDesignation)
    TextView tvEmprDesignation;
    @BindView(R.id.tvEmprCompanyName)
    TextView tvEmprCompanyName;
    @BindView(R.id.tvEmprProfileStatus)
    TextView tvEmprProfileStatus;
    @BindView(R.id.tvEmprProfileCompleted)
    TextView tvEmprProfileCompleted;
    @BindView(R.id.tvEmprProfileStatusPercent)
    TextView tvEmprProfileStatusPercent;
    @BindView(R.id.tvEmplrMobileNo)
    TextView tvEmplrMobileNo;
    @BindView(R.id.tvEmplrMnoVerified)
    TextView tvEmplrMnoVerified;
    @BindView(R.id.tvEmplrEmailId)
    TextView tvEmplrEmailId;
    @BindView(R.id.tvEmplrEmailVerified)
    TextView tvEmplrEmailVerified;

    @BindView(R.id.btnEmprHomeProfileUpdate)
    Button btnEmprHomeProfileUpdate;
    @BindView(R.id.tvEmprLastUpdate)
    TextView tvEmprLastUpdate;


    private String userType = "0", userId = "0";

    private static String TAG = "HomeEmployerFragment";
    private APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Gson gson;

    public HomeEmployerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_employer, container, false);
        getActivity().setTitle("Profile");
        ButterKnife.bind(this, view);
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");

        Typeface myTypefaceBold = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        HomeActivity.tvTitle.setText("SAP Employer");


        tvEmprPersonName.setTypeface(myTypeface);
        tvEmprLocation.setTypeface(myTypeface);
        tvEmprDesignation.setTypeface(myTypeface);
        tvEmprCompanyName.setTypeface(myTypeface);
        tvEmprProfileStatus.setTypeface(myTypefaceBold);
        tvEmprProfileCompleted.setTypeface(myTypefaceBold);
        tvEmprProfileStatusPercent.setTypeface(myTypeface);
        tvEmplrMobileNo.setTypeface(myTypeface);
        tvEmplrMnoVerified.setTypeface(myTypeface);
        tvEmplrEmailId.setTypeface(myTypeface);
        tvEmplrEmailVerified.setTypeface(myTypeface);
        tvEmprLastUpdate.setTypeface(myTypeface);


        pref = getActivity().getSharedPreferences(Constants.myPref, Context.MODE_PRIVATE);
        editor = pref.edit();
        gson = new Gson();

        String json = pref.getString("loginBean", "");
        LoginBean loginBean = gson.fromJson(json, LoginBean.class);
        if (loginBean != null) {
            userId = loginBean.getUserId();
            userType = loginBean.getUserType();
        }

        getCompanyProfile();
        getAllJobDetails();
        return view;
    }

    @OnClick(R.id.llEmprViewPost)
    public void viewPostedJob() {
        Log.e(TAG, "viewPostedJob: method");
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, new ViewAllPostFragment());
        ft.addToBackStack("View All Post");
        ft.commit();
    }


    @OnClick(R.id.llEmprMatchProfile)
    public void matchProfile() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, new RecruiteeProfileFragment());
        ft.addToBackStack("backtoProfile");
        ft.commit();
    }

    @OnClick(R.id.fab_post_job)
    public void postJob() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, new PostJobFragment());
        ft.addToBackStack("backtoProfile");
        ft.commit();
    }

    @OnClick(R.id.btnEmprHomeProfileUpdate)
    public void editCompanyProfile() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, new CompanyProfileFragment());
        ft.addToBackStack("backtoProfile");
        ft.commit();
    }

    public void getCompanyProfile() {
        final AlertDialog dialog = new SpotsDialog(getActivity());
        dialog.show();

        Call<CompanyProfile> companyProfileCall = apiInterface.getCompanyProfile("get_cpmo", userType, userId);
        companyProfileCall.enqueue(new Callback<CompanyProfile>() {
            @Override
            public void onResponse(Call<CompanyProfile> call, Response<CompanyProfile> response) {
                dialog.dismiss();
                if (response.body() != null) {
                    CompanyProfile companyProfile = response.body();

                    String json = gson.toJson(companyProfile);
                    editor.putString("companyProfile", json);
                    editor.apply();

                    if (companyProfile != null) {
                        CompanyPerProfile cpProfile = companyProfile.getPerProfile().get(0);
                        Log.e(TAG, "onResponse: " + cpProfile);
                        tvEmprPersonName.setText(cpProfile.getProfFname() + " " + cpProfile.getProfMname() + " " + cpProfile.getProfLname());
                        tvEmprCompanyName.setText(cpProfile.getRecCompName());
                        tvEmprDesignation.setText(cpProfile.getRecCompYarExp());// Editing required here no designation found
                        tvEmprLocation.setText(cpProfile.getRecCompCity());
                        tvEmprProfileStatusPercent.setText("" + cpProfile.getProfileCompleted());
                        tvEmplrMobileNo.setText(cpProfile.getProfMobile());
                        tvEmplrEmailId.setText(cpProfile.getProfEmail());
                        tvEmprLastUpdate.setText(cpProfile.getProfLstUpdate());
                    }
                } else {
                    Log.e(TAG, "onResponse: No valid response from Server");
                    Toast.makeText(getContext(), "no Response from server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CompanyProfile> call, Throwable t) {
                dialog.dismiss();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });


    }


    public void getAllJobDetails() {
        final AlertDialog dialog = new SpotsDialog(getActivity());
        dialog.show();

        Log.e(TAG, "getAllJobDetails: UserId : " + userId + " User Type : " + userType);
        Call<JobProfileDetails> jobProfileDetailsCall = apiInterface.getJobProfileDetails("get_jobs", userType, userId);
        jobProfileDetailsCall.enqueue(new Callback<JobProfileDetails>() {
            @Override
            public void onResponse(Call<JobProfileDetails> call, Response<JobProfileDetails> response) {
                dialog.dismiss();
                if (response.body() != null) {
                    JobProfileDetails jobProfileDetails = response.body();
                    Log.e(TAG, "onResponse: getAllJobDetails :" + jobProfileDetails);
                } else {
                    Log.e(TAG, "onResponse: getAllJobDetails : No Valid response From Server ");
                    Toast.makeText(getContext(), "No Valid response from Server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JobProfileDetails> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }

}
