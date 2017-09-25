package com.ats.sap_recruitment.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ats.sap_recruitment.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewAllPostFragment extends Fragment {
    @BindView(R.id.rc_view_list_post)
    RecyclerView rc_view_list_post;

    public ViewAllPostFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_view_all_post, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

}
