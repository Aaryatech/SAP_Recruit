package com.ats.sap_recruitment.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.activity.HomeActivity;

import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

public class AssesmentHistoryFragment extends Fragment {

    private TextView tvHead;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assesment_history, container, false);
        tvTitle.setText("Assesment History");

        tvHead = (TextView) view.findViewById(R.id.tvAssesmentHead);

        return view;
    }

}
