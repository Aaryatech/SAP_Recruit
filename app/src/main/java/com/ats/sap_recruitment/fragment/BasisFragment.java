package com.ats.sap_recruitment.fragment;


import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.adpter.BasisDataAdapter;
import com.ats.sap_recruitment.bean.Categories;
import com.ats.sap_recruitment.bean.MainCat;
import com.ats.sap_recruitment.bean.SubCat;
import com.ats.sap_recruitment.bean.SubSubCat;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;


public class BasisFragment extends Fragment {


    private static RecyclerView.Adapter adapter;
    private static ArrayList<String> data = new ArrayList<>();
    private static String TAG = "BasisFragment";
    @BindView(R.id.rcl_view_basis)
    RecyclerView recyclerBasis;
    @BindView(R.id.tvBasisHead)
    TextView tvBasisHead;
    @BindView(R.id.edBasisMonth)
    EditText edBasisMonth;
    @BindView(R.id.edBasisYear)
    EditText edBasisYear;
    private RecyclerView.LayoutManager layoutManager;
    private String selectedSpecialised;
    private String selectedMainCat;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private Categories categories;
    private ArrayList<MainCat> mainCatArrayList = new ArrayList<>();
    private ArrayList<SubCat> subCatBasisArrayList = new ArrayList<>();
    private ArrayList<SubSubCat> subSubBasisCatArrayList = new ArrayList<>();
    private ArrayList<String> subsubcategoryList = new ArrayList<>();

    public BasisFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_basis, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        selectedSpecialised = bundle.getString("selectedItem");
        selectedMainCat = bundle.getString("BASIS");
        tvBasisHead.setText("Core Basis - " + selectedSpecialised + " Experience");

        pref = getActivity().getApplicationContext().getSharedPreferences(Constants.myPref, MODE_PRIVATE);
        editor = pref.edit();
        gson = new Gson();
        String json = pref.getString("categories", "");
        categories = gson.fromJson(json, Categories.class);

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
                        for (int j = 0; j < subcat.getSubSubCats().size(); j++) {
                            subSubBasisCatArrayList.add(j, subcat.getSubSubCats().get(j));
                            Log.e(TAG, "onCreateView: SubSubCat list" + subSubBasisCatArrayList);
                        }
                    }
                }
            }

            if (!subSubBasisCatArrayList.isEmpty()) {
                data.clear();
                for (int i = 0; i < subSubBasisCatArrayList.size(); i++) {
                    Log.e(TAG, "onCreateView:SubSubCategory Name : " + subSubBasisCatArrayList.get(i).getProfCatName());
                    data.add(i, subSubBasisCatArrayList.get(i).getProfCatName());
                }
            }
        }
        adapter = new BasisDataAdapter(data, getContext());
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

}
