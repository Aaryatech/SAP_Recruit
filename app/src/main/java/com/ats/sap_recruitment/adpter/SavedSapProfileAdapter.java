package com.ats.sap_recruitment.adpter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.SapProfileList;

import java.util.ArrayList;

public class SavedSapProfileAdapter extends RecyclerView.Adapter<SavedSapProfileAdapter.MyViewHolder> {

    ArrayList<SapProfileList> dataset;
    Context context;

    public SavedSapProfileAdapter() {
    }

    public SavedSapProfileAdapter(ArrayList<SapProfileList> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_sap_profile, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TextView tvViewSapSubCat = holder.tvViewSapSubCat;
        TextView tvViewSapSubSubCat = holder.tvViewSapSubSubCat;
        TextView tvViewSapExperience = holder.tvViewSapExperience;
        TextView tvViewSapInterested = holder.tvViewSapInterested;
        Button btnEditViewSapProf = holder.btnEditViewSapProf;

        tvViewSapSubCat.setText(dataset.get(position).getSubCat());
        tvViewSapSubSubCat.setText(dataset.get(position).getSubSubCat());
        tvViewSapExperience.setText(dataset.get(position).getExpDetail());
        tvViewSapInterested.setText(dataset.get(position).getIncrested());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvViewSapSubCat;
        TextView tvViewSapSubSubCat;
        TextView tvViewSapExperience;
        TextView tvViewSapInterested;
        Button btnEditViewSapProf;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvViewSapSubCat = itemView.findViewById(R.id.tvViewSapSubCat);
            this.tvViewSapSubSubCat = itemView.findViewById(R.id.tvViewSapSubSubCat);
            this.tvViewSapExperience = itemView.findViewById(R.id.tvViewSapExperience);
            this.tvViewSapInterested = itemView.findViewById(R.id.tvViewSapInterested);
            this.btnEditViewSapProf = itemView.findViewWithTag(R.id.btnEditViewSapProf);
        }
    }
}
