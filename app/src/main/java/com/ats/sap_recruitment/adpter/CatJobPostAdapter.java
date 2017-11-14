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
import android.widget.Toast;

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

public class CatJobPostAdapter extends RecyclerView.Adapter<CatJobPostAdapter.MyViewHolder> {
    ArrayList<SubSubCat> dataset;
    Typeface myTypeface;
    Typeface myTypefaceBold;
    private Context context;
    private static String TAG = "CatJobPostAdapter";
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private ActvityInformation actvityInformation;
    private int jobId;

    public CatJobPostAdapter(ArrayList<SubSubCat> dataset, Typeface myTypeface, Typeface myTypefaceBold, Context context, int jobId) {
        this.dataset = dataset;
        this.myTypeface = myTypeface;
        this.myTypefaceBold = myTypefaceBold;
        this.context = context;
        this.jobId = jobId;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcl_view_basis_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        TextView tvHeadRclJobCat = holder.tvHeadRclJobCat;
        LinearLayout llRclJobCat = holder.llRclJobCat;
        final LinearLayout llRclJobCatData = holder.llRclJobCatData;
        final LinearLayout llRclJobCatNoData = holder.llRclJobCatNoData;
        TextInputLayout textInputJobCatYear = holder.textInputJobCatYear;
        final EditText edJobCatYear = holder.edJobCatYear;
        TextInputLayout textInputJobCatMonth = holder.textInputJobCatMonth;
        final EditText edJobCatMonth = holder.edJobCatMonth;
        final RecyclerView recyclerView = holder.rclActSelection;
        Button btnRclJobCatUpdate = holder.btnRclJobCatUpdate;

        tvHeadRclJobCat.setTypeface(myTypefaceBold);
        textInputJobCatMonth.setTypeface(myTypeface);
        textInputJobCatYear.setTypeface(myTypeface);
        edJobCatMonth.setTypeface(myTypeface);
        edJobCatYear.setTypeface(myTypeface);


        tvHeadRclJobCat.setText(dataset.get(position).getProfCatName().toString());
        pref = context.getSharedPreferences(Constants.myPref, Context.MODE_PRIVATE);
        editor = pref.edit();
        gson = new Gson();


        tvHeadRclJobCat.setOnClickListener(new View.OnClickListener() {
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
                        if (llRclJobCatNoData.getVisibility() == View.GONE) {
                            llRclJobCatNoData.setVisibility(View.VISIBLE);
                        } else {
                            llRclJobCatNoData.setVisibility(View.GONE);
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


                        if (llRclJobCatData.getVisibility() == View.GONE) {
                            llRclJobCatData.setVisibility(View.VISIBLE);

                            edJobCatMonth.setText(actvityInformation.getCatIds().get(0).getExpMonth());
                            edJobCatYear.setText(actvityInformation.getCatIds().get(0).getExpYear());
                            childAdapter = new BasisChildDataAdapter(data, rekArrays, context);
                            recyclerView.setAdapter(childAdapter);
                            recyclerView.setHasFixedSize(true);
                            layoutManager = new LinearLayoutManager(context);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());

                        } else {
                            llRclJobCatData.setVisibility(View.GONE);
                        }
                    }

                }
            }
        });
        btnRclJobCatUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validData = true;
                if (edJobCatMonth.getText().toString().equalsIgnoreCase("")) {
                    edJobCatMonth.setError("empty, please fill");
                    edJobCatMonth.requestFocus();
                    validData = false;
                }

                if (edJobCatYear.getText().toString().equalsIgnoreCase("")) {
                    edJobCatYear.setError("empty, please fill");
                    edJobCatYear.requestFocus();
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
                    String expMonth = edJobCatMonth.getText().toString();
                    String expYear = edJobCatYear.getText().toString();

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

                    Call<EduStatusCode> statusCodeCall = apiInterface.saveJobWithCat("sav_sap", userType, userId, String.valueOf(jobId), mainCatId, subCatId, catId, actvityId, remarkId, expYear, expMonth);
                    statusCodeCall.enqueue(new Callback<EduStatusCode>() {
                        @Override
                        public void onResponse(Call<EduStatusCode> call, Response<EduStatusCode> response) {
                            dialog.dismiss();
                            if (response.body() != null) {
                                Log.e(TAG, "onResponse: status" + response.body().getStatus());
                                if (response.body().getStatus().equalsIgnoreCase("successs")) {
                                    Toast.makeText(context, "Sap Job Profile save successfully with job Id :  " + jobId, Toast.LENGTH_SHORT).show();
                                } else {

                                }
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

        TextView tvHeadRclJobCat;
        LinearLayout llRclJobCat;
        LinearLayout llRclJobCatData;
        LinearLayout llRclJobCatNoData;
        TextInputLayout textInputJobCatYear;
        EditText edJobCatYear;
        TextInputLayout textInputJobCatMonth;
        EditText edJobCatMonth;
        RecyclerView rclActSelection;
        Button btnRclJobCatUpdate;


        public MyViewHolder(View itemView) {
            super(itemView);

            this.tvHeadRclJobCat = itemView.findViewById(R.id.tvRclBasisHead);
            this.llRclJobCat = itemView.findViewById(R.id.llRclBasis);
            this.llRclJobCatData = itemView.findViewById(R.id.llRclBasisData);
            this.textInputJobCatMonth = itemView.findViewById(R.id.textInputBasisMonth);
            this.textInputJobCatYear = itemView.findViewById(R.id.textInputBasisYear);
            this.edJobCatMonth = itemView.findViewById(R.id.edBasisMonth);
            this.edJobCatYear = itemView.findViewById(R.id.edBasisYear);
            this.llRclJobCatNoData = itemView.findViewById(R.id.llRclBasisNoData);
            this.rclActSelection = itemView.findViewById(R.id.rcl_act_selection);
            this.btnRclJobCatUpdate = itemView.findViewById(R.id.btnRclBasisUpdate);
        }
    }
}
