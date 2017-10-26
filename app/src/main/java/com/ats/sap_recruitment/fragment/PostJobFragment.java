package com.ats.sap_recruitment.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ats.sap_recruitment.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostJobFragment extends Fragment {

    @BindView(R.id.textJobTitle)
    TextInputLayout textJobTitle;
    @BindView(R.id.edJobTitle)
    EditText edJobTitle;
    @BindView(R.id.textJobExpMonth)
    TextInputLayout textJobExpMonth;
    @BindView(R.id.edJobMonth)
    EditText edJobMonth;
    @BindView(R.id.textJobExpYear)
    TextInputLayout textJobExpYear;
    @BindView(R.id.edJobYear)
    EditText edJobYear;
    @BindView(R.id.textJobDescription)
    TextInputLayout textJobDescription;
    @BindView(R.id.edJobDiscription)
    EditText edJobDescription;

    public PostJobFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_job, container, false);
        ButterKnife.bind(this, view);
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        textJobDescription.setTypeface(myTypeface);
        textJobExpMonth.setTypeface(myTypeface);
        textJobExpYear.setTypeface(myTypeface);
        textJobTitle.setTypeface(myTypeface);
        edJobDescription.setTypeface(myTypeface);
        edJobMonth.setTypeface(myTypeface);
        edJobYear.setTypeface(myTypeface);
        edJobTitle.setTypeface(myTypeface);

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
