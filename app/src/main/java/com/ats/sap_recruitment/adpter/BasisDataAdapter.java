package com.ats.sap_recruitment.adpter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.Activity;
import com.ats.sap_recruitment.bean.ActvityInformation;
import com.ats.sap_recruitment.bean.EduStatusCode;
import com.ats.sap_recruitment.bean.LoginBean;
import com.ats.sap_recruitment.bean.RekArray;
import com.ats.sap_recruitment.bean.SubSubCat;
import com.ats.sap_recruitment.retroInt.APIClient;
import com.ats.sap_recruitment.retroInt.APIInterface;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by datta on 13/10/17.
 */

public class BasisDataAdapter extends RecyclerView.Adapter<BasisDataAdapter.MyViewHolder> {

    private static String TAG = "BasisDataAdapter";
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
    ArrayList<SubSubCat> dataset;
    Typeface myTypeface;
    Typeface myTypefaceBold;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private Context context;
    private ActvityInformation actvityInformation;

//    public BasisDataAdapter(ArrayList<String> dataset, Context context) {
//        this.dataset = dataset;
//        this.context = context;
//    }

    public BasisDataAdapter(ArrayList<SubSubCat> dataset, Context context, Typeface myTypeface, Typeface myTypefaceBold) {
        this.dataset = dataset;
        this.context = context;
        this.myTypeface = myTypeface;
        this.myTypefaceBold = myTypefaceBold;
    }

    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcl_view_basis_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        TextView tvHeadRclBasis = holder.tvHeadRclBasis;
        LinearLayout llRclBasis = holder.llRclBasis;
        final LinearLayout llRclBasisData = holder.llRclBasisData;
        final LinearLayout llRclBasisNoData = holder.llRclBasisNoData;
        TextInputLayout textInputBasisYear = holder.textInputBasisYear;
        TextInputLayout textInputBasisMonth = holder.textInputBasisMonth;
        final EditText edBasisMonth = holder.edBasisMonth;
        final EditText edBasisYear = holder.edBasisYear;
        final RecyclerView recyclerView = holder.rclActSelection;
        Button btnRclBasisUpdate = holder.btnRclBasisUpdate;

        tvHeadRclBasis.setTypeface(myTypefaceBold);
        textInputBasisMonth.setTypeface(myTypeface);
        textInputBasisYear.setTypeface(myTypeface);
        edBasisMonth.setTypeface(myTypeface);
        edBasisYear.setTypeface(myTypeface);


        tvHeadRclBasis.setText(dataset.get(position).getProfCatName().toString());
        pref = context.getSharedPreferences(Constants.myPref, Context.MODE_PRIVATE);
        editor = pref.edit();
        gson = new Gson();

        tvHeadRclBasis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.Adapter childAdapter;
                RecyclerView.LayoutManager layoutManager;


                String catId = dataset.get(position).getProfCatId();
                Log.e(TAG, "onClick: " + catId);
                String json = pref.getString("actvityInformation" + catId, "");
                actvityInformation = gson.fromJson(json, ActvityInformation.class);
                Log.e(TAG, "onClick: ActInfo : " + actvityInformation);

