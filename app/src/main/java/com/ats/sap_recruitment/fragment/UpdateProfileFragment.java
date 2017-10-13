package com.ats.sap_recruitment.fragment;


import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutCompat;
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
import com.ats.sap_recruitment.bean.EduPerProfile;
import com.ats.sap_recruitment.bean.PerProfile;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;
import static com.ats.sap_recruitment.activity.HomeActivity.tvTitle;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateProfileFragment extends Fragment {

    PerProfile perProfile;
    EduPerProfile eduPerProfile;
    ArrayList<String> arrayBasisList = new ArrayList<>();
    ArrayList<String> arrayABAPList = new ArrayList<>();
    ArrayList<String> arrayFunctionalList = new ArrayList<>();
    private Button btnBasis, btnAbap, btnFunctional;
    private LinearLayout llBasis, llAbap, llFunctional, llLsvBasis, llLsvABAP, llLsvFuctional;
    private Button btnPersonalProfile, btnEduProfile;
    private TextView tvPersonal, tvEdu, tvSAP, tvSAPText, tvBasis, tvAbap, tvFunctional, tvName, tvDegree, tvExp;
    private ListView lsvBasis, lsvABAP, lsvFunctional;

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

//        ArrayList for Basis Specialisation
        arrayBasisList.add("OS");
        arrayBasisList.add("DB");
        arrayBasisList.add("SAP Product");

//        ArrayList for Basis Specialisation

        arrayABAPList.add("SampleABAP");
        arrayABAPList.add("TestABAP");

//         ArrayList for Basis Specialisation
        arrayFunctionalList.add("SampleFunctional");
        arrayFunctionalList.add("TestFunctional");

        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences(Constants.myPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = pref.getString("perProfile", "");
        perProfile = gson.fromJson(json, PerProfile.class);
        if (perProfile != null) {
            tvName.setText(perProfile.getProfFname() + " " + perProfile.getProfMname() + " " + perProfile.getProfLname());
            String exp = perProfile.getProfWExpYear() + " year " + perProfile.getProfWExpMonth() + " month";

            if (exp.equalsIgnoreCase(" "))
                tvExp.setText("Fresher");
            else
                tvExp.setText(exp);
        }

        String json2 = pref.getString("eduPerProfile", "");
        eduPerProfile = gson.fromJson(json2, EduPerProfile.class);
        if (eduPerProfile != null) {
            tvDegree.setText(eduPerProfile.getProfEduCourseDetail());
        }

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
                Bundle bundle = new Bundle();
                bundle.putString("selectedItem", selectedItem);
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
                Bundle bundle = new Bundle();
                bundle.putString("selectedItem", selectedItem);
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
                Bundle bundle = new Bundle();
                bundle.putString("selectedItem", selectedItem);
                Fragment fragment = new FunctionalFragment();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, "UpdateProfile");
                ft.commit();

            }
        });


        return view;
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
}
