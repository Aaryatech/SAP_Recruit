package com.ats.sap_recruitment.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.adpter.AbapDataAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

public class ABAPFragment extends Fragment {

    @BindView(R.id.rcl_view_abap)
    RecyclerView rcl_view_Abap;
    @BindView(R.id.tvHeadAbap)
    TextView tvAbapHead;
    @BindView(R.id.edAbapMonth)
    EditText edAbapHead;
    @BindView(R.id.edAbapYear)
    EditText edAbapYear;
    private static RecyclerView.Adapter adapter;
    private static ArrayList<String> data = new ArrayList<>();
    private static String TAG = "ABAPFragment";
    private RecyclerView.LayoutManager layoutManager;
    private String selectedSpecialised;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_abap, container, false);
        ButterKnife.bind(this, view);
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");

        Bundle bundle = getArguments();
        selectedSpecialised = bundle.getString("selectedItem");

        tvTitle.setText("SAP Profile - ABAP");
        tvTitle.setTypeface(myTypeface);
        data.clear();
        data.add("Test");
        data.add("Normal");
        data.add("Sample");

        adapter = new AbapDataAdapter(data, getContext());
        rcl_view_Abap.setAdapter(adapter);

        rcl_view_Abap.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rcl_view_Abap.setLayoutManager(layoutManager);
        rcl_view_Abap.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.findItem(R.id.action_save);
        item.setVisible(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
