package com.ats.sap_recruitment.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.Categories;
import com.ats.sap_recruitment.bean.LoginBean;
import com.ats.sap_recruitment.bean.MainCat;
import com.ats.sap_recruitment.retroInt.APIClient;
import com.ats.sap_recruitment.retroInt.APIInterface;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class JobCategoryFragment extends Fragment {

    private static final String TAG = "JobCategoryFragment";
    @BindView(R.id.rclJobCatMain)
    RecyclerView rclJobCatMain;

    private String userType = "0";
    private String userId = "0";

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

    ArrayList<MainCat> mainCats = new ArrayList<>();

    public JobCategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_category, container, false);
        ButterKnife.bind(this, view);

        pref = getActivity().getSharedPreferences(Constants.myPref, Context.MODE_PRIVATE);
        editor = pref.edit();
        gson = new Gson();

        String jsonLogin = pref.getString("loginBean", "");
        LoginBean loginBean = gson.fromJson(jsonLogin, LoginBean.class);
        if (loginBean != null) {
            userId = loginBean.getUserId();
            userType = loginBean.getUserType();
        }

        String jsonCategory = pref.getString("allCategories", "");
        Categories allCategories = gson.fromJson(jsonCategory, Categories.class);


        if (allCategories != null) {
            mainCats.clear();
            for (int i = 0; i < allCategories.getMainCats().size(); i++) {
                mainCats.add(i, allCategories.getMainCats().get(i));
                Log.e(TAG, "onCreateView: Main Category : " + allCategories.getMainCats().get(i));
            }
        }

        rclJobCatMain.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rclJobCatMain.setLayoutManager(layoutManager);
        rclJobCatMain.setItemAnimator(new DefaultItemAnimator());


        return view;
    }


}
