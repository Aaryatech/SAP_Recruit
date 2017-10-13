package com.ats.sap_recruitment.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ats.sap_recruitment.R;

import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

public class ABAPFragment extends Fragment {

    private LinearLayout llHead1, llHead1Data, llHead2, llHead2Data, llHead3, llHead3Data, llHead4, llHead4Data;
    private TextView tvHead;
    private EditText edMonth, edYear;
    private String selectedSpecialised;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_abap, container, false);

        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");

        Bundle bundle = getArguments();
        selectedSpecialised = bundle.getString("selectedItem");

        tvTitle.setText("SAP Profile - ABAP");
        tvTitle.setTypeface(myTypeface);


        llHead1 = view.findViewById(R.id.llAbapHead1);
        llHead2 = view.findViewById(R.id.llAbapHead2);
        llHead3 = view.findViewById(R.id.llAbapHead3);
        llHead4 = view.findViewById(R.id.llAbapHead4);

        llHead1Data = view.findViewById(R.id.llAbapHead1Data);
        llHead2Data = view.findViewById(R.id.llAbapHead2Data);
        llHead3Data = view.findViewById(R.id.llAbapHead3Data);
        llHead4Data = view.findViewById(R.id.llAbapHead4Data);

        llHead1Data.setVisibility(View.GONE);
        llHead2Data.setVisibility(View.GONE);
        llHead3Data.setVisibility(View.GONE);
        llHead4Data.setVisibility(View.GONE);

        tvHead = view.findViewById(R.id.tvHeadAbap);
        tvHead.setTypeface(myTypeface);

        tvHead.setText("ABAP - " + selectedSpecialised + " Experience");

        edMonth = view.findViewById(R.id.edAbapMonth);
        edYear = view.findViewById(R.id.edAbapYear);
        edMonth.setTypeface(myTypeface);
        edYear.setTypeface(myTypeface);

        llHead1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llHead1Data.getVisibility() == View.GONE) {
                    llHead1Data.setVisibility(View.VISIBLE);
                } else {
                    llHead1Data.setVisibility(View.GONE);
                }
            }
        });

        llHead2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llHead2Data.getVisibility() == View.GONE) {
                    llHead2Data.setVisibility(View.VISIBLE);
                } else {
                    llHead2Data.setVisibility(View.GONE);
                }
            }
        });

        llHead3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llHead3Data.getVisibility() == View.GONE) {
                    llHead3Data.setVisibility(View.VISIBLE);
                } else {
                    llHead3Data.setVisibility(View.GONE);
                }
            }
        });

        llHead4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llHead4Data.getVisibility() == View.GONE) {
                    llHead4Data.setVisibility(View.VISIBLE);
                } else {
                    llHead4Data.setVisibility(View.GONE);
                }
            }
        });

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
