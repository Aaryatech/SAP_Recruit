package com.ats.sap_recruitment.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.activity.HomeActivity;
import com.ats.sap_recruitment.adpter.ViewPostJobAdapter;
import com.ats.sap_recruitment.bean.JobPost;
import com.ats.sap_recruitment.bean.JobProfileDetails;
import com.ats.sap_recruitment.bean.JobsArry;
import com.ats.sap_recruitment.bean.LoginBean;
import com.ats.sap_recruitment.retroInt.APIClient;
import com.ats.sap_recruitment.retroInt.APIInterface;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllPostFragment extends Fragment {
    @BindView(R.id.rc_view_list_post)
    RecyclerView rc_view_list_post;

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView.Adapter adapter;
    private static ArrayList<JobPost> data = new ArrayList<>();
    private static String TAG = "ViewAllPostFragment";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private String userId = "0";
    private String userType = "0";
    private APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
    private ArrayList<JobsArry> jobsArryArrayList = new ArrayList<>();

    public ViewAllPostFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_view_all_post, container, false);
        ButterKnife.bind(this, v);
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        Typeface myTypefaceBold = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        HomeActivity.tvTitle.setText("View Posted Job");
        pref = getActivity().getSharedPreferences(Constants.myPref, Context.MODE_PRIVATE);
        editor = pref.edit();
        gson = new Gson();


        String json = pref.getString("loginBean", "");
        LoginBean loginBean = gson.fromJson(json, LoginBean.class);
        if (loginBean != null) {

            userId = loginBean.getUserId();
            userType = loginBean.getUserType();
        }

        getAllJobList();

        rc_view_list_post.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rc_view_list_post.setLayoutManager(layoutManager);
        rc_view_list_post.setItemAnimator(new DefaultItemAnimator());
        return v;
    }


    public void getAllJobList() {
        final AlertDialog dialog = new SpotsDialog(getActivity());
        dialog.setCancelable(false);
        dialog.show();
        Call<JobProfileDetails> jobProfileDetailsCall = apiInterface.getJobProfileDetails("get_jobs", userType, userId);
        jobProfileDetailsCall.enqueue(new Callback<JobProfileDetails>() {
            @Override
            public void onResponse(Call<JobProfileDetails> call, Response<JobProfileDetails> response) {
                dialog.dismiss();
                if (response.body() != null) {
                    JobProfileDetails jobProfileDetails = response.body();

                    if (jobProfileDetails.getJobsArry().isEmpty()) {
                        Log.e(TAG, "onResponse: no data Found ");
                    } else {
                        jobsArryArrayList.clear();
                        Log.e(TAG, "onResponse: jobProfileDetails :" + jobProfileDetails);
                        for (int i = 0; i < jobProfileDetails.getJobsArry().size(); i++) {
                            jobsArryArrayList.add(i, jobProfileDetails.getJobsArry().get(i));
                        }
                        adapter = new ViewPostJobAdapter(jobsArryArrayList, getContext());
                        rc_view_list_post.setAdapter(adapter);

                    }

                } else {
                }
                Log.e(TAG, "onResponse: No Valid Response from Server");
            }

            @Override
            public void onFailure(Call<JobProfileDetails> call, Throwable t) {
                dialog.dismiss();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


}
