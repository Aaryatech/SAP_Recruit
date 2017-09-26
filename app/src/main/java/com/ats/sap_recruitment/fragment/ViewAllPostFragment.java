package com.ats.sap_recruitment.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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

public class ViewAllPostFragment extends Fragment {
    @BindView(R.id.rc_view_list_post)
    RecyclerView rc_view_list_post;

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView.Adapter adapter;
    private static ArrayList<JobPost> data = new ArrayList<>();

    public ViewAllPostFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_view_all_post, container, false);
        ButterKnife.bind(this, v);

        data.add(new JobPost(111, "Pune", "Experience SAP Developer(ABAP)", "2-3 Year", "Full Time"));
        data.add(new JobPost(112, "Mumbai", "Experience SAP Developer(ABAP)", "1-3 Year", "Full Time"));
        data.add(new JobPost(113, "Banglore", "Experience SAP Developer(BASIS)", "1-2 Year", "Part Time"));
        data.add(new JobPost(114, "Mumbai", "Experience SAP Developer(OS)", "2-3 Year", "Full Time"));
        data.add(new JobPost(115, "Mumbai", "Experience SAP Developer(BASIS)", "2-3 Year", "Full Time"));

        adapter = new ViewPostJobAdapter(data, getContext());
        rc_view_list_post.setAdapter(adapter);


        rc_view_list_post.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rc_view_list_post.setLayoutManager(layoutManager);
        rc_view_list_post.setItemAnimator(new DefaultItemAnimator());
        return v;
    }

}
