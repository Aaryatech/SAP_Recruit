package com.ats.sap_recruitment.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.CompanyPerProfile;
import com.ats.sap_recruitment.bean.CompanyProfile;
import com.ats.sap_recruitment.retroInt.APIClient;
import com.ats.sap_recruitment.retroInt.APIInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeEmployerFragment extends Fragment {


    private static String TAG = "HomeEmployerFragment";
    @BindView(R.id.llEmprViewPost)
    LinearLayout llEmprViewPost;
    @BindView(R.id.llEmprMatchProfile)
    LinearLayout llEmprMatchProfile;
    @BindView(R.id.fab_post_job)
    FloatingActionButton postJobFab;
    String userType = "2", userId = "2";
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

    public HomeEmployerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_employer, container, false);
        getActivity().setTitle("Profile");
        ButterKnife.bind(this, view);

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


    }

    public void getCompanyProfile() {

        Call<CompanyProfile> companyProfileCall = apiInterface.getCompanyProfile("get_cpmo", userType, userId);
        companyProfileCall.enqueue(new Callback<CompanyProfile>() {
            @Override
            public void onResponse(Call<CompanyProfile> call, Response<CompanyProfile> response) {
                if (response.body() != null) {
                    CompanyProfile companyProfile = response.body();
                    if (companyProfile != null) {
                        CompanyPerProfile companyPerProfile = companyProfile.getPerProfile().get(0);
                        Log.e(TAG, "onResponse: " + companyPerProfile);
                    }

                } else {

                }

            }

            @Override
            public void onFailure(Call<CompanyProfile> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });


    }


}
