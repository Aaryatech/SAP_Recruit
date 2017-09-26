package com.ats.sap_recruitment.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.adpter.ViewPostJobAdapter;
import com.ats.sap_recruitment.bean.JobPost;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewJobFragment extends Fragment {

    @BindView(R.id.fab_post_job)
    FloatingActionButton fabPostJob;




    public ViewJobFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_job, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("View Posted Job");



        return view;
    }

}
