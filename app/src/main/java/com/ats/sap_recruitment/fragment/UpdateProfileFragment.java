package com.ats.sap_recruitment.fragment;


import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.adpter.SavedSapProfileAdapter;
import com.ats.sap_recruitment.bean.Categories;
import com.ats.sap_recruitment.bean.EduPerProfile;
import com.ats.sap_recruitment.bean.LoginBean;
import com.ats.sap_recruitment.bean.MainCat;
import com.ats.sap_recruitment.bean.PerProfile;
import com.ats.sap_recruitment.bean.SapProfile;
import com.ats.sap_recruitment.bean.SapProfileList;
import com.ats.sap_recruitment.bean.SubCat;
import com.ats.sap_recruitment.bean.SubSubCat;
import com.ats.sap_recruitment.retroInt.APIClient;
import com.ats.sap_recruitment.retroInt.APIInterface;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateProfileFragment extends Fragment {

    public static final String TAG = "UpdateProfileFragment";
    private static RecyclerView.Adapter adapter;
    @BindView(R.id.llViewSapProfile)
    LinearLayout llViewSapProfile;
    @BindView(R.id.llLsvViewSAP)
    LinearLayout llLsvViewSAP;
    @BindView(R.id.rclVeiwSap)
    RecyclerView rclVeiwSap;
    @BindView(R.id.tvLabelViewSAP)
    TextView tvLabelViewSAP;
    @BindView(R.id.tvLabelViewSAPText)
    TextView tvLabelViewSAPText;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<SapProfileList> sapProfileLists = new ArrayList<>();
    private PerProfile perProfile;
    private EduPerProfile eduPerProfile;
    private LoginBean loginBean;
    private String userId = "0";
    private String usrType = "0";
    private ArrayList<String> arrayBasisList = new ArrayList<>();
    private ArrayList<String> arrayABAPList = new ArrayList<>();
    private ArrayList<String> arrayFunctionalList = new ArrayList<>();
    private Button btnBasis, btnAbap, btnFunctional;
    private LinearLayout llBasis, llAbap, llFunctional, llLsvBasis, llLsvABAP, llLsvFuctional;
    private Button btnPersonalProfile, btnEduProfile;
    private TextView tvPersonal, tvEdu, tvSAP, tvSAPText, tvBasis, tvAbap, tvFunctional, tvName, tvDegree, tvExp;
    private ListView lsvBasis, lsvABAP, lsvFunctional;
    private APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
    private ArrayList<MainCat> mainCatArrayList = new ArrayList<>();
    private ArrayList<SubCat> subCatBasisArrayList = new ArrayList<>();
    private ArrayList<SubSubCat> subSubBasisCatArrayList = new ArrayList<>();
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Gson gson;


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LinearLayoutCompat.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static void getTotalHeightofRecyclerView(RecyclerView recyclerView) {

        RecyclerView.Adapter mAdapter = recyclerView.getAdapter();

        int totalHeight = 0;

        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            View mView = recyclerView.findViewHolderForAdapterPosition(i).itemView;

            mView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            totalHeight += mView.getMeasuredHeight();
        }

        if (totalHeight > 100) {
            ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
            params.height = 100;
            recyclerView.setLayoutParams(params);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_profile, container, false);
        ButterKnife.bind(this, view);

        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");
        Typeface myTypefaceBold = Typeface.createFromAsset(getContext().getAssets(), "Free_Serif.ttf");


        tvTitle.setText("Update Profile");
        tvTitle.setTypeface(myTypefaceBold);

        tvName = view.findViewById(R.id.tvProfileName);
        tvDegree = view.findViewById(R.id.tvProfileDegree);
        tvExp = view.findViewById(R.id.tvProfileExp);
        tvPersonal = view.findViewById(R.id.tvLabelPersonalPro);
        tvEdu = view.findViewById(R.id.tvLabelEduPro);
        tvSAP = view.findViewById(R.id.tvLabelSAP);
        tvSAPText = view.findViewById(R.id.tvLabelSAPText);
        tvBasis = view.findViewById(R.id.tvLabelBasis);
        tvAbap = view.findViewById(R.id.tvLabelAbap);
        tvFunctional = view.findViewById(R.id.tvLabelFunctional);

        tvName.setTypeface(myTypeface);
        tvDegree.setTypeface(myTypeface);
        tvExp.setTypeface(myTypeface);
        tvPersonal.setTypeface(myTypefaceBold);
        tvEdu.setTypeface(myTypefaceBold);
        tvSAP.setTypeface(myTypefaceBold);
        tvSAPText.setTypeface(myTypeface);
        tvBasis.setTypeface(myTypefaceBold);
        tvAbap.setTypeface(myTypefaceBold);
        tvFunctional.setTypeface(myTypefaceBold);

        btnBasis = view.findViewById(R.id.btnEditBasis);
        btnAbap = view.findViewById(R.id.btnEditABAP);
        btnFunctional = view.findViewById(R.id.btnEditFunctional);
        btnPersonalProfile = view.findViewById(R.id.btnPersonalProfile);
        btnEduProfile = view.findViewById(R.id.btnEducationalProfile);

        llBasis = view.findViewById(R.id.llUpdateBasis);
        llAbap = view.findViewById(R.id.llUpdateAbap);
        llFunctional = view.findViewById(R.id.llUpdateFunctional);

        lsvBasis = view.findViewById(R.id.lsvBasis);
        lsvABAP = view.findViewById(R.id.lsvABAP);
        lsvFunctional = view.findViewById(R.id.lsvFunctional);

        llLsvBasis = view.findViewById(R.id.llLsvBasis);
        llLsvABAP = view.findViewById(R.id.llLsvABAP);
        llLsvFuctional = view.findViewById(R.id.llLsvFuctional);


        pref = getActivity().getApplicationContext().getSharedPreferences(Constants.myPref, MODE_PRIVATE);
        editor = pref.edit();
        gson = new Gson();
        String json = pref.getString("perProfile", "");
        perProfile = gson.fromJson(json, PerProfile.class);
        if (perProfile != null) {
            tvName.setText(perProfile.getProfFname() + " " + perProfile.getProfMname() + " " + perProfile.getProfLname());

            String exp = perProfile.getProfWExpYear() + " year " + perProfile.getProfWExpMonth() + " month";

            if (exp.equalsIgnoreCase(" ") || perProfile.getProfWStatus().toString().equalsIgnoreCase("FRS"))
                tvExp.setText("Fresher");
            else
                tvExp.setText(exp);
        }
        String json2 = pref.getString("eduPerProfile", "");
        eduPerProfile = gson.fromJson(json2, EduPerProfile.class);
        if (eduPerProfile != null) {
            tvDegree.setText(eduPerProfile.getProfEduCourseDetail());
        }

        String json3 = pref.getString("loginBean", "");
        loginBean = gson.fromJson(json3, LoginBean.class);
        if (loginBean != null) {
            userId = loginBean.getUserId();
            usrType = loginBean.getUserType();
        }


        getAllCategories();
        getAllSapProfileList();

        arrayABAPList.add("SampleABAP");
        arrayABAPList.add("TestABAP");

        arrayFunctionalList.add("SampleFunctional");
        arrayFunctionalList.add("TestFunctional");

       /* cdPersonal = (CircleDisplay) view.findViewById(R.id.circleDisplayPersonal);
        cdPersonal.setAnimDuration(3000);
        cdPersonal.setValueWidthPercent(33f);
        cdPersonal.setTextSize(12f);
        cdPersonal.setColor(Color.argb(255, 0, 122, 173));
        cdPersonal.setDrawText(true);
        cdPersonal.setDrawInnerCircle(true);
        cdPersonal.setFormatDigits(1);
        cdPersonal.setTouchEnabled(false);
        cdPersonal.setUnit("%");
        cdPersonal.setStepSize(0.5f);
        cdPersonal.showValue(84, 100, true);


        cdEducational = (CircleDisplay) view.findViewById(R.id.circleDisplayEducation);
        cdEducational.setAnimDuration(3000);
        cdEducational.setValueWidthPercent(33f);
        cdEducational.setTextSize(12f);
        cdEducational.setColor(Color.argb(255, 0, 122, 173));
        cdEducational.setDrawText(true);
        cdEducational.setDrawInnerCircle(true);
        cdEducational.setFormatDigits(1);
        cdEducational.setTouchEnabled(false);
        cdEducational.setUnit("%");
        cdEducational.setStepSize(0.5f);
        cdEducational.showValue(84, 100, true);*/

        btnPersonalProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new PersonalProfileFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "UpdateProfile");
                ft.commit();
            }
        });


        btnEduProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new EducationalProfileFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "UpdateProfile");
                ft.commit();
            }
        });

