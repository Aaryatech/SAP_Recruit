package com.ats.sap_recruitment.fragment;


import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.activity.HomeActivity;
import com.ats.sap_recruitment.adpter.BasisDataAdapter;
import com.ats.sap_recruitment.bean.ActvityInformation;
import com.ats.sap_recruitment.bean.Categories;
import com.ats.sap_recruitment.bean.LoginBean;
import com.ats.sap_recruitment.bean.MainCat;
import com.ats.sap_recruitment.bean.SubCat;
import com.ats.sap_recruitment.bean.SubSubCat;
import com.ats.sap_recruitment.retroInt.APIClient;
import com.ats.sap_recruitment.retroInt.APIInterface;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class BasisFragment extends Fragment {


    private static RecyclerView.Adapter adapter;
    private static ArrayList<SubSubCat> data = new ArrayList<>();
    private static String TAG = "BasisFragment";
    @BindView(R.id.rcl_view_basis)
    RecyclerView recyclerBasis;
    @BindView(R.id.tvBasisHead)
    TextView tvBasisHead;
    private String userType = "0";
    private String userId = "0";

    private RecyclerView.LayoutManager layoutManager;
    private String selectedSpecialised;
    private String selectedMainCat;
    private String mainCatId;
    private String subCatId;
    private ArrayList<String> subSubCatIdArrayList = new ArrayList<>();
    private Categories categories;
    private LoginBean loginBean;
    private ArrayList<MainCat> mainCatArrayList = new ArrayList<>();
    private ArrayList<SubCat> subCatBasisArrayList = new ArrayList<>();
    private ArrayList<SubSubCat> subSubBasisCatArrayList = new ArrayList<>();
    private ArrayList<ActvityInformation> actvityInformationArrayList = new ArrayList<>();
    //private ArrayList<String> subsubcategoryList = new ArrayList<>();
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

    public BasisFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_basis, container, false);
        ButterKnife.bind(this, view);

        HomeActivity.tvTitle.setText("SAP Specialization");
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        Typeface myTypefaceBold = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        Bundle bundle = getArguments();


        selectedSpecialised = bundle.getString("selectedItem");
        selectedMainCat = bundle.getString("BASIS");
        tvBasisHead.setText("Core Basis - " + selectedSpecialised + " Experience");


        pref = getActivity().getApplicationContext().getSharedPreferences(Constants.myPref, MODE_PRIVATE);
        editor = pref.edit();
        gson = new Gson();
        String json = pref.getString("categories", "");
        categories = gson.fromJson(json, Categories.class);

        String jsonLogin = pref.getString("loginBean", "");
        loginBean = gson.fromJson(jsonLogin, LoginBean.class);
        if (loginBean != null) {
            userId = loginBean.getUserId();
            userType = loginBean.getUserType();
        }


        Log.e(TAG, "onCreateView: " + categories);

        if (categories != null) {
            for (int i = 0; i < categories.getMainCats().size(); i++) {
                Log.e(TAG, "MainCategory List: " + categories.getMainCats().get(i));
                mainCatArrayList.add(categories.getMainCats().get(i));
            }

            if (!mainCatArrayList.isEmpty()) {
                for (int i = 0; i < mainCatArrayList.size(); i++) {
                    MainCat mainCat = mainCatArrayList.get(i);
                    Log.e(TAG, "Size of array : mainCatArrayList " + mainCat.getSubCats().size());
                    if (mainCat.getProfCatName().equalsIgnoreCase("Basis")) {
                        mainCatId = mainCat.getProfCatId().toString();
                        for (int j = 0; j < mainCat.getSubCats().size(); j++) {
                            subCatBasisArrayList.add(j, mainCat.getSubCats().get(j));
                            Log.e(TAG, "SubCat List : " + subCatBasisArrayList);
                        }
                    }
                }
            }
            if (!subCatBasisArrayList.isEmpty()) {
                for (int i = 0; i < subCatBasisArrayList.size(); i++) {
                    SubCat subcat = subCatBasisArrayList.get(i);
                    Log.e(TAG, "onCreateView: Subcat array size" + subcat.getSubSubCats().size());
                    if (subcat.getProfCatName().equalsIgnoreCase(selectedSpecialised)) {
                        subCatId = subcat.getProfCatId().toString();
                        for (int j = 0; j < subcat.getSubSubCats().size(); j++) {
                            subSubBasisCatArrayList.add(j, subcat.getSubSubCats().get(j));
                            Log.e(TAG, "onCreateView: SubSubCat list" + subSubBasisCatArrayList);
                        }
                    }
                }
            }

            if (!subSubBasisCatArrayList.isEmpty()) {
                data.clear();
                subSubCatIdArrayList.clear();
                for (int i = 0; i < subSubBasisCatArrayList.size(); i++) {
                    Log.e(TAG, "onCreateView:SubSubCategory Name : " + subSubBasisCatArrayList.get(i).getProfCatName());
                    data.add(i, subSubBasisCatArrayList.get(i));
                    subSubCatIdArrayList.add(i, subSubBasisCatArrayList.get(i).getProfCatId());
                    Log.e(TAG, "onCreateView: List of All content" + data);

                }
                getActivityInformationData(subSubCatIdArrayList);
            }
        }

        adapter = new BasisDataAdapter(data, getContext(), myTypeface, myTypefaceBold);
        recyclerBasis.setAdapter(adapter);

        recyclerBasis.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerBasis.setLayoutManager(layoutManager);
        recyclerBasis.setItemAnimator(new DefaultItemAnimator());
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


    public void getActivityInformationData(ArrayList<String> subSubCategoryId) {

        for (int i = 0; i < subSubCategoryId.size(); i++) {
            final AlertDialog dialog = new SpotsDialog(getContext());
            dialog.show();
            Call<ActvityInformation> actvityInformationCall = apiInterface.getActivityInformation("get_act_data", userType, userId, subSubCategoryId.get(i));
            actvityInformationCall.enqueue(new Callback<ActvityInformation>() {
                @Override
                public void onResponse(Call<ActvityInformation> call, Response<ActvityInformation> response) {
                    dialog.dismiss();
                    if (response.body() != null) {
                        ActvityInformation actvityInformation = response.body();
                        Log.e(TAG, "onResponse: Actvity Info :" + actvityInformation);
                        String catId = actvityInformation.getCatIds().get(0).getSubSubCatId();
                        String json = gson.toJson(actvityInformation);
                        editor.putString("actvityInformation" + catId, json);
                        editor.apply();
                        Log.e(TAG, "Cat id of Activity Information: " + catId);

                    } else {
                        Toast.makeText(getContext(), "Information Not Fetch correctly", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ActvityInformation> call, Throwable t) {
                    dialog.dismiss();
                    Log.e(TAG, "onFailure: " + t.getMessage());
                }
            });
        }
    }

}
