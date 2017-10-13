package com.ats.sap_recruitment.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.adpter.BasisDataAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BasisFragment extends Fragment {


    private static RecyclerView.Adapter adaapter;
    private static ArrayList<String> data = new ArrayList<>();
    private static String TAG = "BasisFragment";
    @BindView(R.id.rcl_view_basis)
    RecyclerView recyclerBasis;
    @BindView(R.id.tvBasisHead)
    TextView tvBasisHead;
    @BindView(R.id.edBasisMonth)
    EditText edBasisHead;
    @BindView(R.id.edBasisYear)
    EditText edBasisYear;
    private RecyclerView.LayoutManager layoutManager;
    private String selectedSpecialised;

    public BasisFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_basis, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        selectedSpecialised = bundle.getString("selectedItem");
        tvBasisHead.setText("Core Basis - " + selectedSpecialised + " Experience");


        data.clear();
        data.add("Linux");
        data.add("HPUX");
        data.add("AIX");
        data.add("Solaris");
        data.add("Windows");

        adaapter = new BasisDataAdapter(data, getContext());
        recyclerBasis.setAdapter(adaapter);

        recyclerBasis.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerBasis.setLayoutManager(layoutManager);
        recyclerBasis.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

}
