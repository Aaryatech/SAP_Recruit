package com.ats.sap_recruitment.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.activity.HomeActivity;
import com.ats.sap_recruitment.bean.CompanyPerProfile;
import com.ats.sap_recruitment.bean.CompanyProfile;
import com.ats.sap_recruitment.bean.LoginBean;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CompanyProfileFragment extends Fragment {


    private static final String TAG = "CompanyProfileFragment";
    @BindView(R.id.textFNameEditComp)
    TextInputLayout textFNameEditComp;
    @BindView(R.id.edFNameEditComp)
    EditText edFNameEditComp;

    @BindView(R.id.textMNameEditComp)
    TextInputLayout textMNameEditComp;
    @BindView(R.id.edMNameEditComp)
    EditText edMNameEditComp;

    @BindView(R.id.textLNameEditComp)
    TextInputLayout textLNameEditComp;
    @BindView(R.id.edLNameEditComp)
    EditText edLNameEditComp;

    @BindView(R.id.textCompanyEditComp)
    TextInputLayout textCompanyEditComp;
    @BindView(R.id.edCompanyEditComp)
    EditText edCompanyEditComp;

    @BindView(R.id.textMobileEditComp)
    TextInputLayout textMobileEditComp;
    @BindView(R.id.edMobileNOEditComp)
    EditText edMobileNOEditComp;

    @BindView(R.id.textAltMobileEditComp)
    TextInputLayout textAltMobileEditComp;
    @BindView(R.id.edAltMobileNOEditComp)
    EditText edAltMobileNOEditComp;

    @BindView(R.id.textWebEditComp)
    TextInputLayout textWebEditComp;
    @BindView(R.id.edWebEditComp)
    EditText edWebEditComp;

    @BindView(R.id.textYearExpEditComp)
    TextInputLayout textYearExpEditComp;
    @BindView(R.id.edYearExpEditComp)
    EditText edYearExpEditComp;

    @BindView(R.id.textAddEditComp)
    TextInputLayout textAddEditComp;
    @BindView(R.id.edAddEditComp)
    EditText edAddEditComp;

    @BindView(R.id.textCityEditComp)
    TextInputLayout textCityEditComp;
    @BindView(R.id.edCityEditComp)
    EditText edCityEditComp;

    @BindView(R.id.textStateEditComp)
    TextInputLayout textStateEditComp;
    @BindView(R.id.edStateEditComp)
    EditText edStateEditComp;

    @BindView(R.id.textCompDescEditComp)
    TextInputLayout textCompDescEditComp;
    @BindView(R.id.edCompDescEditComp)
    EditText edCompDescEditComp;

    private String userId = "0", userType = "0";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Gson gson;

    public CompanyProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_profile, container, false);
        ButterKnife.bind(this, view);
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        Typeface myTypefaceBold = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        HomeActivity.tvTitle.setText("Edit Company Profile");

        pref = getActivity().getSharedPreferences(Constants.myPref, Context.MODE_PRIVATE);
        editor = pref.edit();
        gson = new Gson();

        String json1 = pref.getString("loginBean", "");
        LoginBean loginBean = gson.fromJson(json1, LoginBean.class);
        if (loginBean != null) {
            userId = loginBean.getUserId();
            userType = loginBean.getUserType();
        } else {
            Log.e(TAG, "onCreateView:loginBean is null ");
        }


        textFNameEditComp.setTypeface(myTypeface);
        edFNameEditComp.setTypeface(myTypeface);
        textMNameEditComp.setTypeface(myTypeface);
        edMNameEditComp.setTypeface(myTypeface);
        textLNameEditComp.setTypeface(myTypeface);
        edLNameEditComp.setTypeface(myTypeface);
        textCompanyEditComp.setTypeface(myTypeface);
        edCompanyEditComp.setTypeface(myTypeface);
        textMobileEditComp.setTypeface(myTypeface);
        edMobileNOEditComp.setTypeface(myTypeface);
        textAltMobileEditComp.setTypeface(myTypeface);
        edAltMobileNOEditComp.setTypeface(myTypeface);
        textWebEditComp.setTypeface(myTypeface);
        edWebEditComp.setTypeface(myTypeface);
        textYearExpEditComp.setTypeface(myTypeface);
        edYearExpEditComp.setTypeface(myTypeface);
        textAddEditComp.setTypeface(myTypeface);
        edAddEditComp.setTypeface(myTypeface);
        textCityEditComp.setTypeface(myTypeface);
        edCityEditComp.setTypeface(myTypeface);
        textStateEditComp.setTypeface(myTypeface);
        edStateEditComp.setTypeface(myTypeface);
        textCompDescEditComp.setTypeface(myTypeface);
        edCompDescEditComp.setTypeface(myTypeface);

        setBindData();

        return view;
    }

    private void setBindData() {

        String json2 = pref.getString("companyProfile", "");
        CompanyProfile companyProfile = gson.fromJson(json2, CompanyProfile.class);
        if (companyProfile != null) {
            CompanyPerProfile cpProfile = companyProfile.getPerProfile().get(0);
            edFNameEditComp.setText(cpProfile.getProfFname());
            edMNameEditComp.setText(cpProfile.getProfMname());
            edLNameEditComp.setText(cpProfile.getProfLname());
            edCompanyEditComp.setText(cpProfile.getRecCompName());
            edMobileNOEditComp.setText(cpProfile.getProfMobile());
            edAltMobileNOEditComp.setText(cpProfile.getProfAlternetMobile());
            edWebEditComp.setText(cpProfile.getRecCompWebsite());
            edYearExpEditComp.setText(cpProfile.getRecCompYarExp());
            edAddEditComp.setText(cpProfile.getRecCompAddress());
            edCityEditComp.setText(cpProfile.getRecCompCity());
            edStateEditComp.setText(cpProfile.getRecCompState());
            edCompDescEditComp.setText(cpProfile.getRecCompamnyDesc());
        } else {
            Log.e(TAG, "setBindData: no value still set to Company Profile please cheeck");
        }
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
