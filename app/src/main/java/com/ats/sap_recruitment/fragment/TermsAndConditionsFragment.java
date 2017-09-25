package com.ats.sap_recruitment.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ats.sap_recruitment.R;

import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

public class TermsAndConditionsFragment extends Fragment {

    private Button btnStartTest;
    private TextView tvDoItLater, tvTerm1, tvTerm2, tvTerm3, tvTerm4, tvTerm5, tvTerm6, tvTerm7, tvTerm8, tvTerm9;
    private CheckBox cbTerms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_terms_and_conditions, container, false);
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        tvTitle.setText("Assesment Terms & Conditions");
        tvTitle.setTypeface(myTypeface);

        btnStartTest = view.findViewById(R.id.btnStartTest);
        tvDoItLater = view.findViewById(R.id.tvDoItLater);
        cbTerms = view.findViewById(R.id.cbTerms);

        tvTerm1 = view.findViewById(R.id.tvTerm1);
        tvTerm2 = view.findViewById(R.id.tvTerm2);
        tvTerm3 = view.findViewById(R.id.tvTerm3);
        tvTerm4 = view.findViewById(R.id.tvTerm4);
        tvTerm5 = view.findViewById(R.id.tvTerm5);
        tvTerm6 = view.findViewById(R.id.tvTerm6);
        tvTerm7 = view.findViewById(R.id.tvTerm7);
        tvTerm8 = view.findViewById(R.id.tvTerm8);
        tvTerm9 = view.findViewById(R.id.tvTerm9);

        btnStartTest.setTypeface(myTypeface);
        tvDoItLater.setTypeface(myTypeface);
        cbTerms.setTypeface(myTypeface);
        tvTerm1.setTypeface(myTypeface);
        tvTerm2.setTypeface(myTypeface);
        tvTerm3.setTypeface(myTypeface);
        tvTerm4.setTypeface(myTypeface);
        tvTerm5.setTypeface(myTypeface);
        tvTerm6.setTypeface(myTypeface);
        tvTerm7.setTypeface(myTypeface);
        tvTerm8.setTypeface(myTypeface);
        tvTerm9.setTypeface(myTypeface);




       /* cbTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(getActivity(), "Selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Unselected", Toast.LENGTH_SHORT).show();
                }

            }
        });*/

        btnStartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new PreparationFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "TermsAndCond");
                ft.commit();
            }
        });

        return view;
    }

}
