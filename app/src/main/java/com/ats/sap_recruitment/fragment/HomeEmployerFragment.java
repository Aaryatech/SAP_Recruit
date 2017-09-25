package com.ats.sap_recruitment.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ats.sap_recruitment.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeEmployerFragment extends Fragment {


    @BindView(R.id.llEmprViewPost)
    LinearLayout llEmprViewPost;
    @BindView(R.id.llEmprMatchProfile)
    LinearLayout llEmprMatchProfile;

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
//        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.content_frame, new ViewJobFragment(), "HomeEmplr");
//        ft.commit();

    }


    @OnClick(R.id.llEmprMatchProfile)
    public void matchProfile() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, new MacthProfileFragment());
        ft.addToBackStack("backtoProfile");
        ft.commit();
    }

}