                if (actvityInformation != null) {
                    if (actvityInformation.getActivities().isEmpty()) {
                        if (llRclBasisNoData.getVisibility() == View.GONE) {
                            llRclBasisNoData.setVisibility(View.VISIBLE);
                        } else {
                            llRclBasisNoData.setVisibility(View.GONE);
                        }

                    } else {

                        ArrayList<Activity> data = new ArrayList<>();
                        ArrayList<RekArray> rekArrays = new ArrayList<>();
                        rekArrays.clear();
                        data.clear();
                        for (int i = 0; i < actvityInformation.getActivities().size(); i++) {
                            data.add(i, actvityInformation.getActivities().get(i));
                        }

                        for (int i = 0; i < actvityInformation.getRekArray().size(); i++) {
                            rekArrays.add(i, actvityInformation.getRekArray().get(i));
                        }


                        if (llRclBasisData.getVisibility() == View.GONE) {
                            llRclBasisData.setVisibility(View.VISIBLE);

                            edBasisMonth.setText(actvityInformation.getCatIds().get(0).getExpMonth());
                            edBasisYear.setText(actvityInformation.getCatIds().get(0).getExpYear());
                            childAdapter = new BasisChildDataAdapter(data, rekArrays, context);
                            recyclerView.setAdapter(childAdapter);
                            recyclerView.setHasFixedSize(true);
                            layoutManager = new LinearLayoutManager(context);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());

                        } else {
                            llRclBasisData.setVisibility(View.GONE);
                        }
                    }

                }
            }
        });
        btnRclBasisUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean validData = true;
                if (edBasisMonth.getText().toString().equalsIgnoreCase("")) {
                    edBasisMonth.setError("empty, please fill");
                    edBasisMonth.requestFocus();
                    validData = false;
                }

                if (edBasisYear.getText().toString().equalsIgnoreCase("")) {
                    edBasisYear.setError("empty, please fill");
                    edBasisYear.requestFocus();
                    validData = false;
                }

                if (validData) {
                    String remarkId = "";
                    String actvityId = "";


                    String userType = "0";
                    String userId = "0";
                    String catId = dataset.get(position).getProfCatId();
                    String mainCatId = actvityInformation.getCatIds().get(0).getMainCatId();
                    String subCatId = actvityInformation.getCatIds().get(0).getSubCatId();
                    String expMonth = edBasisMonth.getText().toString();
                    String expYear = edBasisYear.getText().toString();

                    String jsonLogin = pref.getString("loginBean", "");
                    LoginBean loginBean = gson.fromJson(jsonLogin, LoginBean.class);
                    if (loginBean != null) {
                        userType = loginBean.getUserType();
                        userId = loginBean.getUserId();
                    }


                    Log.e(TAG, "onClick: MainCatId : " + mainCatId + " SubCatId : " + subCatId + " SubSubCatId : " + catId);
                    Log.e(TAG, "onClick: Exp Month : " + expMonth + " Exp Year : " + expYear);
                    Log.e(TAG, "onClick: userId : " + userId + " userType : " + userType);


                    Log.e(TAG, "onClick: " + catId);
                    String json = pref.getString("actvityInformation" + catId, "");
                    actvityInformation = gson.fromJson(json, ActvityInformation.class);
                    Log.e(TAG, "onClick: ActInfo : " + actvityInformation);

                    ArrayList<Activity> actArray = new ArrayList<>();
                    ArrayList<RekArray> rekArrays = new ArrayList<>();
                    rekArrays.clear();
                    actArray.clear();
                    for (int i = 0; i < actvityInformation.getActivities().size(); i++) {
                        actArray.add(i, actvityInformation.getActivities().get(i));
                    }

                    for (int i = 0; i < actvityInformation.getRekArray().size(); i++) {
                        rekArrays.add(i, actvityInformation.getRekArray().get(i));
                    }


                    for (int childCount = recyclerView.getChildCount(), i = 0; i < childCount; ++i) {
                        //Log.e(TAG, "onClick: child count : " + childCount);
                        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForLayoutPosition(i);
                        TextView tvActName = viewHolder.itemView.findViewById(R.id.tvActivityName);
                        CheckBox cbSelected = viewHolder.itemView.findViewById(R.id.cbRclSelect);
                        Spinner spnChoice = viewHolder.itemView.findViewById(R.id.spnStatusCode);

                        if (cbSelected.isChecked()) {
                            Log.e(TAG, "onClick: check");
                            String selected = spnChoice.getSelectedItem().toString();
                            String activityName = tvActName.getText().toString();
                            Log.e(TAG, "onClick: Selected String : " + selected + " Activity Name :" + activityName);

                            for (int j = 0; j < actArray.size(); j++) {

                                if (activityName.equalsIgnoreCase(actArray.get(j).getActName())) {
                                    if (actvityId.equals("")) {
                                        actvityId = actArray.get(j).getActId();
                                    } else {
                                        actvityId = actvityId + "," + actArray.get(j).getActId();
                                    }

                                    Log.e(TAG, "onClick: Actvity Id : " + actvityId);
                                    break;
                                }
                            }

                            for (int j = 0; j < rekArrays.size(); j++) {
                                if (selected.equalsIgnoreCase(rekArrays.get(j).getRemrkTitle())) {

                                    if (remarkId.equals("")) {
                                        remarkId = rekArrays.get(j).getRemrkId();
                                    } else {
                                        remarkId = remarkId + "," + rekArrays.get(j).getRemrkId();
                                    }
                                    Log.e(TAG, "onClick: Remark Id : " + remarkId);
                                    break;
                                }
                            }


                        } else {
                            if (remarkId.equals("")) {
                                remarkId = "0";
                            } else {
                                remarkId = remarkId + "," + "0";
                            }

                            Log.e(TAG, "onClick: Unchecked");
                            Log.e(TAG, "onClick: Remark Id : " + remarkId);
                        }
                    }


                    final AlertDialog dialog = new SpotsDialog(context);
                    dialog.show();

                    Call<EduStatusCode> statusCodeCall = apiInterface.saveSapProfile("sav_sap", userType, userId, mainCatId, subCatId, catId, actvityId, remarkId, expYear, expMonth);
                    statusCodeCall.enqueue(new Callback<EduStatusCode>() {
                        @Override
                        public void onResponse(Call<EduStatusCode> call, Response<EduStatusCode> response) {
                            dialog.dismiss();
                            if (response.body() != null) {

                                Log.e(TAG, "onResponse: " + response.body().getStatus());

                            }
                        }

                        @Override
                        public void onFailure(Call<EduStatusCode> call, Throwable t) {
                            dialog.dismiss();
                            Log.e(TAG, "onFailure: " + t.getMessage());

                        }
                    });

                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvHeadRclBasis;
        LinearLayout llRclBasis;
        LinearLayout llRclBasisData;
        LinearLayout llRclBasisNoData;
        TextInputLayout textInputBasisYear;
        EditText edBasisYear;
        TextInputLayout textInputBasisMonth;
        EditText edBasisMonth;
        RecyclerView rclActSelection;
        Button btnRclBasisUpdate;


        public MyViewHolder(View itemView) {
            super(itemView);

            this.tvHeadRclBasis = itemView.findViewById(R.id.tvRclBasisHead);
            this.llRclBasis = itemView.findViewById(R.id.llRclBasis);
            this.llRclBasisData = itemView.findViewById(R.id.llRclBasisData);
            this.textInputBasisMonth = itemView.findViewById(R.id.textInputBasisMonth);
            this.textInputBasisYear = itemView.findViewById(R.id.textInputBasisYear);
            this.edBasisMonth = itemView.findViewById(R.id.edBasisMonth);
            this.edBasisYear = itemView.findViewById(R.id.edBasisYear);
            this.llRclBasisNoData = itemView.findViewById(R.id.llRclBasisNoData);
            this.rclActSelection = itemView.findViewById(R.id.rcl_act_selection);
            this.btnRclBasisUpdate = itemView.findViewById(R.id.btnRclBasisUpdate);
        }
    }
}
