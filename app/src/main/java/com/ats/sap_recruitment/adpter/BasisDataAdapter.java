package com.ats.sap_recruitment.adpter;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.Activity;
import com.ats.sap_recruitment.bean.ActvityInformation;
import com.ats.sap_recruitment.bean.RekArray;
import com.ats.sap_recruitment.bean.SubSubCat;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by datta on 13/10/17.
 */

public class BasisDataAdapter extends RecyclerView.Adapter<BasisDataAdapter.MyViewHolder> {

    private static String TAG = "BasisDataAdapter";
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
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        TextView tvHeadRclBasis = holder.tvHeadRclBasis;
        LinearLayout llRclBasis = holder.llRclBasis;
        final LinearLayout llRclBasisData = holder.llRclBasisData;
        final LinearLayout llRclBasisNoData = holder.llRclBasisNoData;
        TextInputLayout textInputBasisYear = holder.textInputBasisYear;
        TextInputLayout textInputBasisMonth = holder.textInputBasisMonth;
        final EditText edBasisMonth = holder.edBasisMonth;
        final EditText edBasisYear = holder.edBasisYear;
        final RecyclerView recyclerView = holder.rclActSelection;

        tvHeadRclBasis.setTypeface(myTypefaceBold);
        textInputBasisMonth.setTypeface(myTypeface);
        textInputBasisYear.setTypeface(myTypeface);
        edBasisMonth.setTypeface(myTypeface);
        edBasisYear.setTypeface(myTypeface);


        tvHeadRclBasis.setText(dataset.get(position).getProfCatName().toString());

        tvHeadRclBasis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.Adapter childAdapter;
                RecyclerView.LayoutManager layoutManager;


                String catId = dataset.get(position).getProfCatId();
                Log.e(TAG, "onClick: " + catId);
                pref = context.getSharedPreferences(Constants.myPref, Context.MODE_PRIVATE);
                editor = pref.edit();
                gson = new Gson();

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
        }
    }
}
