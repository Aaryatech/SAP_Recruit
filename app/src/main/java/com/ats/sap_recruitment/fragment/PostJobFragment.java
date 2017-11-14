package com.ats.sap_recruitment.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.Categories;
import com.ats.sap_recruitment.bean.LoginBean;
import com.ats.sap_recruitment.bean.SaveJobBaens;
import com.ats.sap_recruitment.retroInt.APIClient;
import com.ats.sap_recruitment.retroInt.APIInterface;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class PostJobFragment extends Fragment {
    public static final String TAG = "PostJobFragment";

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


    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private String userId = "0";
    private String userType = "0";
    private APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

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

        pref = getActivity().getSharedPreferences(Constants.myPref, Context.MODE_PRIVATE);
        editor = pref.edit();
        gson = new Gson();


        String json = pref.getString("loginBean", "");
        LoginBean loginBean = gson.fromJson(json, LoginBean.class);
        if (loginBean != null) {
            userId = loginBean.getUserId();
            userType = loginBean.getUserType();

        }
        getAllCategories();

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


    public void getAllCategories() {

        final AlertDialog dialog = new SpotsDialog(getActivity());
        dialog.show();

        Call<Categories> categoriesCall = apiInterface.getCategoryRecruiter("get_cats_list");
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                dialog.dismiss();
                if (response.body() != null) {

                    Categories allCategories = response.body();

                    pref = getActivity().getApplicationContext().getSharedPreferences(Constants.myPref, MODE_PRIVATE);
                    editor = pref.edit();
                    gson = new Gson();
                    String json = gson.toJson(allCategories);
                    editor.putString("allCategories", json);
                    editor.apply();

                    Log.e(TAG, "onResponse: All Categories:  " + allCategories);

                } else {
                    Toast.makeText(getContext(), "No valid Response from server", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onResponse: Null Response");
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                dialog.dismiss();
                Log.e(TAG, "onFailure: no valid Response from server");

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:
                saveJob();
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }

    public void saveJob() {

        boolean validData = true;
        String jobTitle = edJobTitle.getText().toString();
        String month = edJobMonth.getText().toString();
        String year = edJobYear.getText().toString();
        String jobDesc = edJobDescription.getText().toString();
        if (jobTitle.equalsIgnoreCase("")) {
            edJobTitle.setError("field required");
            edJobTitle.requestFocus();
            validData = false;
        }
        if (month.equalsIgnoreCase("")) {
            edJobMonth.setError("field required");
            edJobMonth.requestFocus();
            validData = false;
        }
        if (year.equalsIgnoreCase("")) {
            edJobYear.setError("field required");
            edJobYear.requestFocus();
            validData = false;
        }
        if (jobDesc.equalsIgnoreCase("")) {
            edJobDescription.setError("field required");
            edJobDescription.requestFocus();
            validData = false;
        }

        if (validData) {
            final AlertDialog dialog = new SpotsDialog(getActivity());
            dialog.show();
            Call<SaveJobBaens> saveJobBaensCall = apiInterface.saveJobProfile("", userType, userId, jobTitle, jobDesc, year, month);
            saveJobBaensCall.enqueue(new Callback<SaveJobBaens>() {
                @Override
                public void onResponse(Call<SaveJobBaens> call, Response<SaveJobBaens> response) {
                    dialog.dismiss();

                    if (response.body() != null) {
                        Log.e(TAG, "onResponse: " + response.body());
                        SaveJobBaens saveJobBaens = response.body();
                        Bundle bundle = new Bundle();
                        bundle.putInt("jobId", saveJobBaens.getJobId());
                        Log.e(TAG, "onCreateView: JobId : " + saveJobBaens.getJobId());
                        Fragment fragment = new JobCategoryFragment();
                        fragment.setArguments(bundle);
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment);
                        ft.addToBackStack("backToProfile");
                        ft.commit();

                    } else {
                        Log.e(TAG, "onResponse: ");
                    }
                }

                @Override
                public void onFailure(Call<SaveJobBaens> call, Throwable t) {
                    dialog.show();
                    Log.e(TAG, "onFailure: no valid response from server");

                }
            });
        }
    }

}