//        btnBasis.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                PopupMenu popupMenu = new PopupMenu(getContext(), btnBasis);
//                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu_specialised, popupMenu.getMenu());
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//
//                        switch (item.getItemId()) {
//                            case R.id.special_os:
//                                Fragment fragment = new OsBasisFragment();
//                                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                                ft.replace(R.id.content_frame, fragment, "UpdateProfile");
//                                ft.commit();
//                                break;
//                            case R.id.special_db:
//                                break;
//                            case R.id.special_sap_prod:
//                                break;
//                        }
//                        return false;
//                    }
//                });
//                popupMenu.show();
//            }
//        });

        lsvBasis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = arrayBasisList.get(i);
                String basis = "BASIS";
                Bundle bundle = new Bundle();
                bundle.putString("selectedItem", selectedItem);
                bundle.putString("BASIS", basis);
                //Fragment fragment = new OsBasisFragment();
                Fragment fragment = new BasisFragment();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "UpdateProfile");
                ft.commit();

            }
        });

        lsvABAP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = arrayABAPList.get(i);
                String abap = "ABAP";
                Bundle bundle = new Bundle();
                bundle.putString("selectedItem", selectedItem);
                bundle.putString("ABAP", abap);
                Fragment fragment = new ABAPFragment();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "UpdateProfile");
                ft.commit();

            }
        });

        lsvFunctional.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String selectedItem = arrayFunctionalList.get(i);
                String functional = "Functional";
                Bundle bundle = new Bundle();
                bundle.putString("selectedItem", selectedItem);
                bundle.putString("Functional", functional);
                Fragment fragment = new FunctionalFragment();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "UpdateProfile");
                ft.commit();

            }
        });


        return view;
    }

    @OnClick(R.id.llViewSapProfile)
    public void getVeiwSapClick() {
        if (llLsvViewSAP.getVisibility() == View.GONE) {
            llLsvViewSAP.setVisibility(View.VISIBLE);
            if (!sapProfileLists.isEmpty()) {
                adapter = new SavedSapProfileAdapter(sapProfileLists, getActivity());
                rclVeiwSap.setAdapter(adapter);


                DisplayMetrics displaymetrics = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                int a = (displaymetrics.heightPixels * 90) / 100;
                rclVeiwSap.getLayoutParams().height = a;


                rclVeiwSap.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getContext());
                rclVeiwSap.setLayoutManager(layoutManager);
                rclVeiwSap.setItemAnimator(new DefaultItemAnimator());
                //  getTotalHeightofRecyclerView(rclVeiwSap);
            }


        } else {
            llLsvViewSAP.setVisibility(View.GONE);

        }
    }

    @OnClick(R.id.llUpdateBasis)
    public void getBasisSelected() {
        if (llLsvBasis.getVisibility() == View.GONE) {
            llLsvBasis.setVisibility(View.VISIBLE);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayBasisList) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    String selected = arrayBasisList.get(position);
                    LayoutInflater inflater1 = getActivity().getLayoutInflater();
                    View view1 = inflater1.inflate(R.layout.list_view_specialisation, null);
                    TextView textView = view1.findViewById(R.id.tvLsvSpecialTest);
                    textView.setText(selected);

                    return view1;
                }
            };
            lsvBasis.setAdapter(arrayAdapter);
            lsvBasis.requestFocus();
            setListViewHeightBasedOnChildren(lsvBasis);
        } else {
            llLsvBasis.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btnEditBasis)
    public void getEditBasis() {
        getBasisSelected();
    }

    @OnClick(R.id.llUpdateAbap)
    public void getAbapSelected() {

        if (llLsvABAP.getVisibility() == View.GONE) {
            llLsvABAP.setVisibility(View.VISIBLE);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayABAPList) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    String selected = arrayABAPList.get(position);
                    LayoutInflater inflater1 = getActivity().getLayoutInflater();
                    View view1 = inflater1.inflate(R.layout.list_view_specialisation, null);
                    TextView textView = view1.findViewById(R.id.tvLsvSpecialTest);
                    textView.setText(selected);

                    return view1;
                }
            };
            lsvABAP.setAdapter(arrayAdapter);
            lsvABAP.requestFocus();
            setListViewHeightBasedOnChildren(lsvABAP);

        } else {
            llLsvABAP.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.btnEditABAP)
    public void getEditABAP() {
        getAbapSelected();
    }

    @OnClick(R.id.llUpdateFunctional)
    public void getFuctionalSeleccted() {

        if (llLsvFuctional.getVisibility() == View.GONE) {
            llLsvFuctional.setVisibility(View.VISIBLE);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayFunctionalList) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    String selected = arrayFunctionalList.get(position);
                    LayoutInflater inflater1 = getActivity().getLayoutInflater();
                    View view1 = inflater1.inflate(R.layout.list_view_specialisation, null);
                    TextView textView = view1.findViewById(R.id.tvLsvSpecialTest);
                    textView.setText(selected);

                    return view1;
                }
            };
            lsvFunctional.setAdapter(arrayAdapter);
            lsvFunctional.requestFocus();
            setListViewHeightBasedOnChildren(lsvFunctional);


        } else {
            llLsvFuctional.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.btnEditFunctional)

    public void getEditFunctional() {
        getFuctionalSeleccted();
    }

    public void getAllCategories() {
        final AlertDialog dialog = new SpotsDialog(getActivity());
        dialog.show();
        Call<Categories> categoriesCall = apiInterface.getSpecialisedCategories("get_specilic", usrType, userId);
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                dialog.dismiss();
                Log.e(TAG, "onResponse: Category Data" + response.body());
                if (response.body() != null) {
                    Categories categories = response.body();

                    pref = getActivity().getApplicationContext().getSharedPreferences(Constants.myPref, MODE_PRIVATE);
                    editor = pref.edit();
                    gson = new Gson();
                    String json = gson.toJson(categories);
                    editor.putString("categories", json);
                    editor.apply();

                    Log.e(TAG, "onResponse: category Data : " + categories);

                    for (int i = 0; i < categories.getMainCats().size(); i++) {
                        Log.e(TAG, "onResponse: MainCategory : " + categories.getMainCats().get(i));
                        mainCatArrayList.add(categories.getMainCats().get(i));
                    }

                    if (!mainCatArrayList.isEmpty()) {
                        for (int i = 0; i < mainCatArrayList.size(); i++) {
                            MainCat mainCat = mainCatArrayList.get(i);
                            Log.e(TAG, "onResponse: size of array" + mainCat.getSubCats().size());
                            if (mainCat.getProfCatName().equalsIgnoreCase("Basis")) {
                                for (int j = 0; j < mainCat.getSubCats().size(); j++) {
                                    subCatBasisArrayList.add(j, mainCat.getSubCats().get(j));
                                    Log.e(TAG, "onResponse: SubCat List : " + subCatBasisArrayList);
                                }
                            }
                        }
                    }
                    if (!subCatBasisArrayList.isEmpty()) {
                        for (int i = 0; i < subCatBasisArrayList.size(); i++) {
                            arrayBasisList.add(i, subCatBasisArrayList.get(i).getProfCatName());
                        }
                    }
                } else {
                    Log.e(TAG, "onResponse: Category Data Not Found");
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                dialog.dismiss();
                Log.e(TAG, "onFailure: Errot Occur in getAllCategories :  " + t.getMessage());
            }
        });
    }

    public void getAllSapProfileList() {
        sapProfileLists.clear();
        final AlertDialog dialog = new SpotsDialog(getActivity());
        dialog.show();
        Call<SapProfile> sapProfileCall = apiInterface.getSapProfile("get_mysap_prof_list", usrType, userId, "1");
        sapProfileCall.enqueue(new Callback<SapProfile>() {
            @Override
            public void onResponse(Call<SapProfile> call, Response<SapProfile> response) {
                dialog.dismiss();
                if (response.body() != null) {
                    SapProfile sapProfile = response.body();
                    Log.e(TAG, "onResponse: Sap Profile : " + sapProfile);
                    for (int i = 0; i < sapProfile.getSapProfileList().size(); i++) {
                        sapProfileLists.add(i, sapProfile.getSapProfileList().get(i));
                    }
                    Log.e(TAG, "onResponse: Sap Profile List : " + sapProfileLists);
                } else {
                    Log.e(TAG, "onResponse: SapProfile Data Not Found");
                }

            }

            @Override
            public void onFailure(Call<SapProfile> call, Throwable t) {
                dialog.dismiss();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
